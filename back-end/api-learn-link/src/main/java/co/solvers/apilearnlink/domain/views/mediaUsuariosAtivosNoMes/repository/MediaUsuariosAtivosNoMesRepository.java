package co.solvers.apilearnlink.domain.views.mediaUsuariosAtivosNoMes.repository;

import co.solvers.apilearnlink.domain.views.mediaUsuariosAtivosNoMes.MediaUsuariosAtivosNoMes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MediaUsuariosAtivosNoMesRepository extends JpaRepository<MediaUsuariosAtivosNoMes, Integer> {
    @Query(value = "SELECT usuarios_ativos FROM view_usuarios_ativos_no_mes WHERE ano = :ano AND mes = :mes", nativeQuery = true)
    Integer findUsuariosAtivosNoMes(@Param("mes") Integer mes, @Param("ano") Integer ano);
}
