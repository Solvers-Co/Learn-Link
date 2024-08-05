package co.solvers.apilearnlink.domain.classificacao.repository;

import co.solvers.apilearnlink.domain.classificacao.Classificacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassificacaoRepository extends JpaRepository<Classificacao, Integer> {
    Optional<Classificacao> findByClassificacao(String classificacao);
}
