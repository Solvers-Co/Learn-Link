package co.solvers.apilearnlink.service.canal;

import co.solvers.apilearnlink.domain.canal.Canal;
import co.solvers.apilearnlink.domain.canal.repository.CanalRepository;
import co.solvers.apilearnlink.exception.NaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class CanalServiceTest {

    @Mock
    private CanalRepository canalRepository;

    @InjectMocks
    private CanalService canalService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarPorId_CanalExistente() {
        Canal canal = new Canal();
        canal.setId(1);
        canal.setNome("Canal Teste");

        when(canalRepository.findById(1)).thenReturn(Optional.of(canal));

        Canal resultado = canalService.buscarPorId(1);

        assertEquals(canal, resultado);
    }

    @Test
    public void testBuscarPorId_CanalNaoEncontrado() {
        when(canalRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> canalService.buscarPorId(1));
    }
}