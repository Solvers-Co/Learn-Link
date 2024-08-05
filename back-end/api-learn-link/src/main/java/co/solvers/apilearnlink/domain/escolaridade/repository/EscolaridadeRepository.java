package co.solvers.apilearnlink.domain.escolaridade.repository;

import co.solvers.apilearnlink.domain.escolaridade.Escolaridade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EscolaridadeRepository extends JpaRepository<Escolaridade, Integer> {
    Optional<Escolaridade> findByEscolaridade(String escolaridade);
}
