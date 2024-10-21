package co.solvers.apilearnlink.service.endereco;

import co.solvers.apilearnlink.domain.endereco.Endereco;
import co.solvers.apilearnlink.domain.endereco.repository.EnderecoRepository;
import co.solvers.apilearnlink.exception.NaoEncontradoException;
import org.junit.jupiter.api.DisplayName;
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
public class EnderecoServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private EnderecoService enderecoService;

    @Test
    @DisplayName("Deve retornar o endereço quando ele existe")
    void testBuscarPorIdEnderecoExistente() {
        Integer id = 1;
        Endereco enderecoMock = new Endereco();
        enderecoMock.setId(id);
        enderecoMock.setBairro("Centro");
        enderecoMock.setCidade("São Paulo");
        enderecoMock.setEstado("SP");
        enderecoMock.setCep("01000-000");

        Mockito.when(enderecoRepository.findById(id)).thenReturn(Optional.of(enderecoMock));

        Endereco enderecoRetornado = enderecoService.buscarPorId(id);

        Mockito.verify(enderecoRepository, Mockito.times(1)).findById(id);
        assertEquals(enderecoMock.getId(), enderecoRetornado.getId());
        assertEquals(enderecoMock.getBairro(), enderecoRetornado.getBairro());
        assertEquals(enderecoMock.getCidade(), enderecoRetornado.getCidade());
        assertEquals(enderecoMock.getEstado(), enderecoRetornado.getEstado());
        assertEquals(enderecoMock.getCep(), enderecoRetornado.getCep());
    }

    @Test
    @DisplayName("Deve lançar exceção quando o endereço não é encontrado")
    void testBuscarPorIdEnderecoInexistente() {
        Integer id = 1;

        Mockito.when(enderecoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> {
            enderecoService.buscarPorId(id);
        });

        Mockito.verify(enderecoRepository, Mockito.times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve salvar o endereço corretamente")
    void testSalvarEndereco() {
        Endereco enderecoMock = new Endereco();
        enderecoMock.setId(1);
        enderecoMock.setBairro("Centro");
        enderecoMock.setCidade("São Paulo");
        enderecoMock.setEstado("SP");
        enderecoMock.setCep("01000-000");

        Mockito.when(enderecoRepository.save(enderecoMock)).thenReturn(enderecoMock);

        Endereco enderecoSalvo = enderecoService.salvar(enderecoMock);

        Mockito.verify(enderecoRepository, Mockito.times(1)).save(enderecoMock);
        assertEquals(enderecoMock.getId(), enderecoSalvo.getId());
        assertEquals(enderecoMock.getBairro(), enderecoSalvo.getBairro());
        assertEquals(enderecoMock.getCidade(), enderecoSalvo.getCidade());
        assertEquals(enderecoMock.getEstado(), enderecoSalvo.getEstado());
        assertEquals(enderecoMock.getCep(), enderecoSalvo.getCep());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar salvar um endereço nulo")
    void testSalvarEnderecoNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            enderecoService.salvar(null);
        });

        Mockito.verify(enderecoRepository, Mockito.never()).save(Mockito.any(Endereco.class));
    }
}