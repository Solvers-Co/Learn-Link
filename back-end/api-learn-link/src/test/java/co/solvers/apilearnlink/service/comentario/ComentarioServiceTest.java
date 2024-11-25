package co.solvers.apilearnlink.service.comentario;

import co.solvers.apilearnlink.domain.comentario.Comentario;
import co.solvers.apilearnlink.domain.comentario.ComentarioStatus;
import co.solvers.apilearnlink.domain.comentario.repository.ComentarioRepository;
import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import co.solvers.apilearnlink.exception.NaoEncontradoException;
import co.solvers.apilearnlink.service.comentario.dto.ComentarioCriacaoDto;
import co.solvers.apilearnlink.service.comentario.dto.QuantidadeComentarioDiaListagemDto;
import co.solvers.apilearnlink.service.publicacao.PublicacaoService;
import co.solvers.apilearnlink.service.usuario.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
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
    @DisplayName("Testa o método comentar")
    void testComentar() {
        int idPublicacao = 1;
        ComentarioCriacaoDto comentarioCriacaoDto = new ComentarioCriacaoDto();
        comentarioCriacaoDto.setIdUsuario(1L);

        Usuario usuarioMock = new Usuario();
        usuarioMock.setId(1L);
        Publicacao publicacaoMock = new Publicacao();
        publicacaoMock.setId(idPublicacao);

        // Mockando os retornos dos métodos usuarioService.buscarPorId e publicacaoService.listarPorId
        Mockito.when(usuarioService.buscarPorId(comentarioCriacaoDto.getIdUsuario())).thenReturn(usuarioMock);
        Mockito.when(publicacaoService.listarPorId(idPublicacao)).thenReturn(publicacaoMock);

        // Chamando o método comentar
        Comentario comentarioMock = new Comentario();
        comentarioMock.setId(1);
        Mockito.when(comentarioRepository.save(Mockito.any(Comentario.class))).thenReturn(comentarioMock);
        Comentario comentarioRetornado = comentarioService.comentar(idPublicacao, comentarioCriacaoDto);

        // Verificando se o método usuarioService.buscarPorId foi chamado com os parâmetros corretos
        Mockito.verify(usuarioService, Mockito.times(1)).buscarPorId(comentarioCriacaoDto.getIdUsuario());

        // Verificando se o método publicacaoService.listarPorId foi chamado com os parâmetros corretos
        Mockito.verify(publicacaoService, Mockito.times(1)).listarPorId(idPublicacao);

        // Verificando se o método comentarioRepository.save foi chamado com o objeto Comentario correto
        Mockito.verify(comentarioRepository, Mockito.times(1)).save(Mockito.any(Comentario.class));

        // Verificando se o Comentario retornado é o esperado
        assertEquals(comentarioMock.getId(), comentarioRetornado.getId());
    }

    @Test
    @DisplayName("Testa o método comentar com usuário inexistente")
    void testBuscarPorIdComentarioExistente() {
        int id = 1;
        Comentario comentarioMock = new Comentario();
        comentarioMock.setId(id);
        comentarioMock.setStatus(ComentarioStatus.ATIVO);

        // Mockando o retorno do método findByIdAndStatus do comentarioRepository
        Mockito.when(comentarioRepository.findByIdAndStatus(id, ComentarioStatus.ATIVO)).thenReturn(Optional.of(comentarioMock));

        // Chamando o método buscarPorId
        Comentario comentarioRetornado = comentarioService.buscarPorId(id);

        // Verificando se o método findByIdAndStatus foi chamado com os parâmetros corretos
        Mockito.verify(comentarioRepository, Mockito.times(1)).findByIdAndStatus(id, ComentarioStatus.ATIVO);

        // Verificando se o Comentario retornado é o esperado
        assertEquals(comentarioMock.getId(), comentarioRetornado.getId());
    }

    @Test
    @DisplayName("Testa o método buscarPorId com comentário inexistente")
    void testBuscarPorIdComentarioInexistente() {
        int id = 1;

        // Mockando o retorno do método findByIdAndStatus do comentarioRepository para um Optional vazio
        Mockito.when(comentarioRepository.findByIdAndStatus(id, ComentarioStatus.ATIVO)).thenReturn(Optional.empty());

        // Verificando se uma exceção é lançada ao tentar buscar um comentário inexistente
        assertThrows(NaoEncontradoException.class, () -> {
            comentarioService.buscarPorId(id);
        });

        // Verificando se o método findByIdAndStatus foi chamado com os parâmetros corretos
        Mockito.verify(comentarioRepository, Mockito.times(1)).findByIdAndStatus(id, ComentarioStatus.ATIVO);
    }

    @Test
    @DisplayName("Testa o método editarComentario")
    void testEditarComentario() {
        int id = 1;
        String comentarioAlterar = "Novo comentário";

        Comentario comentarioMock = new Comentario();
        comentarioMock.setId(id);
        comentarioMock.setComentario("Comentário antigo");
        comentarioMock.setStatus(ComentarioStatus.ATIVO);

        // Mockando o retorno do método buscarPorId do comentarioService
        Mockito.when(comentarioRepository.findByIdAndStatus(id, ComentarioStatus.ATIVO)).thenReturn(Optional.of(comentarioMock));

        // Chamando o método editarComentario
        Comentario comentarioEditadoMock = new Comentario();
        comentarioEditadoMock.setId(id);
        comentarioEditadoMock.setComentario(comentarioAlterar);
        comentarioEditadoMock.setStatus(ComentarioStatus.ATIVO);
        Mockito.when(comentarioRepository.save(Mockito.any(Comentario.class))).thenReturn(comentarioEditadoMock);
        Comentario comentarioEditado = comentarioService.editarComentario(id, comentarioAlterar);

        // Verificando se o método findByIdAndStatus foi chamado com os parâmetros corretos
        Mockito.verify(comentarioRepository, Mockito.times(1)).findByIdAndStatus(id, ComentarioStatus.ATIVO);

        // Verificando se o método save foi chamado com o objeto Comentario correto
        Mockito.verify(comentarioRepository, Mockito.times(1)).save(Mockito.any(Comentario.class));

        // Verificando se o Comentario retornado é o esperado
        assertEquals(comentarioEditadoMock.getId(), comentarioEditado.getId());
        assertEquals(comentarioEditadoMock.getComentario(), comentarioEditado.getComentario());
    }

    @Test
    @DisplayName("Testa o método editarComentario com comentário existente")
    void testDeletarComentarioExistente() {
        int id = 1;

        Comentario comentarioMock = new Comentario();
        comentarioMock.setId(id);
        comentarioMock.setStatus(ComentarioStatus.ATIVO);

        // Mockando o retorno do método buscarPorId do comentarioService
        Mockito.when(comentarioRepository.findByIdAndStatus(id, ComentarioStatus.ATIVO)).thenReturn(Optional.of(comentarioMock));

        // Chamando o método deletar
        comentarioService.deletar(id);

        // Verificando se o status do comentário foi alterado para EXCLUIDO
        assertEquals(ComentarioStatus.EXCLUIDO, comentarioMock.getStatus());

        // Verificando se o método save foi chamado com o objeto Comentario correto
        Mockito.verify(comentarioRepository, Mockito.times(1)).save(comentarioMock);
    }

    @Test
    @DisplayName("Testa o método deletar com comentário inexistente")
    void testDeletarComentarioInexistente() {
        int id = 1;

        // Mockando o retorno do método findByIdAndStatus do comentarioRepository para um Optional vazio
        Mockito.when(comentarioRepository.findByIdAndStatus(id, ComentarioStatus.ATIVO)).thenReturn(Optional.empty());

        // Verificando se uma exceção é lançada ao tentar deletar um comentário inexistente
        assertThrows(NaoEncontradoException.class, () -> {
            comentarioService.deletar(id);
        });

        // Verificando se o método findByIdAndStatus foi chamado com os parâmetros corretos
        Mockito.verify(comentarioRepository, Mockito.times(1)).findByIdAndStatus(id, ComentarioStatus.ATIVO);
    }

    @Test
    @DisplayName("Testa a busca de quantidade de comentários por dia")
    void testBuscaQuantidadeDeComentariosPorDia() {
        int mes = 5;
        int ano = 2023;
        ComentarioStatus status = ComentarioStatus.ATIVO;

        QuantidadeComentarioDiaListagemDto dto1 = new QuantidadeComentarioDiaListagemDto();
        dto1.setDataComentario(new Date(2023, 5, 1));
        dto1.setQuantidadeComentarios(10L);

        QuantidadeComentarioDiaListagemDto dto2 = new QuantidadeComentarioDiaListagemDto();
        dto2.setDataComentario(new Date(2023, 5, 2));
        dto2.setQuantidadeComentarios(20L);

        List<QuantidadeComentarioDiaListagemDto> listaMock = Arrays.asList(dto1, dto2);

        // Mocking the return of the buscaQuantidadeDeComentariosPorDia method from comentarioRepository
        Mockito.when(comentarioRepository.buscaQuantidadeDeComentariosPorDia(mes, ano, status)).thenReturn(listaMock);

        // Calling the buscaQuantidadeDeComentariosPorDia method
        List<QuantidadeComentarioDiaListagemDto> resultado = comentarioService.buscaQuantidadeDeComentariosPorDia(mes, ano);

        // Verifying if the buscaQuantidadeDeComentariosPorDia method was called with the correct parameters
        Mockito.verify(comentarioRepository, Mockito.times(1)).buscaQuantidadeDeComentariosPorDia(mes, ano, status);

        // Verifying if the return of the method is as expected
        assertEquals(listaMock.size(), resultado.size());
        assertEquals(listaMock.get(0).getDataComentario(), resultado.get(0).getDataComentario());
        assertEquals(listaMock.get(0).getQuantidadeComentarios(), resultado.get(0).getQuantidadeComentarios());
        assertEquals(listaMock.get(1).getDataComentario(), resultado.get(1).getDataComentario());
        assertEquals(listaMock.get(1).getQuantidadeComentarios(), resultado.get(1).getQuantidadeComentarios());
    }

    @Test
    @DisplayName("Deve listar todos os comentários de uma publicação específica")
    void testListarPorPublicacao() {
        int idPublicacao = 1;

        Publicacao publicacaoMock = new Publicacao();
        publicacaoMock.setId(idPublicacao);

        Comentario comentario1 = new Comentario();
        comentario1.setId(1);
        comentario1.setComentario("Comentário 1");
        comentario1.setStatus(ComentarioStatus.ATIVO);

        Comentario comentario2 = new Comentario();
        comentario2.setId(2);
        comentario2.setComentario("Comentário 2");
        comentario2.setStatus(ComentarioStatus.ATIVO);

        List<Comentario> comentariosMock = Arrays.asList(comentario1, comentario2);

        // Mockando o retorno do método listarPorId do publicacaoService
        Mockito.when(publicacaoService.listarPorId(idPublicacao)).thenReturn(publicacaoMock);

        // Mockando o retorno do método findByPublicacaoAndStatus do comentarioRepository
        Mockito.when(comentarioRepository.findByPublicacaoAndStatus(publicacaoMock, ComentarioStatus.ATIVO)).thenReturn(comentariosMock);

        // Chamando o método listarPorPublicacao
        List<Comentario> resultado = comentarioService.listarPorPublicacao(idPublicacao);

        // Verificando se o método listarPorId foi chamado com os parâmetros corretos
        Mockito.verify(publicacaoService, Mockito.times(1)).listarPorId(idPublicacao);

        // Verificando se o método findByPublicacaoAndStatus foi chamado com os parâmetros corretos
        Mockito.verify(comentarioRepository, Mockito.times(1)).findByPublicacaoAndStatus(publicacaoMock, ComentarioStatus.ATIVO);

        // Verificando se o retorno do método é o esperado
        assertEquals(comentariosMock.size(), resultado.size());
        assertEquals(comentariosMock.get(0).getId(), resultado.get(0).getId());
        assertEquals(comentariosMock.get(0).getComentario(), resultado.get(0).getComentario());
        assertEquals(comentariosMock.get(1).getId(), resultado.get(1).getId());
        assertEquals(comentariosMock.get(1).getComentario(), resultado.get(1).getComentario());
    }

    @Test
    @DisplayName("Deve listar todos os comentários de uma publicação específica de forma paginada")
    void testListarPorPublicacaoPaginado() {
        int idPublicacao = 1;
        Pageable pageable = PageRequest.of(0, 10);

        Publicacao publicacaoMock = new Publicacao();
        publicacaoMock.setId(idPublicacao);

        Comentario comentario1 = new Comentario();
        comentario1.setId(1);
        comentario1.setComentario("Comentário 1");
        comentario1.setStatus(ComentarioStatus.ATIVO);

        Comentario comentario2 = new Comentario();
        comentario2.setId(2);
        comentario2.setComentario("Comentário 2");
        comentario2.setStatus(ComentarioStatus.ATIVO);

        List<Comentario> comentariosMock = Arrays.asList(comentario1, comentario2);
        Page<Comentario> pageMock = new PageImpl<>(comentariosMock, pageable, comentariosMock.size());

        // Mockando o retorno do método listarPorId do publicacaoService
        Mockito.when(publicacaoService.listarPorId(idPublicacao)).thenReturn(publicacaoMock);

        // Mockando o retorno do método findByPublicacaoAndStatus do comentarioRepository
        Mockito.when(comentarioRepository.findByPublicacaoAndStatus(publicacaoMock, pageable, ComentarioStatus.ATIVO)).thenReturn(pageMock);

        // Chamando o método listarPorPublicacaoPaginado
        Page<Comentario> resultado = comentarioService.listarPorPublicacaoPaginado(idPublicacao, pageable);

        // Verificando se o método listarPorId foi chamado com os parâmetros corretos
        Mockito.verify(publicacaoService, Mockito.times(1)).listarPorId(idPublicacao);

        // Verificando se o método findByPublicacaoAndStatus foi chamado com os parâmetros corretos
        Mockito.verify(comentarioRepository, Mockito.times(1)).findByPublicacaoAndStatus(publicacaoMock, pageable, ComentarioStatus.ATIVO);

        // Verificando se o retorno do método é o esperado
        assertEquals(pageMock.getTotalElements(), resultado.getTotalElements());
        assertEquals(pageMock.getContent().get(0).getId(), resultado.getContent().get(0).getId());
        assertEquals(pageMock.getContent().get(0).getComentario(), resultado.getContent().get(0).getComentario());
        assertEquals(pageMock.getContent().get(1).getId(), resultado.getContent().get(1).getId());
        assertEquals(pageMock.getContent().get(1).getComentario(), resultado.getContent().get(1).getComentario());
    }

}
