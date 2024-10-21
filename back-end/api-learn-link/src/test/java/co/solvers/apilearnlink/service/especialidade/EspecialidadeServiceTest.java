package co.solvers.apilearnlink.service.especialidade;

import co.solvers.apilearnlink.domain.especialidade.Especialidade;
import co.solvers.apilearnlink.domain.especialidade.repository.EspecialidadeRepository;
import co.solvers.apilearnlink.exception.NaoEncontradoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class EspecialidadeServiceTest {

    @Mock
    private EspecialidadeRepository especialidadeRepository;

    @InjectMocks
    private EspecialidadeService especialidadeService;

    @Test
    @DisplayName("Deve retornar a especialidade quando ela existe")
    void testBuscarPorIdEspecialidadeExistente() {
        int id = 1;
        Especialidade especialidadeMock = new Especialidade();
        especialidadeMock.setId(id);
        especialidadeMock.setMateria("Matemática");

        Mockito.when(especialidadeRepository.findById(Long.valueOf(id))).thenReturn(Optional.of(especialidadeMock));

        Especialidade especialidadeRetornada = especialidadeService.buscarPorId(Long.valueOf(id));

        Mockito.verify(especialidadeRepository, Mockito.times(1)).findById(Long.valueOf(id));
        assertEquals(especialidadeMock.getId(), especialidadeRetornada.getId());
        assertEquals(especialidadeMock.getMateria(), especialidadeRetornada.getMateria());
    }

    @Test
    @DisplayName("Deve lançar exceção quando a especialidade não é encontrada")
    void testBuscarPorIdEspecialidadeInexistente() {
        Long id = 1L;

        Mockito.when(especialidadeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> {
            especialidadeService.buscarPorId(id);
        });

        Mockito.verify(especialidadeRepository, Mockito.times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve retornar a especialidade quando o nome existe")
    void testBuscarPorNomeEspecialidadeExistente() {
        String nome = "Matemática";
        Especialidade especialidadeMock = new Especialidade();
        especialidadeMock.setId(1);
        especialidadeMock.setMateria(nome);

        Mockito.when(especialidadeRepository.findByMateria(nome)).thenReturn(Optional.of(especialidadeMock));

        Especialidade especialidadeRetornada = especialidadeService.buscarPorNome(nome);

        Mockito.verify(especialidadeRepository, Mockito.times(1)).findByMateria(nome);
        assertEquals(especialidadeMock.getId(), especialidadeRetornada.getId());
        assertEquals(especialidadeMock.getMateria(), especialidadeRetornada.getMateria());
    }

    @Test
    @DisplayName("Deve lançar exceção quando o nome da especialidade não é encontrado")
    void testBuscarPorNomeEspecialidadeInexistente() {
        String nome = "Física";

        Mockito.when(especialidadeRepository.findByMateria(nome)).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> {
            especialidadeService.buscarPorNome(nome);
        });

        Mockito.verify(especialidadeRepository, Mockito.times(1)).findByMateria(nome);
    }

    @Test
    @DisplayName("Deve listar todas as especialidades")
    void testListarEspecialidades() {
        Especialidade especialidade1 = new Especialidade();
        especialidade1.setId(1);
        especialidade1.setMateria("Matemática");

        Especialidade especialidade2 = new Especialidade();
        especialidade2.setId(2);
        especialidade2.setMateria("Física");

        List<Especialidade> especialidadesMock = Arrays.asList(especialidade1, especialidade2);

        Mockito.when(especialidadeRepository.findAll()).thenReturn(especialidadesMock);

        List<Especialidade> especialidadesRetornadas = especialidadeService.listar();

        Mockito.verify(especialidadeRepository, Mockito.times(1)).findAll();
        assertEquals(especialidadesMock.size(), especialidadesRetornadas.size());
        assertEquals(especialidadesMock.get(0).getId(), especialidadesRetornadas.get(0).getId());
        assertEquals(especialidadesMock.get(0).getMateria(), especialidadesRetornadas.get(0).getMateria());
        assertEquals(especialidadesMock.get(1).getId(), especialidadesRetornadas.get(1).getId());
        assertEquals(especialidadesMock.get(1).getMateria(), especialidadesRetornadas.get(1).getMateria());
    }
}