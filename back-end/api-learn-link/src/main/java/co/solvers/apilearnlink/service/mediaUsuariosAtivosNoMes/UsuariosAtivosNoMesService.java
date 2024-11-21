package co.solvers.apilearnlink.service.mediaUsuariosAtivosNoMes;

import co.solvers.apilearnlink.domain.views.mediaUsuariosAtivosNoMes.repository.MediaUsuariosAtivosNoMesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsuariosAtivosNoMesService {
    private final MediaUsuariosAtivosNoMesRepository usuariosAtivosNoMesRepository;

    public Integer listagemUsuariosAtivosNoMes(Integer mes, Integer ano) {
        return usuariosAtivosNoMesRepository.findUsuariosAtivosNoMes(mes, ano);
    }

}
