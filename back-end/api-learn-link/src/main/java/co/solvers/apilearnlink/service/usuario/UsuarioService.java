package co.solvers.apilearnlink.service.usuario;

import co.solvers.apilearnlink.api.configuration.security.jwt.GerenciadorTokenJwt;
import co.solvers.apilearnlink.domain.classificacao.Classificacao;
import co.solvers.apilearnlink.domain.endereco.Endereco;
import co.solvers.apilearnlink.domain.especialidade.Especialidade;
import co.solvers.apilearnlink.domain.registroLogin.RegistroLogin;
import co.solvers.apilearnlink.domain.respostaimagem.RespostaImagem;
import co.solvers.apilearnlink.domain.tipostatus.TipoStatus;
import co.solvers.apilearnlink.domain.tipostatus.repository.TipoStatusRepository;
import co.solvers.apilearnlink.domain.tipousuario.TipoUsuario;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import co.solvers.apilearnlink.domain.usuario.repository.UsuarioRepository;
import co.solvers.apilearnlink.domain.views.ReacoesEmComentariosDoUsuario.QtdReacoesComentariosUsuarioView;
import co.solvers.apilearnlink.exception.ConflitoException;
import co.solvers.apilearnlink.exception.NaoEncontradoException;
import co.solvers.apilearnlink.service.classificacao.ClassificacaoService;
import co.solvers.apilearnlink.service.endereco.EnderecoService;
import co.solvers.apilearnlink.service.endereco.dto.EnderecoCriacaoDto;
import co.solvers.apilearnlink.service.endereco.dto.mapper.EnderecoMapper;
import co.solvers.apilearnlink.service.especialidade.EspecialidadeService;
import co.solvers.apilearnlink.service.reacoesemcomentariosdousuario.QtdReacoesComentariosUsuarioService;
import co.solvers.apilearnlink.service.registrologin.RegistroLoginService;
import co.solvers.apilearnlink.service.tipostatus.TipoStatusService;
import co.solvers.apilearnlink.service.tipousuario.TipoUsuarioService;
import co.solvers.apilearnlink.service.usuario.autenticacao.dto.UsuarioLoginDto;
import co.solvers.apilearnlink.service.usuario.autenticacao.dto.UsuarioTokenDto;
import co.solvers.apilearnlink.service.usuario.dto.UsuarioAceitacaoListagemDto;
import co.solvers.apilearnlink.service.usuario.dto.UsuarioListagemRankingDto;
import co.solvers.apilearnlink.service.usuario.dto.mapper.UsuarioMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;
import software.amazon.awssdk.services.lambda.model.LambdaException;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final RegistroLoginService registroLoginService;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final TipoStatusRepository tipoStatusRepository;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final AuthenticationManager authenticationManager;
    private final ClassificacaoService classificacaoService;
    private final EnderecoService enderecoService;
    private final TipoUsuarioService tipoUsuarioService;
    private final TipoStatusService tipoStatusService;
    private final EspecialidadeService especialidadeService;
    private final QtdReacoesComentariosUsuarioService qtdReacoesComentariosUsuarioService;

    public Usuario criar(Usuario usuario) {

        verificaEmailExistente(usuario.getEmail());

        TipoStatus tipoStatus = tipoStatusService.buscarPorId(1);
        Classificacao classificacao = classificacaoService.buscarPorClassificacao("JUNIOR");
        TipoUsuario tipoUsuario = tipoUsuarioService.buscarPorTipoUsuario("COMUM");

        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());

        usuario.setSenha(senhaCriptografada);
        usuario.setTipoStatus(tipoStatus);
        usuario.setTipoUsuario(tipoUsuario);
        usuario.setClassificacao(classificacao);

        if (usuario.getEndereco() != null) {
            Endereco endereco = enderecoService.salvar(usuario.getEndereco());
            usuario.setEndereco(endereco);
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario criarAdm(Usuario usuario) {

        verificaEmailExistente(usuario.getEmail());

        TipoStatus tipoStatus = tipoStatusService.buscarPorId(1);
        Classificacao classificacao = classificacaoService.buscarPorClassificacao("JUNIOR");
        TipoUsuario tipoUsuario = tipoUsuarioService.buscarPorTipoUsuario("ADMIN");

        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());

        usuario.setSenha(senhaCriptografada);
        usuario.setTipoStatus(tipoStatus);
        usuario.setTipoUsuario(tipoUsuario);
        usuario.setClassificacao(classificacao);

        if (usuario.getEndereco() != null) {
            Endereco endereco = enderecoService.salvar(usuario.getEndereco());
            usuario.setEndereco(endereco);
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario finalizarCadastro(Long idUsuario, Long idEspecialidade, @Valid EnderecoCriacaoDto enderecoCadastrar) {
        Usuario usuarioBd = buscarPorId(idUsuario);

        if (usuarioBd.getEndereco() != null && usuarioBd.getEspecialidade() != null) {
            throw new ConflitoException("Usuário");
        }

        Especialidade especialidade = especialidadeService.buscarPorId(idEspecialidade);
        usuarioBd.setEspecialidade(especialidade);

        Endereco endereco = enderecoService.salvar(EnderecoMapper.toEntity(enderecoCadastrar));
        usuarioBd.setEndereco(endereco);

        return usuarioRepository.save(usuarioBd);
    }

    public List<Usuario> listarUsuariosTipoStatus(String status) {
        TipoStatus tipoStatus = tipoStatusService.buscarPorStatus(status);

        List<Usuario> usuarios = usuarioRepository.findByTipoStatus(tipoStatus);

        return usuarios;
    }


    public Usuario buscarPorId(Long id) {

        verificaIdExistente(id);

        return usuarioRepository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Usuário")
        );
    }

    public void deletar(Long id) {

        verificaIdExistente(id);

        usuarioRepository.deleteById(id);
    }


    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto) {
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha()
        );

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado =
                usuarioRepository.findByEmail(usuarioLoginDto.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        RegistroLogin registroLogin = registroLoginService.gerarLog(usuarioAutenticado.getId());

        final String token = gerenciadorTokenJwt.generateToken(authentication);
        return UsuarioMapper.of(usuarioAutenticado, token, registroLogin);
    }

    public UsuarioTokenDto desconectar(Long id) {
        final Usuario usuario = usuarioRepository.findById(id).get();

        if (usuario == null) {
            throw new NaoEncontradoException("Usuário");
        }

        return UsuarioMapper.desconectar(usuario);
    }

    public Usuario buscarPorEmail(String email) {

        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        if (usuario.isEmpty()) {
            throw new NaoEncontradoException("Email");
        }

        return usuario.get();
    }

    public Usuario atualizar(Long id, String senha) {

        verificaIdExistente(id);

        Optional<Usuario> usuario = usuarioRepository.findById(id);

        String senhaCriptografada = passwordEncoder.encode(senha);

        usuario.get().setSenha(senhaCriptografada);

        return usuarioRepository.save(usuario.get());
    }

    public Usuario alterarStatus(Long id, Integer idTipoStatus, Long idUsuarioRequisicao) {
        verificaSeEAdm(idUsuarioRequisicao);

        Usuario usuario = buscarPorId(id);

        TipoStatus tipoStatus = tipoStatusService.buscarPorId(idTipoStatus);

        if (usuario.getTipoStatus().getStatus().equals(tipoStatus.getStatus()))
            throw new ConflitoException("Tipo status");

        usuario.setTipoStatus(tipoStatus);

        return usuarioRepository.save(usuario);
    }


    public List<UsuarioListagemRankingDto> ranking() {

        List<UsuarioListagemRankingDto> usuarios = usuarioRepository.findRanking();

        if (usuarios.isEmpty()) {
            throw new NaoEncontradoException("Usuario");
        }

        return usuarios;
    }

    public RespostaImagem uploadFoto(byte[] imagemBytes, Long id){

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

                Usuario usuario = buscarPorId(id);
                usuario.setUrlImagemPerfil(respostaImagem.urlArquivo());
                usuarioRepository.save(usuario);

                return respostaImagem;
            } else {
                System.out.println("Upload falhou! Motivo: " + respostaImagem.resultado());
            }

        } catch (LambdaException | JsonProcessingException e) {
            System.err.println(e.getMessage());
        }


        awsLambda.close();


        return null;
    }

    public String buscarImagem(Long idUsuario){
        Usuario usuario = usuarioRepository.findUrlImagemPerfilById(idUsuario);

        return  usuario.getUrlImagemPerfil();
    }


    // Verificações de existência e vazio


    public void verificaEmailExistente(String email) {

        Optional<Usuario> usuarioValidacaoEmailExistente = usuarioRepository.findByEmail(email);

        if (usuarioValidacaoEmailExistente.isPresent()) {
            throw new ConflitoException("Email do usuário");
        }
    }

    public void verificaIdExistente(Long id) {

        usuarioRepository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Usuário")
        );

    }

    public void verificaSeEAdm(Long id) {
        Usuario usuario = buscarPorId(id);

        if (!usuario.getTipoUsuario().getTipoUsuario().equalsIgnoreCase("ADMIN")){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não autorizado");
        }
    }

    public Page<UsuarioAceitacaoListagemDto> listagemDeUsuariosPaginado(Pageable pageable) {
        return usuarioRepository.findAllUsuariosPaginado(pageable);
    }

    public Page<UsuarioAceitacaoListagemDto> listagemDeUsuariosAtivosPaginado(Pageable pageable) {
        return usuarioRepository.findAllUsuariosAtivosPaginado(pageable);
    }

    public Page<UsuarioAceitacaoListagemDto> listagemDeUsuariosPendentesPaginado(Pageable pageable) {
        return usuarioRepository.findAllUsuariosPendentesPaginado(pageable);
    }

    public Page<UsuarioAceitacaoListagemDto> listagemDeUsuariosNegadosPaginado(Pageable pageable) {
        return usuarioRepository.findAllUsuariosNegadosPaginado(pageable);
    }

    public Classificacao classificarUsuario(Long id) {
        Optional<QtdReacoesComentariosUsuarioView> reacoes = qtdReacoesComentariosUsuarioService.listagemQtdReacoesComentarios(id);
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(id);

        if (reacoes.isEmpty()){
            Classificacao classificacao = classificacaoService.buscarPorClassificacao("INICIANTE");
            usuarioEncontrado.get().setClassificacao(classificacao);
            usuarioRepository.save(usuarioEncontrado.get());
            return classificacao;
        }

        int qtdPontosUsuario = reacoes.get().getReacoes() * reacoes.get().getPontuacao();

        if (qtdPontosUsuario >= 100) {
            Classificacao classificacao = classificacaoService.buscarPorClassificacao("ESPECIALISTA");
            usuarioEncontrado.get().setClassificacao(classificacao);
            usuarioRepository.save(usuarioEncontrado.get());
            return classificacao;
        } else if (qtdPontosUsuario >= 60) {
            Classificacao classificacao = classificacaoService.buscarPorClassificacao("SENIOR");
            usuarioEncontrado.get().setClassificacao(classificacao);
            usuarioRepository.save(usuarioEncontrado.get());
            return classificacao;
        } else if (qtdPontosUsuario >= 30) {
            Classificacao classificacao = classificacaoService.buscarPorClassificacao("PLENO");
            usuarioEncontrado.get().setClassificacao(classificacao);
            usuarioRepository.save(usuarioEncontrado.get());
            return classificacao;
        } else {
            Classificacao classificacao = classificacaoService.buscarPorClassificacao("JUNIOR");
            usuarioEncontrado.get().setClassificacao(classificacao);
            usuarioRepository.save(usuarioEncontrado.get());
            return classificacao;
        }

    }

}
