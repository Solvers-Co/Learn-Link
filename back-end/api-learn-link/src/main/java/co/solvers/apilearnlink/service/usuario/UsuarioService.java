package co.solvers.apilearnlink.service.usuario;

import co.solvers.apilearnlink.api.configuration.security.jwt.GerenciadorTokenJwt;
import co.solvers.apilearnlink.domain.classificacao.Classificacao;
import co.solvers.apilearnlink.domain.endereco.Endereco;
import co.solvers.apilearnlink.domain.escolaridade.Escolaridade;
import co.solvers.apilearnlink.domain.registroLogin.RegistroLogin;
import co.solvers.apilearnlink.domain.tipostatus.TipoStatus;
import co.solvers.apilearnlink.domain.tipostatus.repository.TipoStatusRepository;
import co.solvers.apilearnlink.domain.tipousuario.TipoUsuario;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import co.solvers.apilearnlink.domain.usuario.repository.UsuarioRepository;
import co.solvers.apilearnlink.exception.ConflitoException;
import co.solvers.apilearnlink.exception.NaoEncontradoException;
import co.solvers.apilearnlink.fila.FilaObj;
import co.solvers.apilearnlink.service.classificacao.ClassificacaoService;
import co.solvers.apilearnlink.service.endereco.EnderecoService;
import co.solvers.apilearnlink.service.escolaridade.EscolaridadeService;
import co.solvers.apilearnlink.service.registrologin.RegistroLoginService;
import co.solvers.apilearnlink.service.tipoStatus.TipoStatusService;
import co.solvers.apilearnlink.service.tipousuario.escolaridade.TipoUsuarioService;
import co.solvers.apilearnlink.service.usuario.autenticacao.dto.UsuarioLoginDto;
import co.solvers.apilearnlink.service.usuario.autenticacao.dto.UsuarioTokenDto;
import co.solvers.apilearnlink.service.usuario.dto.UsuarioAceitacaoListagemDto;
import co.solvers.apilearnlink.service.usuario.dto.UsuarioListagemDto;
import co.solvers.apilearnlink.service.usuario.dto.UsuarioListagemRankingDto;
import co.solvers.apilearnlink.service.usuario.dto.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final RegistroLoginService registroLoginService;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final TipoStatusRepository tipoStatusRepository;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final AuthenticationManager authenticationManager;
    private final EscolaridadeService escolaridadeService;
    private final ClassificacaoService classificacaoService;
    private final EnderecoService enderecoService;
    private final TipoUsuarioService tipoUsuarioService;
    private final TipoStatusService tipoStatusService;

    public Usuario criar(Usuario usuario, Integer escolaridadeId) {

        verificaEmailExistente(usuario.getEmail());

        TipoStatus tipoStatus = tipoStatusService.buscarPorId(1);
        Classificacao classificacao = classificacaoService.buscarPorClassificacao("JUNIOR");
        TipoUsuario tipoUsuario = tipoUsuarioService.buscarPorTipoUsuario("COMUM");

        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());

        usuario.setSenha(senhaCriptografada);
        usuario.setTipoStatus(tipoStatus);
        usuario.setTipoUsuario(tipoUsuario);
        usuario.setClassificacao(classificacao);

        if(escolaridadeId != null) {
            Escolaridade escolaridade = escolaridadeService.buscarPorId(escolaridadeId);
            usuario.setEscolaridade(escolaridade);
        }

        if (usuario.getEndereco() != null) {
            Endereco endereco = enderecoService.salvar(usuario.getEndereco());
            usuario.setEndereco(endereco);
        }

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuariosTipoStatus(String status){
        TipoStatus tipoStatus = tipoStatusService.buscarPorStatus(status);

        List<Usuario> usuarios = usuarioRepository.findByTipoStatus(tipoStatus);

        FilaObj<Usuario> fila = new FilaObj<>(usuarios.size());

        for (Usuario usuario : usuarios){
            fila.insert(usuario);
        }

        for (int i = 0 ; i < fila.getTamanho() ; i++){
            usuarios.set(i, fila.poll());
        }

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

    public UsuarioTokenDto desconectar(Long id){
        final Usuario usuario = usuarioRepository.findById(id).get();

        if(usuario == null){
            throw new NaoEncontradoException("Usuário");
        }

        return UsuarioMapper.desconectar(usuario);
    }

    public Usuario atualizar(Long id, String senha) {

        verificaIdExistente(id);

        Optional<Usuario> usuario = usuarioRepository.findById(id);

        String senhaCriptografada = passwordEncoder.encode(senha);

        usuario.get().setSenha(senhaCriptografada);
        Usuario usuarioSenhaAlterada = usuarioRepository.save(usuario.get());

        return usuarioSenhaAlterada;
    }

    public Usuario alterarStatus(Long id, Integer idTipoStatus) {
        Usuario usuario = buscarPorId(id);

        TipoStatus tipoStatus = tipoStatusService.buscarPorId(idTipoStatus);

        if (usuario.getTipoStatus().getStatus().equals(tipoStatus.getStatus())) throw new ConflitoException("Tipo status");

        usuario.setTipoStatus(tipoStatus);

        return usuarioRepository.save(usuario);
    }


    public List<UsuarioListagemRankingDto> ranking(){

        List<UsuarioListagemRankingDto> usuarios = usuarioRepository.findRanking();

        if(usuarios.isEmpty()){
            throw new NaoEncontradoException("Usuario");
        }

        return usuarios;
    }


    // Verificações de existência e vazio

 /*   public void verificaUsuarioAtivo(int id){
        String status = usuarioRepository.findStatusById(id);

        if (status != "APROVADO"){
            throw new InvalidoException("Status de usuário");
        }
    }*/

    public void verificaEmailExistente(String email) {

//        usuarioRepository.findByEmail(email).orElseThrow(
//                () -> new ConflitoException("Email do usuário")
//        );

        Optional<Usuario> usuarioValidacaoEmailExistente = usuarioRepository.findByEmail(email);

        if (usuarioValidacaoEmailExistente.isPresent()) {
            throw new ConflitoException("Email do usuário");
        }
    }

    public void verificaIdExistente(Long id) {

        usuarioRepository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Usuário")
        );

//        Optional<Usuario> IdValidacaoExistente = usuarioRepository.findById(id);
//
//        if (IdValidacaoExistente.isEmpty()) {
//            throw new NaoEncontradoException("Usuário");
//        }
    }

    public List<UsuarioAceitacaoListagemDto> listagemDeUsuarios(){
        return usuarioRepository.findAllUsuarios();
    }



}
