package co.solvers.apilearnlink.service.mediaUsuariosAtivosNoMes;

import co.solvers.apilearnlink.domain.views.mediaUsuariosAtivosNoMes.MediaUsuariosAtivosNoMes;
import co.solvers.apilearnlink.domain.views.mediaUsuariosAtivosNoMes.repository.MediaUsuariosAtivosNoMesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuariosAtivosNoMesService {
    private final MediaUsuariosAtivosNoMesRepository usuariosAtivosNoMesRepository;

    public List<MediaUsuariosAtivosNoMes> listagemUsuariosAtivosNoMes(){
        Integer mesAtual = LocalDate.now().getMonthValue();

        return usuariosAtivosNoMesRepository.findByMes(mesAtual);
    }

}
