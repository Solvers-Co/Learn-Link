package co.solvers.apilearnlink.service.publicacao;

import co.solvers.apilearnlink.domain.canal.Canal;
import co.solvers.apilearnlink.domain.canal.repository.CanalRepository;
import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import co.solvers.apilearnlink.domain.publicacao.PublicacaoStatus;
import co.solvers.apilearnlink.domain.publicacao.repository.PublicacaoRepository;
import co.solvers.apilearnlink.domain.respostaImagem.RespostaImagem;
import co.solvers.apilearnlink.domain.tipopublicacao.TipoPublicacao;
import co.solvers.apilearnlink.domain.tipopublicacao.repository.TipoPublicacaoRepository;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import co.solvers.apilearnlink.exception.InvalidoException;
import co.solvers.apilearnlink.exception.NaoEncontradoException;
import co.solvers.apilearnlink.exception.PublicacaoJaArquivadaException;
import co.solvers.apilearnlink.service.canal.CanalService;
import co.solvers.apilearnlink.service.publicacao.dto.PublicacaoCriacaoRequestDto;
import co.solvers.apilearnlink.service.publicacao.dto.QuantidadePublicacaoDiaListagemDto;
import co.solvers.apilearnlink.service.publicacao.dto.QuantidadePublicacaoMesCanalListagemDto;
import co.solvers.apilearnlink.service.publicacao.dto.mapper.PublicacaoMapper;
import co.solvers.apilearnlink.service.usuario.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;
import software.amazon.awssdk.services.lambda.model.LambdaException;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalDateTime;
import java.util.*;

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
        publicacao.setStatus(PublicacaoStatus.ATIVO);
        uploadFoto(publicacaoCriacaoRequestDto.getImagemUrl(), publicacao);

        return publicacaoRepository.save(publicacao);
    }

    public Publicacao uploadFoto(byte[] imagemBytes, Publicacao publicacao){

        String funcao = "arn:aws:lambda:us-east-1:718117031225:function:lambda-envio-imagens-learnlink";
        Region region = Region.US_EAST_1;

        String base64String = Base64.getEncoder().encodeToString(imagemBytes);
        LocalDateTime dataImagem = LocalDateTime.now();
        String dataImagemString = dataImagem.toString();

        LambdaClient awsLambda = LambdaClient.builder()
                .region(region)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();

        InvokeResponse res = null;
        try {
            Map<String, String> parametros = new HashMap<>();
            parametros.put("imageBase64", base64String);
            parametros.put("nomeArquivo", dataImagemString);
            System.out.println(parametros);

            SdkBytes payload = SdkBytes.fromUtf8String(objectMapper.writeValueAsString(parametros));

            InvokeRequest request = InvokeRequest.builder()
                    .functionName(funcao)
                    .payload(payload)
                    .build();

            res = awsLambda.invoke(request);

            String value = res.payload().asUtf8String();

            RespostaImagem respostaImagem =
                    objectMapper.readValue(value, RespostaImagem.class);

            System.out.println();
            if (respostaImagem.status() == 201) {
                System.out.println("Upload da imagem concluído!");

                publicacao.setUrlImagem(respostaImagem.urlArquivo());

                return publicacao;
            } else {
                System.out.println("Upload falhou! Motivo: " + respostaImagem.resultado());
            }

        } catch (LambdaException | JsonProcessingException e) {
            System.err.println(e.getMessage());
        }

        awsLambda.close();
        return null;
    }

    public Page<Publicacao> listarMaisRecentesPaginado(Pageable pageable) {
        return publicacaoRepository.findByStatus(pageable, PublicacaoStatus.ATIVO);
    }

    public Page<Publicacao> listarPublicacoesPorCanal(Long canalId, int page, int size, String sortDirection) {
        if (!sortDirection.equalsIgnoreCase("ASC") && !sortDirection.equalsIgnoreCase("DESC")) {
            throw new IllegalArgumentException("Invalid sort direction: " + sortDirection);
        }

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

    public void verificaIdVazio(Integer id) {

        Optional<Publicacao> IdValidacaoVazio = publicacaoRepository.findById(id);

        if (IdValidacaoVazio.isEmpty()) {
            throw new NaoEncontradoException("Publicação");
        }
    }

    public void verificaTipoVazio(PublicacaoCriacaoRequestDto novaPublicacao) {
        Optional<TipoPublicacao> optTipoPublicacao = tipoPublicacaoRepository.findById(novaPublicacao.getIdTipoPublicacao());

        if (optTipoPublicacao.isEmpty()) {
            throw new NaoEncontradoException("Tipo de publicação");
        }
    }

    public void verificaConteudoVazio(String novoConteudo) {

        if (novoConteudo.isBlank()) {
            throw new InvalidoException("Conteúdo");
        }
    }

    public void verificaTipoPublicacaoVazio(String tipoPublicacao) {
        if (tipoPublicacao == null || tipoPublicacao.isBlank()) {
            throw new InvalidoException("Tipo de publicação");
        }
    }

    public void verificaPublicacaoAtiva(int idPublicacao) {
        Optional<Publicacao> publicacaoOptional = publicacaoRepository.findById(idPublicacao);

        if (publicacaoOptional.get().getStatus().equals(PublicacaoStatus.EXCLUIDO)) {
            throw new InvalidoException("Publicação");
        }
    }

    public List<Publicacao> listarPorUsuario(Long idUsuario) {
        return publicacaoRepository.findByUsuarioId(idUsuario, PublicacaoStatus.ATIVO);
    }

    public Publicacao arquivarPublicacao(int id) {
        Optional<Publicacao> optPublicacao = publicacaoRepository.findById(id);

        if (optPublicacao.isEmpty()) {
            throw new NaoEncontradoException("Publicação não encontrada");
        }

        Publicacao publicacao = optPublicacao.get();

        if (publicacao.getStatus() == PublicacaoStatus.ARQUIVADO) {
            throw new PublicacaoJaArquivadaException("Publicação já arquivada");
        }

        publicacao.setStatus(PublicacaoStatus.ARQUIVADO);

        return publicacaoRepository.save(publicacao);
    }

    public String buscarImagem(Integer id){
        Publicacao publicacao = publicacaoRepository.findUrlImagemById(id);
        return publicacao.getUrlImagem();
    }
}
