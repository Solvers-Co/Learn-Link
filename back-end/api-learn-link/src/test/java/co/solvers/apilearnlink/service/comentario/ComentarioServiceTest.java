package co.solvers.apilearnlink.service.comentario;

import co.solvers.apilearnlink.domain.comentario.Comentario;
import co.solvers.apilearnlink.domain.comentario.repository.ComentarioRepository;
import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import co.solvers.apilearnlink.exception.NaoEncontradoException;
import co.solvers.apilearnlink.service.comentario.dto.ComentarioCriacaoDto;
import co.solvers.apilearnlink.service.publicacao.PublicacaoService;
import co.solvers.apilearnlink.service.usuario.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ComentarioServiceTest {

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private PublicacaoService publicacaoService;

    @Mock
    private ComentarioRepository comentarioRepository;

    @InjectMocks
    private ComentarioService comentarioService;

    @Test
    void testComentar() {
        int idPublicacao = 1;
        ComentarioCriacaoDto comentarioCriacaoDto = new ComentarioCriacaoDto();
        comentarioCriacaoDto.setIdUsuario(1L);

        Usuario usuarioMock = new Usuario();
        usuarioMock.setId(1L);
        Publicacao publicacaoMock = new Publicacao();
        publicacaoMock.setId(idPublicacao);

        // Mockando os retornos dos métodos usuarioService.listar e publicacaoService.listarPorId
        Mockito.when(usuarioService.buscarPorId(comentarioCriacaoDto.getIdUsuario())).thenReturn(usuarioMock);
        Mockito.when(publicacaoService.listarPorId(idPublicacao)).thenReturn(publicacaoMock);

        // Chamando o método comentar
        Comentario comentarioMock = new Comentario();
        comentarioMock.setId(1);
        Mockito.when(comentarioRepository.save(Mockito.any(Comentario.class))).thenReturn(comentarioMock);
        Comentario comentarioRetornado = comentarioService.comentar(idPublicacao, comentarioCriacaoDto);

        // Verificando se o método usuarioService.listar foi chamado com os parâmetros corretos
        Mockito.verify(usuarioService, Mockito.times(1)).buscarPorId(comentarioCriacaoDto.getIdUsuario());

        // Verificando se o método publicacaoService.listarPorId foi chamado com os parâmetros corretos
        Mockito.verify(publicacaoService, Mockito.times(1)).listarPorId(idPublicacao);

        // Verificando se o método comentarioRepository.save foi chamado com o objeto Comentario correto
        Mockito.verify(comentarioRepository, Mockito.times(1)).save(Mockito.any(Comentario.class));

        // Verificando se o Comentario retornado é o esperado
        assertEquals(comentarioMock.getId(), comentarioRetornado.getId());
    }

    @Test
    void testBuscarPorIdComentarioExistente() {
        int id = 1;
        Comentario comentarioMock = new Comentario();
        comentarioMock.setId(id);

        // Mockando o retorno do método findById do comentarioRepository
        Mockito.when(comentarioRepository.findById(id)).thenReturn(Optional.of(comentarioMock));

        // Chamando o método buscarPorId
        Comentario comentarioRetornado = comentarioService.buscarPorId(id);

        // Verificando se o método findById foi chamado com os parâmetros corretos
        Mockito.verify(comentarioRepository, Mockito.times(1)).findById(id);

        // Verificando se o Comentario retornado é o esperado
        assertEquals(comentarioMock.getId(), comentarioRetornado.getId());
    }

    @Test
    void testBuscarPorIdComentarioInexistente() {
        int id = 1;

        // Mockando o retorno do método findById do comentarioRepository para um Optional vazio
        Mockito.when(comentarioRepository.findById(id)).thenReturn(Optional.empty());

        // Verificando se uma exceção é lançada ao tentar buscar um comentário inexistente
        assertThrows(NaoEncontradoException.class, () -> {
            comentarioService.buscarPorId(id);
        });

        // Verificando se o método findById foi chamado com os parâmetros corretos
        Mockito.verify(comentarioRepository, Mockito.times(1)).findById(id);
    }

    @Test
    void testEditarComentario() {
        int id = 1;
        String comentarioAlterar = "Novo comentário";

        Comentario comentarioMock = new Comentario();
        comentarioMock.setId(id);
        comentarioMock.setComentario("Comentário antigo");

        // Mockando o retorno do método buscarPorId do comentarioService
        Mockito.when(comentarioRepository.findById(id)).thenReturn(Optional.of(comentarioMock));

        // Chamando o método editarComentario
        Comentario comentarioEditadoMock = new Comentario();
        comentarioEditadoMock.setId(id);
        comentarioEditadoMock.setComentario(comentarioAlterar);
        Mockito.when(comentarioRepository.save(Mockito.any(Comentario.class))).thenReturn(comentarioEditadoMock);
        Comentario comentarioEditado = comentarioService.editarComentario(id, comentarioAlterar);

        // Verificando se o método findById foi chamado com os parâmetros corretos
        Mockito.verify(comentarioRepository, Mockito.times(1)).findById(id);

        // Verificando se o método save foi chamado com o objeto Comentario correto
        Mockito.verify(comentarioRepository, Mockito.times(1)).save(Mockito.any(Comentario.class));

        // Verificando se o Comentario retornado é o esperado
        assertEquals(comentarioEditadoMock.getId(), comentarioEditado.getId());
        assertEquals(comentarioEditadoMock.getComentario(), comentarioEditado.getComentario());
    }

}
