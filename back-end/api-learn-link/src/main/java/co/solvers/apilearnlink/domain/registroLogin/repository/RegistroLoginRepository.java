package co.solvers.apilearnlink.domain.registroLogin.repository;

import co.solvers.apilearnlink.domain.registroLogin.RegistroLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegistroLoginRepository extends JpaRepository<RegistroLogin, Long> {

    @Query("SELECT r " +
            "FROM RegistroLogin r " +
            "WHERE r.usuario.id = :idUsuario " +
            "ORDER BY r.registroLogin ASC")
    List<RegistroLogin> findByIdUsuario(Long idUsuario);
}
