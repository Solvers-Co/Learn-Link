package co.solvers.apilearnlink.domain.notificacao.repository;

import co.solvers.apilearnlink.domain.notificacao.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

    List<Notificacao> findByUsuarioRecebedorId(Long id);
}
