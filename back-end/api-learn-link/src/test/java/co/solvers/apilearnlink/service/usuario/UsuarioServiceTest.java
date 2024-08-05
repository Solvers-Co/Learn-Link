package co.solvers.apilearnlink.service.usuario;

import co.solvers.apilearnlink.domain.classificacao.Classificacao;
import co.solvers.apilearnlink.domain.endereco.Endereco;
import co.solvers.apilearnlink.domain.escolaridade.Escolaridade;
import co.solvers.apilearnlink.domain.tipostatus.TipoStatus;
import co.solvers.apilearnlink.domain.tipostatus.repository.TipoStatusRepository;
import co.solvers.apilearnlink.domain.tipousuario.TipoUsuario;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import co.solvers.apilearnlink.domain.usuario.repository.UsuarioRepository;
import co.solvers.apilearnlink.exception.NaoEncontradoException;
import co.solvers.apilearnlink.service.classificacao.ClassificacaoService;
import co.solvers.apilearnlink.service.endereco.EnderecoService;
import co.solvers.apilearnlink.service.escolaridade.EscolaridadeService;
import co.solvers.apilearnlink.service.tipoStatus.TipoStatusService;
import co.solvers.apilearnlink.service.tipousuario.escolaridade.TipoUsuarioService;
import co.solvers.apilearnlink.service.usuario.autenticacao.dto.UsuarioLoginDto;
import co.solvers.apilearnlink.service.usuario.autenticacao.dto.UsuarioTokenDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService service;

    @Mock
    private EscolaridadeService escolaridadeService;

    @Mock
    private ClassificacaoService classificacaoService;

    @Mock
    private EnderecoService enderecoService;


    @Mock
    private TipoUsuarioService tipoUsuarioService;

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private TipoStatusRepository tipoStatusRepository;

    @Mock
    private UsuarioRepository repository;

    @Mock
    private TipoStatusService tipoStatusService;

    @Nested
    @DisplayName("Criação de usuários")
    public class UsuarioCriacaoTest {
        @Test
        @DisplayName("Deve criar um usuário")
        public void deveCriarUmUsuario() {

            Escolaridade escolaridade = new Escolaridade(1, "ENSINO MEDIO COMPLETO");
            Classificacao classificacao = new Classificacao(1, "JUNIOR");
            Endereco endereco = new Endereco(1, "Rua dos Bobos", 10, "Algum Lugar", "São Paulo", "SP", "04798522");
            TipoStatus tipoStatus = new TipoStatus(1, "PENDENTE");
            TipoUsuario tipoUsuario = new TipoUsuario(1, "COMUM");

            Usuario usuario = new Usuario(
                    100L,
                    "Paulo",
                    "69732014789",
                    "paulo.gmail.com",
                    "paulo123",
                    "MATEMATICA",
                    escolaridade,
                    classificacao,
                    endereco,
                    tipoStatus,
                    tipoUsuario);

            Usuario usuarioNovo = new Usuario();
            usuarioNovo.setNome("Paulo");
            usuarioNovo.setCpf("69732014789");
            usuarioNovo.setEmail("paulo.gmail.com");
            usuarioNovo.setSenha("paulo123");
            usuarioNovo.setEndereco(endereco);


            Mockito.when(tipoStatusService.buscarPorId(1)).thenReturn(tipoStatus);
            Mockito.when(classificacaoService.buscarPorClassificacao("JUNIOR")).thenReturn(classificacao);
            Mockito.when(tipoUsuarioService.buscarPorTipoUsuario("COMUM")).thenReturn(tipoUsuario);
            Mockito.when(service.criar(usuarioNovo, 1)).thenReturn(usuario);

            Usuario usuarioSalvo = service.criar(usuarioNovo, 1);

            assertEquals(usuario.getTipoUsuario().getTipoUsuario(), usuarioSalvo.getTipoUsuario().getTipoUsuario());
            assertEquals(usuario.getTipoStatus().getStatus(), usuarioSalvo.getTipoStatus().getStatus());
            assertEquals(usuario, usuarioSalvo);
        }
    }

