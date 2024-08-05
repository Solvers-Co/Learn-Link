package co.solvers.apilearnlink.domain.tiporeacao.repository;

import co.solvers.apilearnlink.domain.tiporeacao.TipoReacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoReacaoRepository extends JpaRepository<TipoReacao, Integer> {
    Optional<TipoReacao> findByNomeIgnoreCase(String nome);
}
