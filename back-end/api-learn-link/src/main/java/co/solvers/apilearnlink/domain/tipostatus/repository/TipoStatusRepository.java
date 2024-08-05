package co.solvers.apilearnlink.domain.tipostatus.repository;

import co.solvers.apilearnlink.domain.tipostatus.TipoStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoStatusRepository extends JpaRepository<TipoStatus, Integer> {
    Optional<TipoStatus> findByStatusIgnoreCase(String status);
}
