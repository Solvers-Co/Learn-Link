package co.solvers.apilearnlink.domain.views.mediaUsuariosAtivosNoMes.repository;

import co.solvers.apilearnlink.domain.views.mediaUsuariosAtivosNoMes.MediaUsuariosAtivosNoMes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaUsuariosAtivosNoMesRepository extends JpaRepository<MediaUsuariosAtivosNoMes, Integer> {
    List<MediaUsuariosAtivosNoMes> findByMes(Integer mes);
}
