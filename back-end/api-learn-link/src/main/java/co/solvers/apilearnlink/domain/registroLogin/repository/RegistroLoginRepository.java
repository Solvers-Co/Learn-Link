package co.solvers.apilearnlink.domain.registroLogin.repository;

import co.solvers.apilearnlink.domain.registroLogin.RegistroLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegistroLoginRepository extends JpaRepository<RegistroLogin, Long> {

    /*@Query("SELECT DATE(registro_login) AS data_login, MIN(registro_login) AS primeiro_login, usuario_id FROM registro_login WHERE usuario_id = 1 AND YEAR(registro_login) = 2024 AND MONTH(registro_login) = 5 GROUP BY DATE(registro_login), usuario_id ORDER BY data_login")
    List<RegistroLogin> findAtividadeMes();*/

    @Query("SELECT r " +
            "FROM RegistroLogin r " +
            "WHERE r.usuario.id = :idUsuario " +
            "ORDER BY r.registroLogin ASC")
    List<RegistroLogin> findByIdUsuario(Long idUsuario);
}
