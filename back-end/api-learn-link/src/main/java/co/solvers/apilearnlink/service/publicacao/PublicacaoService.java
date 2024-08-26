package co.solvers.apilearnlink.service.publicacao;

import co.solvers.apilearnlink.domain.canal.Canal;
import co.solvers.apilearnlink.domain.canal.repository.CanalRepository;
import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import co.solvers.apilearnlink.domain.publicacao.repository.PublicacaoRepository;
import co.solvers.apilearnlink.domain.tipopublicacao.TipoPublicacao;
import co.solvers.apilearnlink.domain.tipopublicacao.repository.TipoPublicacaoRepository;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import co.solvers.apilearnlink.exception.InvalidoException;
import co.solvers.apilearnlink.exception.NaoEncontradoException;
import co.solvers.apilearnlink.pilha.PilhaObj;
import co.solvers.apilearnlink.service.canal.CanalService;
import co.solvers.apilearnlink.service.publicacao.dto.PublicacaoCriacaoRequestDto;
import co.solvers.apilearnlink.service.publicacao.dto.PublicacaoListagemResponseDto;
import co.solvers.apilearnlink.service.publicacao.dto.QuantidadePublicacaoDiaListagemDto;
import co.solvers.apilearnlink.service.publicacao.dto.QuantidadePublicacaoMesCanalListagemDto;
import co.solvers.apilearnlink.service.publicacao.dto.mapper.PublicacaoMapper;
import co.solvers.apilearnlink.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublicacaoService {

    private final PublicacaoRepository publicacaoRepository;
    private final CanalRepository canalRepository;
    private final TipoPublicacaoRepository tipoPublicacaoRepository;
    private final UsuarioService usuarioService;
    private final CanalService canalService;

    public Publicacao criarPublicacao(PublicacaoCriacaoRequestDto publicacaoCriacaoRequestDto) {

        verificaTipoVazio(publicacaoCriacaoRequestDto);

        Optional<TipoPublicacao> optTipoPublicacao = tipoPublicacaoRepository.findById(publicacaoCriacaoRequestDto.getIdTipoPublicacao());
        Usuario usuario = usuarioService.buscarPorId(publicacaoCriacaoRequestDto.getIdUsuario());
        Canal canal = canalService.buscarPorId(publicacaoCriacaoRequestDto.getIdCanal());

        Publicacao publicacao = PublicacaoMapper.toEntity(publicacaoCriacaoRequestDto);
        publicacao.setTipoPublicacao(optTipoPublicacao.get());
        publicacao.setCanal(canal);
        publicacao.setUsuario(usuario);

        return publicacaoRepository.save(publicacao);
    }

    public List<Publicacao> listarMaisRecentes() {

        return publicacaoRepository.findAllByOrderByDataHoraDesc();
    }

    public List<Publicacao> listarMaisRecentesPilha() {

        List<Publicacao> publicacoes = publicacaoRepository.findAllByOrderByDataHora();

        if (!publicacoes.isEmpty()) {
            PilhaObj<Publicacao> pilha = new PilhaObj<>(publicacoes.size());

            for (Publicacao publicacao : publicacoes) {
                pilha.push(publicacao);
            }

            for (int i = 0; i < publicacoes.size(); i++) {
                publicacoes.set(i, pilha.pop());
            }
        }

        return publicacoes;
    }


    public Page<Publicacao> listarMaisRecentesPaginado(Pageable pageable) {
        return publicacaoRepository.findAllByOrderByDataHoraDesc(pageable);
    }

    public Page<Publicacao> listarPublicacoesPorCanal(Long canalId, int page, int size, String sortDirection) {
        Sort sort = Sort.by("dataHora");
        sort = sortDirection.equalsIgnoreCase("ASC") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return publicacaoRepository.findByCanalId(canalId, pageable);
    }


    public List<Publicacao> listarMaisAntigo() {

        return publicacaoRepository.findAllByOrderByDataHora();
    }

    public List<Publicacao> listarPorTipo(String tipoPublicacao) {

        verificaTipoPublicacaoVazio(tipoPublicacao);

        return publicacaoRepository.findAllByTipoPublicacaoTipoOrderByDataHoraDesc(tipoPublicacao.toUpperCase());
    }

    public Publicacao listarPorId(int id) {

        verificaIdVazio(id);

        return publicacaoRepository.findById(id).get();
    }

    public Publicacao editarConteudo(int id, String novoConteudo, String novoCanal) {
        verificaConteudoVazio(novoConteudo);
        verificaIdVazio(id);

        Optional<Publicacao> optPublicacao = publicacaoRepository.findById(id);

        Publicacao publicacao = optPublicacao.get();
        publicacao.setConteudo(novoConteudo);
        Canal novoCanalNome = canalRepository.findByNome(novoCanal);
        publicacao.setCanal(novoCanalNome);


        return publicacaoRepository.save(publicacao);
    }


    public void deletar(Integer id) {

        verificaIdVazio(id);
        publicacaoRepository.deleteById(id);
    }


    public List<Publicacao> listarPorPalavraChave(String palavraChave) {

        if (palavraChave.isBlank()) {
            throw new InvalidoException("Palavra chave");
        }

        return publicacaoRepository.findByConteudoLikePalavrachaveOrderByDataHoraDesc(palavraChave.toUpperCase());
    }

    public String[][] buscaQuantidadeDePublicacoesPorDiaMatriz(int mes, int ano) {
        List<QuantidadePublicacaoDiaListagemDto> quantidadePublicacoes = publicacaoRepository.buscaQuantidadeDePublicacaoPorDia(mes, ano);
        String[][] m = new String[31][2];

        if (quantidadePublicacoes.isEmpty()) return null;

        for (int coluna = 0; coluna < m[0].length; coluna++) {

            for (int linha = 0; linha < m.length; linha++) {
                if (coluna == 0) {
                    m[linha][coluna] = quantidadePublicacoes.get(linha).getDataPublicacao().toString();
                } else {
                    m[linha][coluna] = quantidadePublicacoes.get(linha).getQuantidadePublicacoes().toString();
                }
            }
        }
        return m;
    }

    public List<QuantidadePublicacaoDiaListagemDto> buscaQuantidadeDePublicacoesPorDia(int mes, int ano) {

        return publicacaoRepository.buscaQuantidadeDePublicacaoPorDia(mes, ano);
    }

    public List<QuantidadePublicacaoMesCanalListagemDto> buscaQuantidadePublicacoesEmCadaCanal(int mes, int ano) {

        return publicacaoRepository.buscaQuantidadeDePublicacoesEmCadaCanal(mes, ano);
    }

    public QuantidadePublicacaoMesCanalListagemDto buscaCanalComMaiorNumeroDePublicacoes(int mes, int ano) {
        Optional<QuantidadePublicacaoMesCanalListagemDto> canalMaisPublicacoes = publicacaoRepository.buscaCanalComMaiorNumeroDePublicacoes(mes, ano);

        if (canalMaisPublicacoes.isEmpty()) throw new NaoEncontradoException("Canal com maior número de publicações");

        return canalMaisPublicacoes.get();
    }


    // Verificaçoes de existencia e vazio

    public boolean verificaIdExistente(int id) {

        Optional<Publicacao> IdValidacaoExistente = publicacaoRepository.findById(id);
        return IdValidacaoExistente.isPresent();
    }

    public boolean verificaListaPublicacaoVazia() {

        List<Publicacao> publicacoes = publicacaoRepository.findAllByOrderByDataHoraDesc();
        return publicacoes.isEmpty();
    }

    public void verificaIdVazio(Integer id) {

        Optional<Publicacao> IdValidacaoVazio = publicacaoRepository.findById(id);

        if (IdValidacaoVazio.isEmpty()) {
            throw new NaoEncontradoException("Publicação");
        }
    }

    public void verificaTipoVazio(PublicacaoCriacaoRequestDto novaPublicacao) {
        Optional<TipoPublicacao> optTipoPublicacao = tipoPublicacaoRepository.findById(novaPublicacao.getIdTipoPublicacao());

        if (optTipoPublicacao.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de publicação inválido");
        }
    }

    public boolean verificaDtoVazia(List<PublicacaoListagemResponseDto> dto) {
        return dto.isEmpty();
    }

    public void verificaConteudoVazio(String novoConteudo) {

        if (novoConteudo.isBlank()) {
            throw new InvalidoException("Conteúdo");
        }
    }

    public void verificaTipoPublicacaoVazio(String tipoPublicacao) {

        if (tipoPublicacao.isBlank()) {
            throw new InvalidoException("Tipo de publicação");

        }
    }

}
