package co.solvers.apilearnlink.domain.views.ReacoesEmComentariosDoUsuario.repository;

import co.solvers.apilearnlink.domain.views.ReacoesEmComentariosDoUsuario.QtdReacoesComentariosUsuarioView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QtdReacoesComentariosUsuarioViewRepository extends JpaRepository<QtdReacoesComentariosUsuarioView, Integer> {
    Optional<QtdReacoesComentariosUsuarioView> findByUsuarioId(Integer id);
}
