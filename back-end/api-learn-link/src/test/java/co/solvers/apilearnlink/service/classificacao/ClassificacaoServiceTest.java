package co.solvers.apilearnlink.service.classificacao;

import co.solvers.apilearnlink.domain.classificacao.Classificacao;
import co.solvers.apilearnlink.domain.classificacao.repository.ClassificacaoRepository;
import co.solvers.apilearnlink.exception.NaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClassificacaoServiceTest {

    @Mock
    private ClassificacaoRepository classificacaoRepository;

    @InjectMocks
    private ClassificacaoService classificacaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarPorIdFound() {
        Classificacao classificacao = new Classificacao(1, "Test");
        when(classificacaoRepository.findById(1)).thenReturn(Optional.of(classificacao));

        Classificacao result = classificacaoService.buscarPorId(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Test", result.getClassificacao());
    }

    @Test
    void testBuscarPorIdNotFound() {
        when(classificacaoRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> classificacaoService.buscarPorId(1));
    }

    @Test
    void testBuscarPorClassificacaoFound() {
        Classificacao classificacao = new Classificacao(1, "Test");
        when(classificacaoRepository.findByClassificacao("Test")).thenReturn(Optional.of(classificacao));

        Classificacao result = classificacaoService.buscarPorClassificacao("Test");

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Test", result.getClassificacao());
    }

    @Test
    void testBuscarPorClassificacaoNotFound() {
        when(classificacaoRepository.findByClassificacao("Test")).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> classificacaoService.buscarPorClassificacao("Test"));
    }
}