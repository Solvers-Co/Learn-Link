package co.solvers.apilearnlink.service.publicacao;

import co.solvers.apilearnlink.domain.canal.Canal;
import co.solvers.apilearnlink.domain.canal.repository.CanalRepository;
import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import co.solvers.apilearnlink.domain.publicacao.PublicacaoStatus;
import co.solvers.apilearnlink.domain.publicacao.repository.PublicacaoRepository;
import co.solvers.apilearnlink.domain.tipopublicacao.TipoPublicacao;
import co.solvers.apilearnlink.domain.tipopublicacao.repository.TipoPublicacaoRepository;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import co.solvers.apilearnlink.exception.InvalidoException;
import co.solvers.apilearnlink.exception.NaoEncontradoException;
import co.solvers.apilearnlink.service.canal.CanalService;
import co.solvers.apilearnlink.service.publicacao.dto.PublicacaoCriacaoRequestDto;
import co.solvers.apilearnlink.service.publicacao.dto.QuantidadePublicacaoDiaListagemDto;
import co.solvers.apilearnlink.service.publicacao.dto.QuantidadePublicacaoMesCanalListagemDto;
import co.solvers.apilearnlink.service.publicacao.dto.mapper.PublicacaoMapper;
import co.solvers.apilearnlink.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
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

    public Page<Publicacao> listarMaisRecentesPaginado(Pageable pageable) {
        return publicacaoRepository.findByStatus(pageable, PublicacaoStatus.ATIVO);
    }

    public Page<Publicacao> listarPublicacoesPorCanal(Long canalId, int page, int size, String sortDirection) {
        Sort sort = Sort.by("dataHora");
        sort = sortDirection.equalsIgnoreCase("ASC") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return publicacaoRepository.findByCanalIdAndStatus(canalId, PublicacaoStatus.ATIVO, pageable);
    }


    public List<Publicacao> listarPorTipo(String tipoPublicacao) {

        verificaTipoPublicacaoVazio(tipoPublicacao);

        return publicacaoRepository.findAllByTipoPublicacaoTipoAndStatusOrderByDataHoraDesc(tipoPublicacao.toUpperCase(), PublicacaoStatus.ATIVO);
    }

    public Publicacao listarPorId(int id) {

        verificaIdVazio(id);
        verificaPublicacaoAtiva(id);

        return publicacaoRepository.findById(id).get();
    }

    public Publicacao editarConteudo(int id, String novoConteudo, String novoCanal) {
        verificaConteudoVazio(novoConteudo);
        verificaPublicacaoAtiva(id);
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
        verificaPublicacaoAtiva(id);

        Optional<Publicacao> optPublicacao = publicacaoRepository.findById(id);

        Publicacao publicacao = optPublicacao.get();
        publicacao.setStatus(PublicacaoStatus.EXCLUIDO);

        publicacaoRepository.save(publicacao);
    }

    public Page<Publicacao> listarPorPalavraChavePaginado(String palavraChave, Pageable pageable) {

        if (palavraChave.isBlank()) {
            throw new InvalidoException("Palavra chave");
        }

        return publicacaoRepository.findByConteudoLikePalavrachaveAndStatusOrderByDataHoraDesc(palavraChave.toUpperCase(), PublicacaoStatus.ATIVO, pageable);
    }


    public List<QuantidadePublicacaoDiaListagemDto> listarQuantidadeDePublicacaoPorDia(int mes, int ano) {
        return publicacaoRepository.buscaQuantidadeDePublicacaoPorDia(mes, ano);
    }

    public List<QuantidadePublicacaoMesCanalListagemDto> buscaQuantidadePublicacoesEmCadaCanal(int mes, int ano) {

        return publicacaoRepository.buscaQuantidadeDePublicacoesEmCadaCanal(mes, ano, PublicacaoStatus.ATIVO);
    }

    public QuantidadePublicacaoMesCanalListagemDto buscaCanalComMaiorNumeroDePublicacoes(int mes, int ano) {
        Optional<QuantidadePublicacaoMesCanalListagemDto> canalMaisPublicacoes = publicacaoRepository.buscaCanalComMaiorNumeroDePublicacoes(mes, ano, PublicacaoStatus.ATIVO);

        if (canalMaisPublicacoes.isEmpty()) throw new NaoEncontradoException("Canal com maior número de publicações");

        return canalMaisPublicacoes.get();
    }


    // Verificaçoes de existencia e vazio

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


    public void verificaPublicacaoAtiva (int idPublicacao) {
        Optional<Publicacao> publicacaoOptional = publicacaoRepository.findById(idPublicacao);

        if (publicacaoOptional.get().getStatus().equals(PublicacaoStatus.EXCLUIDO)) {
            throw new NaoEncontradoException("Publicação");
        }
    }


    public List<Publicacao> listarPorUsuario(Long idUsuario) {
        return publicacaoRepository.findByUsuarioId(idUsuario, PublicacaoStatus.ATIVO);
    }


}
