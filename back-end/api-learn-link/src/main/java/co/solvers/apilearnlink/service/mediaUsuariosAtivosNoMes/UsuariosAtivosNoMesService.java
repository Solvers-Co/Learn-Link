package co.solvers.apilearnlink.service.mediaUsuariosAtivosNoMes;

import co.solvers.apilearnlink.domain.views.mediaUsuariosAtivosNoMes.repository.MediaUsuariosAtivosNoMesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsuariosAtivosNoMesService {
    private final MediaUsuariosAtivosNoMesRepository usuariosAtivosNoMesRepository;

    public Integer listagemUsuariosAtivosNoMes(Integer mes, Integer ano) {
        Integer usuariosAtivosNoMes = usuariosAtivosNoMesRepository.findUsuariosAtivosNoMes(mes, ano);
        return usuariosAtivosNoMes != null ? usuariosAtivosNoMes : 0;
    }

}
