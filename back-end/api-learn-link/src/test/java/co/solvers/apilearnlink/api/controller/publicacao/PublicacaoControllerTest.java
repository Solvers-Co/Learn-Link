package co.solvers.apilearnlink.api.controller.publicacao;

import co.solvers.apilearnlink.domain.canal.Canal;
import co.solvers.apilearnlink.domain.comentario.Comentario;
import co.solvers.apilearnlink.domain.publicacao.PublicacaoStatus;
import co.solvers.apilearnlink.domain.tipopublicacao.TipoPublicacao;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import co.solvers.apilearnlink.service.publicacao.PublicacaoService;
import co.solvers.apilearnlink.service.publicacao.dto.PublicacaoCriacaoRequestDto;
import co.solvers.apilearnlink.service.publicacao.dto.PublicacaoListagemResponseDto;
import co.solvers.apilearnlink.service.publicacao.dto.mapper.PublicacaoMapper;
import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class PublicacaoControllerTest {

    @Mock
    private PublicacaoService publicacaoService;

    @InjectMocks
    private PublicacaoController publicacaoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve criar uma publicação com sucesso")
    void testPublicarComSucesso() {
        PublicacaoCriacaoRequestDto novaPublicacao = new PublicacaoCriacaoRequestDto();
        novaPublicacao.setConteudo("Quem descobriu o Brasil?");
        novaPublicacao.setIdTipoPublicacao(1);
        novaPublicacao.setIdUsuario(1L);
        novaPublicacao.setIdCanal(4);

        Publicacao publicacao = new Publicacao();
        publicacao.setId(1);
        publicacao.setConteudo("Quem descobriu o Brasil?");
        publicacao.setTipoPublicacao(new TipoPublicacao() {{ setId(1); }});
        publicacao.setUsuario(new Usuario() {{ setId(1L); }});
        publicacao.setCanal(new Canal() {{ setId(4); }});
        publicacao.setStatus(PublicacaoStatus.ATIVO);

        Comentario comentario = new Comentario();
        comentario.setId(1);
        comentario.setComentario("Primeiro comentário");
        comentario.setPublicacao(publicacao);

        publicacao.setComentarios(List.of(comentario));

        when(publicacaoService.criarPublicacao(novaPublicacao)).thenReturn(publicacao);

        PublicacaoListagemResponseDto dto = PublicacaoMapper.toDto(publicacao);
        ResponseEntity<PublicacaoListagemResponseDto> response = publicacaoController.publicar(novaPublicacao);

        assertEquals(201, response.getStatusCodeValue());

        // Usando AssertJ para comparar os objetos recursivamente
        assertThat(response.getBody())
                .usingRecursiveComparison()
                .isEqualTo(dto);
    }

    @Test
    @DisplayName("Deve retornar erro ao criar uma publicação com tipo inválido")
    void testPublicarComTipoInvalido() {
        PublicacaoCriacaoRequestDto novaPublicacao = new PublicacaoCriacaoRequestDto();

        when(publicacaoService.criarPublicacao(novaPublicacao)).thenThrow(new IllegalArgumentException("Tipo de publicação inválido"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            publicacaoController.publicar(novaPublicacao);
        });

        assertEquals("Tipo de publicação inválido", exception.getMessage());
    }

    @Test
    @DisplayName("Deve listar publicações com sucesso")
    void testListarPublicacoesComSucesso() {
        int page = 0;
        int size = 10;
        String sortDirection = "desc";
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dataHora"));

        Publicacao publicacao = new Publicacao();
        publicacao.setId(1);
        publicacao.setConteudo("Conteúdo da publicação");

        Comentario comentario = new Comentario();
        comentario.setId(1);
        comentario.setComentario("Primeiro comentário");
        comentario.setPublicacao(publicacao);

        publicacao.setComentarios(List.of(comentario));

        Page<Publicacao> publicacoesPage = new PageImpl<>(List.of(publicacao));
        when(publicacaoService.listarMaisRecentesPaginado(pageable)).thenReturn(publicacoesPage);

        ResponseEntity<Page<PublicacaoListagemResponseDto>> response = publicacaoController.listarPublicacoes(page, size, sortDirection);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, Objects.requireNonNull(response.getBody()).getTotalElements());
    }

    @Test
    @DisplayName("Deve retornar 204 quando não houver publicações")
    void testListarPublicacoesSemConteudo() {
        int page = 0;
        int size = 10;
        String sortDirection = "desc";
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dataHora"));

        Page<Publicacao> publicacoesPage = new PageImpl<>(Collections.emptyList());
        when(publicacaoService.listarMaisRecentesPaginado(pageable)).thenReturn(publicacoesPage);

        ResponseEntity<Page<PublicacaoListagemResponseDto>> response = publicacaoController.listarPublicacoes(page, size, sortDirection);

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve listar publicações por canal com sucesso")
    void testListarPublicacoesPorCanalComSucesso() {
        int page = 0;
        int size = 10;
        String sortDirection = "desc";
        Long canalId = 1L;
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dataHora"));

        Publicacao publicacao = new Publicacao();
        publicacao.setId(1);
        publicacao.setConteudo("Conteúdo da publicação");

        Comentario comentario = new Comentario();
        comentario.setId(1);
        comentario.setComentario("Primeiro comentário");
        comentario.setPublicacao(publicacao);

        publicacao.setComentarios(List.of(comentario));

        Page<Publicacao> publicacoesPage = new PageImpl<>(List.of(publicacao));
        when(publicacaoService.listarPublicacoesPorCanal(canalId, page, size, sortDirection)).thenReturn(publicacoesPage);

        ResponseEntity<Page<PublicacaoListagemResponseDto>> response = publicacaoController.listarPublicacoesPorCanal(page, size, sortDirection, canalId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, Objects.requireNonNull(response.getBody()).getTotalElements());
    }

    @Test
    @DisplayName("Deve retornar 204 quando não houver publicações por canal")
    void testListarPublicacoesPorCanalSemConteudo() {
        int page = 0;
        int size = 10;
        String sortDirection = "desc";
        Long canalId = 1L;
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dataHora"));

        Page<Publicacao> publicacoesPage = new PageImpl<>(Collections.emptyList());
        when(publicacaoService.listarPublicacoesPorCanal(canalId, page, size, sortDirection)).thenReturn(publicacoesPage);

        ResponseEntity<Page<PublicacaoListagemResponseDto>> response = publicacaoController.listarPublicacoesPorCanal(page, size, sortDirection, canalId);

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve listar publicações por tipo com sucesso")
    void testListarPublicacoesTipoComSucesso() {
        String tipoPublicacao = "COMUM";

        Publicacao publicacao = new Publicacao();
        publicacao.setId(1);
        publicacao.setConteudo("Conteúdo da publicação");

        Comentario comentario = new Comentario();
        comentario.setId(1);
        comentario.setComentario("Primeiro comentário");
        comentario.setPublicacao(publicacao);

        publicacao.setComentarios(List.of(comentario));

        List<Publicacao> publicacoes = List.of(publicacao);
        when(publicacaoService.listarPorTipo(tipoPublicacao)).thenReturn(publicacoes);

        ResponseEntity<List<PublicacaoListagemResponseDto>> response = publicacaoController.listarPublicacoesTipo(tipoPublicacao);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    @DisplayName("Deve retornar 204 quando não houver publicações do tipo especificado")
    void testListarPublicacoesTipoSemConteudo() {
        String tipoPublicacao = "COMUM";

        when(publicacaoService.listarPorTipo(tipoPublicacao)).thenReturn(Collections.emptyList());

        ResponseEntity<List<PublicacaoListagemResponseDto>> response = publicacaoController.listarPublicacoesTipo(tipoPublicacao);

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve listar publicação por ID com sucesso")
    void testListarPublicacaoIDComSucesso() {
        int id = 1;
        Publicacao publicacao = new Publicacao();
        publicacao.setId(id);
        publicacao.setConteudo("Conteúdo da publicação");

        Comentario comentario = new Comentario();
        comentario.setId(1);
        comentario.setComentario("Primeiro comentário");
        comentario.setPublicacao(publicacao);

        publicacao.setComentarios(List.of(comentario));

        when(publicacaoService.listarPorId(id)).thenReturn(publicacao);

        ResponseEntity<PublicacaoListagemResponseDto> response = publicacaoController.listarPublicacaoID(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(id, Objects.requireNonNull(response.getBody()).getId());
    }

    @Test
    @DisplayName("Deve editar conteúdo com sucesso")
    void testEditarConteudoComSucesso() {
        int id = 1;
        String novoConteudo = "Novo conteúdo";
        String novoCanal = "Novo Canal";
        Publicacao publicacao = new Publicacao();
        publicacao.setId(id);
        publicacao.setConteudo(novoConteudo);

        Comentario comentario = new Comentario();
        comentario.setId(1);
        comentario.setComentario("Primeiro comentário");
        comentario.setPublicacao(publicacao);

        publicacao.setComentarios(List.of(comentario));

        when(publicacaoService.editarConteudo(id, novoConteudo, novoCanal)).thenReturn(publicacao);

        ResponseEntity<PublicacaoListagemResponseDto> response = publicacaoController.editarConteudo(id, novoConteudo, novoCanal);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(novoConteudo, Objects.requireNonNull(response.getBody()).getConteudo());
    }


}