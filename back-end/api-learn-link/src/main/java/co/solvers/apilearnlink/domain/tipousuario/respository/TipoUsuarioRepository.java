package co.solvers.apilearnlink.domain.tipousuario.respository;

import co.solvers.apilearnlink.domain.tipousuario.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Integer> {
    Optional<TipoUsuario> findByTipoUsuario(String tipoUsuario);
}
