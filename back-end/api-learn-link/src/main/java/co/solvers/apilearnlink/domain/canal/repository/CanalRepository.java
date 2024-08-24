package co.solvers.apilearnlink.domain.canal.repository;

import co.solvers.apilearnlink.domain.canal.Canal;
import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CanalRepository extends JpaRepository<Canal, Integer> {
    Canal findByNome(String nome);
}