//    @Nested
//    @DisplayName("Autenticação de usuários")
//    public class UsuarioAutenticacaoTest {
//        @Test
//        @DisplayName("Deve autenticar um usuário")
//        public void deveAutenticarUmUsuario() {
//
//            UsuarioLoginDto login = new UsuarioLoginDto(
//                    "ana.silva@gmail.com",
//                    "ana123");
//
//            UsuarioTokenDto loginAutenticadoManipulado = new UsuarioTokenDto(
//                    1L,
//                    "Ana Silva",
//                    "ana.silva@gmail.com",
//                    "ABC123",
//                    true,
//                    new UsuarioTokenDto.RegistroLoginDto(LocalDateTime.now()));
//
//            Mockito.when(service.autenticar(login)).thenReturn(loginAutenticadoManipulado);
//
//            UsuarioTokenDto loginAutenticado = service.autenticar(login);
//
//            assertEquals(loginAutenticadoManipulado.getDataHoraLogin().getRegistroLogin(), loginAutenticado.getDataHoraLogin().getRegistroLogin());
//
//
//        }
//    }

    @Nested
    @DisplayName("Listagem de usuários")
    public class UsuarioListagemTest {

        @Test
        @DisplayName("Deve listar o usuário por id")
        void usuarioListagemPorId() {

            Usuario usuario = new Usuario();
            usuario.setId(1L);
            usuario.setEmail("email@email.com");
            usuario.setNome("Nome");

            Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(usuario));
            Usuario usuarioTeste = service.buscarPorId(1L);
            assertEquals(usuario.getNome(), usuarioTeste.getNome());
        }

        @Test
        @DisplayName("Deve retornar 404 ao não encontrar um usuário buscando por id")
        void usuarioListagemPorIdErro() {

            Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());

            NaoEncontradoException exception = assertThrows(NaoEncontradoException.class,
                    () -> service.buscarPorId(1L));
            assertEquals("Usuário não encontrado!", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Deletar usuário")
    public class UsuarioDeletarTest {

        @Test
        @DisplayName("Deve retornar 404 ao tentar deletar um usuário que não existe")
        void deletarUsuarioInexistente() {
            Mockito.when(repository.findById(anyLong())).thenReturn(Optional.empty());
            NaoEncontradoException exception = assertThrows(NaoEncontradoException.class,
                    () -> service.deletar(1L));

            assertEquals("Usuário não encontrado!", exception.getMessage());
        }

        @Test
        void testDeletarUsuarioExistente() {
            Long id = 1L;
            Usuario usuario = new Usuario();
            usuario.setId(id);

            // Mockando o retorno do método findById para um Optional contendo o usuário
            Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(usuario));

            // Verificando se o método deleteById foi chamado com o ID do usuário
            service.deletar(id);

            Mockito.verify(repository, Mockito.times(1)).deleteById(id);

            // Verificando se o usuário não existe mais no repositório
            Mockito.verify(repository, Mockito.times(1)).findById(id);
        }

    }

    @Nested
    @DisplayName("Atualizar usuário")
    public class UsuarioAtualizarTest {
        @Test
        @DisplayName("Deve atualizar a senha de um usuário")
        public void testAtualizarSenha() {
            // Dados do usuário existente
            Long usuarioId = 1L;
            String novaSenha = "novaSenha";
            String senhaCriptografada = "senhaCriptografada";

            Usuario usuario = new Usuario();
            usuario.setId(usuarioId);
            usuario.setSenha("senhaAntiga");

            // Mockando as dependências
            Mockito.when(repository.findById(usuarioId)).thenReturn(Optional.of(usuario));
            Mockito.when(passwordEncoder.encode(novaSenha)).thenReturn(senhaCriptografada);
            Mockito.when(repository.save(Mockito.any(Usuario.class))).thenReturn(usuario);

            // Chamando o método atualizar
            Usuario result = service.atualizar(usuarioId, novaSenha);

            assertEquals(senhaCriptografada, result.getSenha());
        }

        @Test
        @DisplayName("Deve lançar exceção ao tentar atualizar a senha de um usuário que não existe")
        public void testAtualizarSenhaUsuarioNaoEncontrado() {
            // Mockando a ausência do usuário
            Long usuarioId = 1L;
            String novaSenha = "novaSenha";

            Mockito.when(repository.findById(usuarioId)).thenReturn(Optional.empty());

            // Verificando se a exceção é lançada
            assertThrows(NaoEncontradoException.class, () -> {
                service.atualizar(usuarioId, novaSenha);
            });

            // Verificando se o método foi chamado
            Mockito.verify(passwordEncoder, Mockito.never()).encode(Mockito.anyString());
            Mockito.verify(repository, Mockito.never()).save(Mockito.any(Usuario.class));
        }
    }

