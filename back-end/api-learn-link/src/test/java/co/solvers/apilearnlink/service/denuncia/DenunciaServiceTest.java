package co.solvers.apilearnlink.service.denuncia;

import co.solvers.apilearnlink.domain.comentario.Comentario;
import co.solvers.apilearnlink.domain.comentario.ComentarioStatus;
import co.solvers.apilearnlink.domain.denuncia.Denuncia;
import co.solvers.apilearnlink.domain.denuncia.repository.DenunciaRespository;
import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import co.solvers.apilearnlink.domain.publicacao.PublicacaoStatus;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import co.solvers.apilearnlink.domain.views.comentariosDenunciados.ComentariosDenunciados;
import co.solvers.apilearnlink.domain.views.publicacoesDenunciadas.PublicacoesDenunciadas;
import co.solvers.apilearnlink.exception.NaoEncontradoException;
import co.solvers.apilearnlink.service.comentario.ComentarioService;
import co.solvers.apilearnlink.service.denuncia.dto.DenunciaComentarioCriarDto;
import co.solvers.apilearnlink.service.denuncia.dto.DenunciaPublicacaoCriarDto;
import co.solvers.apilearnlink.service.publicacao.PublicacaoService;
import co.solvers.apilearnlink.service.usuario.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DenunciaServiceTest {

    @Mock
    private DenunciaRespository denunciaRespository;

    @Mock
    private PublicacaoService publicacaoService;

    @Mock
    private ComentarioService comentarioService;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private DenunciaService denunciaService;

    private Publicacao publicacao;
    private Comentario comentario;
    private Usuario usuario;
    private DenunciaPublicacaoCriarDto denunciaPublicacaoCriarDto;
    private DenunciaComentarioCriarDto denunciaComentarioCriarDto;


    @BeforeEach
    void setUp() {
        publicacao = new Publicacao();
        publicacao.setId(1);
        usuario = new Usuario();
        usuario.setId(1L);
        publicacao.setUsuario(usuario);

        denunciaPublicacaoCriarDto = new DenunciaPublicacaoCriarDto();
        denunciaPublicacaoCriarDto.setIdUsuario(2L);

        comentario = new Comentario();
        comentario.setId(1);
        usuario = new Usuario();
        usuario.setId(1L);
        comentario.setUsuario(usuario);

        denunciaComentarioCriarDto = new DenunciaComentarioCriarDto();
        denunciaComentarioCriarDto.setIdUsuario(2L);

    }

    @Test
    @DisplayName("Denunciar publicação com sucesso")
    void denunciarPublicacao_Success() {
        Usuario denunciante = new Usuario();
        denunciante.setId(2L);

        when(publicacaoService.listarPorId(1)).thenReturn(publicacao);
        when(usuarioService.buscarPorId(2L)).thenReturn(denunciante);
        when(denunciaRespository.findByPublicacaoAndUsuario(publicacao, denunciante)).thenReturn(Optional.empty());
        when(denunciaRespository.save(any(Denuncia.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Denuncia denuncia = denunciaService.denunciarPublicacao(1, denunciaPublicacaoCriarDto);

        assertNotNull(denuncia);
        assertEquals(publicacao, denuncia.getPublicacao());
        assertEquals(denunciante, denuncia.getUsuario());
    }

    @Test
    @DisplayName("Denunciar propria publicação")
    void denunciarPublicacao_UsuarioDenunciandoPropriaPublicacao() {
        denunciaPublicacaoCriarDto.setIdUsuario(1L);

        when(publicacaoService.listarPorId(1)).thenReturn(publicacao);
        when(usuarioService.buscarPorId(1L)).thenReturn(usuario);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            denunciaService.denunciarPublicacao(1, denunciaPublicacaoCriarDto);
        });

        assertEquals("400 BAD_REQUEST \"O usuário não pode denúnciar sua própria publicação\"", exception.getMessage());
    }

    @Test
    @DisplayName("Denunciar publicação já denunciada")
    void denunciarPublicacao_UsuarioJaDenunciouPublicacao() {
        Usuario denunciante = new Usuario();
        denunciante.setId(2L);

        when(publicacaoService.listarPorId(1)).thenReturn(publicacao);
        when(usuarioService.buscarPorId(2L)).thenReturn(denunciante);
        when(denunciaRespository.findByPublicacaoAndUsuario(publicacao, denunciante)).thenReturn(Optional.of(new Denuncia()));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            denunciaService.denunciarPublicacao(1, denunciaPublicacaoCriarDto);
        });

        assertEquals("400 BAD_REQUEST \"Esse usuário já denunciou essa publicação\"", exception.getMessage());
    }

    @Test
    @DisplayName("Denunciar comentário com sucesso")
    void denunciarComentario_Success() {
        Usuario denunciante = new Usuario();
        denunciante.setId(2L);

        when(comentarioService.buscarPorId(1)).thenReturn(comentario);
        when(usuarioService.buscarPorId(2L)).thenReturn(denunciante);
        when(denunciaRespository.findByComentarioAndUsuario(comentario, denunciante)).thenReturn(Optional.empty());
        when(denunciaRespository.save(any(Denuncia.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Denuncia denuncia = denunciaService.denunciarComentario(1, denunciaComentarioCriarDto);

        assertNotNull(denuncia);
        assertEquals(comentario, denuncia.getComentario());
        assertEquals(denunciante, denuncia.getUsuario());
    }

    @Test
    @DisplayName("Denunciar próprio comentário")
    void denunciarComentario_UsuarioDenunciandoProprioComentario() {
        denunciaComentarioCriarDto.setIdUsuario(1L);

        when(comentarioService.buscarPorId(1)).thenReturn(comentario);
        when(usuarioService.buscarPorId(1L)).thenReturn(usuario);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            denunciaService.denunciarComentario(1, denunciaComentarioCriarDto);
        });

        assertEquals("400 BAD_REQUEST \"O usuário não pode denúnciar seu próprio comentário\"", exception.getMessage());
    }

    @Test
    @DisplayName("Denunciar comentário já denunciado")
    void denunciarComentario_UsuarioJaDenunciouComentario() {
        Usuario denunciante = new Usuario();
        denunciante.setId(2L);

        when(comentarioService.buscarPorId(1)).thenReturn(comentario);
        when(usuarioService.buscarPorId(2L)).thenReturn(denunciante);
        when(denunciaRespository.findByComentarioAndUsuario(comentario, denunciante)).thenReturn(Optional.of(new Denuncia()));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            denunciaService.denunciarComentario(1, denunciaComentarioCriarDto);
        });

        assertEquals("400 BAD_REQUEST \"Esse usuário já denunciou esse comentário\"", exception.getMessage());
    }

    @Test
    @DisplayName("Usuário já denunciou a publicação")
    void verificaSeUsuarioJaDenunciouPublicacao_UsuarioJaDenunciou() {
        when(denunciaRespository.findByPublicacaoAndUsuario(publicacao, usuario)).thenReturn(Optional.of(new Denuncia()));

        Boolean resultado = denunciaService.verificaSeUsuarioJaDenunciouPublicacao(publicacao, usuario);

        assertTrue(resultado);
    }

    @Test
    @DisplayName("Usuário ainda não denunciou a publicação")
    void verificaSeUsuarioJaDenunciouPublicacao_UsuarioNaoDenunciou() {
        when(denunciaRespository.findByPublicacaoAndUsuario(publicacao, usuario)).thenReturn(Optional.empty());

        Boolean resultado = denunciaService.verificaSeUsuarioJaDenunciouPublicacao(publicacao, usuario);

        assertFalse(resultado);
    }

    @Test
    @DisplayName("Usuário já denunciou o comentário")
    void verificaSeUsuarioJaDenunciouComentario_UsuarioJaDenunciou() {
        when(denunciaRespository.findByComentarioAndUsuario(comentario, usuario)).thenReturn(Optional.of(new Denuncia()));

        Boolean resultado = denunciaService.verificaSeUsuarioJaDenunciouComentario(comentario, usuario);

        assertTrue(resultado);
    }

    @Test
    @DisplayName("Usuário ainda não denunciou o comentário")
    void verificaSeUsuarioJaDenunciouComentario_UsuarioNaoDenunciou() {
        when(denunciaRespository.findByComentarioAndUsuario(comentario, usuario)).thenReturn(Optional.empty());

        Boolean resultado = denunciaService.verificaSeUsuarioJaDenunciouComentario(comentario, usuario);

        assertFalse(resultado);
    }

    @Test
    @DisplayName("Buscar publicações denunciadas com sucesso")
    void buscaPublicacoesDenunciadas_Sucesso() {
        List<PublicacoesDenunciadas> publicacoesDenunciadas = List.of(
                new PublicacoesDenunciadas(new Publicacao(), 5L),
                new PublicacoesDenunciadas(new Publicacao(), 3L)
        );

        when(denunciaRespository.buscaPublicacoesDenunciadas(PublicacaoStatus.ATIVO)).thenReturn(publicacoesDenunciadas);

        List<PublicacoesDenunciadas> resultado = denunciaService.buscaPublicacoesDenunciadas();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(5, resultado.get(0).getQuantidadeDenuncias());
        assertEquals(3, resultado.get(1).getQuantidadeDenuncias());
    }

    @Test
    @DisplayName("Buscar publicações denunciadas sem resultados")
    void buscaPublicacoesDenunciadas_SemResultados() {
        when(denunciaRespository.buscaPublicacoesDenunciadas(PublicacaoStatus.ATIVO)).thenReturn(List.of());

        List<PublicacoesDenunciadas> resultado = denunciaService.buscaPublicacoesDenunciadas();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Buscar comentários denunciados com sucesso")
    void buscaComentariosDenunciados_Sucesso() {
        List<ComentariosDenunciados> comentariosDenunciados = List.of(
                new ComentariosDenunciados(new Comentario(), 5L),
                new ComentariosDenunciados(new Comentario(), 3L)
        );

        when(denunciaRespository.buscaComentariosDenunciados(ComentarioStatus.ATIVO)).thenReturn(comentariosDenunciados);

        List<ComentariosDenunciados> resultado = denunciaService.buscaComentariosDenunciados();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(5, resultado.get(0).getQuantidadeDenuncias());
        assertEquals(3, resultado.get(1).getQuantidadeDenuncias());
    }

    @Test
    @DisplayName("Buscar comentários denunciados sem resultados")
    void buscaComentariosDenunciados_SemResultados() {
        when(denunciaRespository.buscaComentariosDenunciados(ComentarioStatus.ATIVO)).thenReturn(List.of());

        List<ComentariosDenunciados> resultado = denunciaService.buscaComentariosDenunciados();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Remover denúncias de publicação com sucesso")
    void removerDenunciasPublicacao_Sucesso() {
        when(publicacaoService.listarPorId(1)).thenReturn(publicacao);

        assertDoesNotThrow(() -> denunciaService.removerDenunciasPublicacao(1));

        verify(denunciaRespository, times(1)).deleteAllByPublicacao(publicacao);
    }

    @Test
    @DisplayName("Remover denúncias de publicação não encontrada")
    void removerDenunciasPublicacao_PublicacaoNaoEncontrada() {
        when(publicacaoService.listarPorId(1)).thenThrow(new NaoEncontradoException("Publicação"));

        NaoEncontradoException exception = assertThrows(NaoEncontradoException.class, () -> {
            denunciaService.removerDenunciasPublicacao(1);
        });

        assertEquals("Publicação não encontrado!", exception.getMessage());
        verify(denunciaRespository, never()).deleteAllByPublicacao(any(Publicacao.class));
    }

    @Test
    @DisplayName("Remover denúncias de comentário com sucesso")
    void removerDenunciasComentario_Sucesso() {
        when(comentarioService.buscarPorId(1)).thenReturn(comentario);

        assertDoesNotThrow(() -> denunciaService.removerDenunciasComentario(1));

        verify(denunciaRespository, times(1)).deleteAllByComentario(comentario);
    }

    @Test
    @DisplayName("Remover denúncias de comentário não encontrado")
    void removerDenunciasComentario_ComentarioNaoEncontrado() {
        when(comentarioService.buscarPorId(1)).thenThrow(new NaoEncontradoException("Comentário"));

        NaoEncontradoException exception = assertThrows(NaoEncontradoException.class, () -> {
            denunciaService.removerDenunciasComentario(1);
        });

        assertEquals("Comentário não encontrado!", exception.getMessage());
        verify(denunciaRespository, never()).deleteAllByComentario(any(Comentario.class));
    }

}