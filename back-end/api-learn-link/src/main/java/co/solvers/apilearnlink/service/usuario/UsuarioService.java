package co.solvers.apilearnlink.service.usuario;

import co.solvers.apilearnlink.api.configuration.security.jwt.GerenciadorTokenJwt;
import co.solvers.apilearnlink.domain.classificacao.Classificacao;
import co.solvers.apilearnlink.domain.endereco.Endereco;
import co.solvers.apilearnlink.domain.especialidade.Especialidade;
import co.solvers.apilearnlink.domain.registroLogin.RegistroLogin;
import co.solvers.apilearnlink.domain.tipostatus.TipoStatus;
import co.solvers.apilearnlink.domain.tipostatus.repository.TipoStatusRepository;
import co.solvers.apilearnlink.domain.tipousuario.TipoUsuario;
import co.solvers.apilearnlink.domain.usuario.HashTableUsuario;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import co.solvers.apilearnlink.domain.usuario.repository.UsuarioRepository;
import co.solvers.apilearnlink.domain.views.ReacoesEmComentariosDoUsuario.QtdReacoesComentariosUsuarioView;
import co.solvers.apilearnlink.exception.ConflitoException;
import co.solvers.apilearnlink.exception.NaoEncontradoException;
import co.solvers.apilearnlink.fila.FilaObj;
import co.solvers.apilearnlink.service.classificacao.ClassificacaoService;
import co.solvers.apilearnlink.service.endereco.EnderecoService;
import co.solvers.apilearnlink.service.endereco.dto.EnderecoCriacaoDto;
import co.solvers.apilearnlink.service.endereco.dto.mapper.EnderecoMapper;
import co.solvers.apilearnlink.service.especialidade.EspecialidadeService;
import co.solvers.apilearnlink.service.reacoesEmComentariosDoUsuario.QtdReacoesComentariosUsuarioService;
import co.solvers.apilearnlink.service.registrologin.RegistroLoginService;
import co.solvers.apilearnlink.service.tipoStatus.TipoStatusService;
import co.solvers.apilearnlink.service.tipousuario.TipoUsuarioService;
import co.solvers.apilearnlink.service.usuario.autenticacao.dto.UsuarioLoginDto;
import co.solvers.apilearnlink.service.usuario.autenticacao.dto.UsuarioTokenDto;
import co.solvers.apilearnlink.service.usuario.dto.UsuarioAceitacaoListagemDto;
import co.solvers.apilearnlink.service.usuario.dto.UsuarioListagemRankingDto;
import co.solvers.apilearnlink.service.usuario.dto.mapper.UsuarioMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Usuario finalizarCadastro(Long idUsuario, Long idEspecialidade, @Valid EnderecoCriacaoDto enderecoCadastrar) {
        Usuario usuarioBd = buscarPorId(idUsuario);

        if (usuarioBd.getEndereco() != null && usuarioBd.getEspecialidade() != null){
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

        FilaObj<Usuario> fila = new FilaObj<>(usuarios.size());

        for (Usuario usuario : usuarios) {
            fila.insert(usuario);
        }

        for (int i = 0; i < fila.getTamanho(); i++) {
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

    private HashTableUsuario populaHashTable(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        HashTableUsuario usuariosHashTable = new HashTableUsuario(5);

        if (usuarios.isEmpty()) {
            return usuariosHashTable;
        }

        for (Usuario usuario : usuarios) {
            usuariosHashTable.insere(usuario);
        }

        return usuariosHashTable;
    }

    public Usuario buscarPorNomeHashTable(String nome) {

        HashTableUsuario usuarios = populaHashTable();

        if (usuarios.isEmpty()){
            throw new NaoEncontradoException("Usuario");
        }

        Usuario usuario = usuarios.busca(nome);

        if (usuario == null){
            throw new NaoEncontradoException("Usuario");
        } else {
            return usuario;
        }

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

    public Usuario alterarStatus(Long id, Integer idTipoStatus) {
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
    public Usuario classificarUsuario(Long id) {
        Optional<QtdReacoesComentariosUsuarioView> reacoes = qtdReacoesComentariosUsuarioService.listagemQtdReacoesComentarios(id);
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(id);

        int qtdPontosUsuario = reacoes.get().getReacoes() * reacoes.get().getPontuacao();
        if (qtdPontosUsuario >= 100){
            Classificacao classificacao = classificacaoService.buscarPorClassificacao("ESPECIALISTA");
            usuarioEncontrado.get().setClassificacao(classificacao);
        }else if (qtdPontosUsuario >= 60){
            Classificacao classificacao = classificacaoService.buscarPorClassificacao("SENIOR");
            usuarioEncontrado.get().setClassificacao(classificacao);
        }else if (qtdPontosUsuario >= 30){
            Classificacao classificacao = classificacaoService.buscarPorClassificacao("PLENO");
            usuarioEncontrado.get().setClassificacao(classificacao);
        }else {
            Classificacao classificacao = classificacaoService.buscarPorClassificacao("JUNIOR");
            usuarioEncontrado.get().setClassificacao(classificacao);
        }

        return usuarioRepository.save(usuarioEncontrado.get());
    }

//    public List<UsuarioAceitacaoListagemDto> listagemDeUsuariosAtivos(){
//        return usuarioRepository.findAllUsuariosAtivos();
//    }
//
//    public List<UsuarioAceitacaoListagemDto> listagemDeUsuariosPendentes(){
//        return usuarioRepository.findAllUsuariosPendentes();
//    }

//    public List<UsuarioAceitacaoListagemDto> listagemDeUsuariosNegados(){
//        return usuarioRepository.findAllUsuariosNegados();
//    }

    //    public List<UsuarioAceitacaoListagemDto> listagemDeUsuarios(){
//        return usuarioRepository.findAllUsuarios();
//    }

}