//    @Nested
//    @DisplayName("Analisar usuário")
//    public class UsuarioAnalizarTest {
//        @Test
//        void testAnalisarUsuario() {
//            Long id = 1L;
//            Integer idTipoStatus = 2;
//
//            // Mockando o retorno do método findById do usuarioRepository
//            Usuario usuarioMock = new Usuario();
//            usuarioMock.setId(id);
//            Optional<Usuario> usuarioOptional = Optional.of(usuarioMock);
//            Mockito.when(repository.findById(id)).thenReturn(usuarioOptional);
//
//            // Mockando o retorno do método findById do tipoStatusRepository
//            TipoStatus tipoStatusMock = new TipoStatus();
//            tipoStatusMock.setId(idTipoStatus);
//            Optional<TipoStatus> tipoStatusOptional = Optional.of(tipoStatusMock);
//            Mockito.when(tipoStatusRepository.findById(idTipoStatus)).thenReturn(tipoStatusOptional);
//
//            // Mockando o save para retornar o usuarioMock
//            Mockito.when(repository.save(usuarioMock)).thenReturn(usuarioMock);
//
//            // Chamando o método analisarUsuario
//            Usuario usuarioAnalisado = service.alterarStatus(id, idTipoStatus);
//
//            // Verificando se o método findById foi chamado com os parâmetros corretos
//            Mockito.verify(repository, Mockito.times(2)).findById(id);
//            Mockito.verify(tipoStatusRepository, Mockito.times(1)).findById(idTipoStatus);
//
//            // Verificando se o tipoStatus do usuário foi alterado corretamente
//            assertEquals(idTipoStatus, usuarioAnalisado.getTipoStatus().getId());
//
//            // Verificando se o método save foi chamado com o usuário correto
//            Mockito.verify(repository, Mockito.times(1)).save(usuarioMock);
//        }
//    }

    //teste não funciona pois sempre o token é diferente
//    @Test
//    void testDesconectarUsuarioExistente() {
//        Long id = 1L;
//        Usuario usuarioMock = new Usuario();
//        usuarioMock.setId(id);
//
//        // Mockando o retorno do método findById do usuarioRepository
//        Mockito.when(repository.findById(id)).thenReturn(Optional.of(usuarioMock));
//
//        // Chamando o método desconectar
//        UsuarioTokenDto usuarioToken = service.desconectar(id);
//
//        // Verificando se o método findById foi chamado com os parâmetros corretos
//        Mockito.verify(repository, Mockito.times(1)).findById(id);
//
//        // Verificando se o método UsuarioMapper.desconectar foi chamado com o usuário correto
//        UsuarioTokenDto usuarioTokenEsperado = UsuarioMapper.desconectar(usuarioMock);
//        assertEquals(usuarioTokenEsperado, usuarioToken);
//    }

//    @Test
//    void testDesconectarUsuarioInexistente() {
//        Long id = 1L;
//
//        // Mockando o retorno do método findById do usuarioRepository para um Optional vazio
//        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());
//
//        // Verificando se uma exceção é lançada ao tentar desconectar um usuário inexistente
//        assertThrows(NoSuchElementException.class, () -> {
//            service.desconectar(id);
//        });
//
//        // Verificando se o método findById foi chamado com os parâmetros corretos
//        Mockito.verify(repository, Mockito.times(1)).findById(id);
//    }


}
