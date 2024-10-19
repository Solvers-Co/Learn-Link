package co.solvers.apilearnlink.service.mediaUsuariosAtivosNoMes;

import co.solvers.apilearnlink.domain.views.mediaUsuariosAtivosNoMes.MediaUsuariosAtivosNoMes;
import co.solvers.apilearnlink.domain.views.mediaUsuariosAtivosNoMes.repository.MediaUsuariosAtivosNoMesRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class UsuariosAtivosNoMesServiceTest {

    @Mock
    private MediaUsuariosAtivosNoMesRepository usuariosAtivosNoMesRepository;

    @InjectMocks
    private UsuariosAtivosNoMesService usuariosAtivosNoMesService;

    @Test
    @DisplayName("Deve retornar a lista correta de usuários ativos no mês")
    void testListagemUsuariosAtivosNoMes() {
        int mesAtual = LocalDate.now().getMonthValue();

        MediaUsuariosAtivosNoMes usuario1 = new MediaUsuariosAtivosNoMes();
        usuario1.setAno(2023);
        usuario1.setMes(mesAtual);
        usuario1.setUsuariosAtivos(10);

        MediaUsuariosAtivosNoMes usuario2 = new MediaUsuariosAtivosNoMes();
        usuario2.setAno(2023);
        usuario2.setMes(mesAtual);
        usuario2.setUsuariosAtivos(20);

        List<MediaUsuariosAtivosNoMes> usuariosMock = Arrays.asList(usuario1, usuario2);

        Mockito.when(usuariosAtivosNoMesRepository.findByMes(mesAtual)).thenReturn(usuariosMock);

        List<MediaUsuariosAtivosNoMes> resultado = usuariosAtivosNoMesService.listagemUsuariosAtivosNoMes();

        Mockito.verify(usuariosAtivosNoMesRepository, Mockito.times(1)).findByMes(mesAtual);
        assertEquals(usuariosMock.size(), resultado.size());
        assertEquals(usuariosMock.get(0).getUsuariosAtivos(), resultado.get(0).getUsuariosAtivos());
        assertEquals(usuariosMock.get(1).getUsuariosAtivos(), resultado.get(1).getUsuariosAtivos());
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia quando não há usuários ativos no mês")
    void testListagemUsuariosAtivosNoMesVazia() {
        int mesAtual = LocalDate.now().getMonthValue();

        Mockito.when(usuariosAtivosNoMesRepository.findByMes(mesAtual)).thenReturn(Arrays.asList());

        List<MediaUsuariosAtivosNoMes> resultado = usuariosAtivosNoMesService.listagemUsuariosAtivosNoMes();

        Mockito.verify(usuariosAtivosNoMesRepository, Mockito.times(1)).findByMes(mesAtual);
        assertTrue(resultado.isEmpty());
    }
}