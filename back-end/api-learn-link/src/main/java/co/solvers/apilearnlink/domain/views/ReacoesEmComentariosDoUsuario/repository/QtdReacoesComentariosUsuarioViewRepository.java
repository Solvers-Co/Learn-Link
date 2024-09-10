package co.solvers.apilearnlink.domain.views.ReacoesEmComentariosDoUsuario.repository;

import co.solvers.apilearnlink.domain.views.ReacoesEmComentariosDoUsuario.QtdReacoesComentariosUsuarioView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QtdReacoesComentariosUsuarioViewRepository extends JpaRepository<QtdReacoesComentariosUsuarioView, Integer> {
    Optional<QtdReacoesComentariosUsuarioView> findByUsuarioId(Long id);

    //lista os 10 primeiros registros
    List<QtdReacoesComentariosUsuarioView> findTop10ByOrderByReacoesDesc();
}
