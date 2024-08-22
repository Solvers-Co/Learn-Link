package co.solvers.apilearnlink.domain.especialidade.repository;

import co.solvers.apilearnlink.domain.especialidade.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {
    Optional<Especialidade> findByMateria(String materia);
}
