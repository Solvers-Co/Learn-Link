package co.solvers.apilearnlink.domain.endereco.repository;

import co.solvers.apilearnlink.domain.endereco.Endereco;
import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
