package co.solvers.apilearnlink.domain.denuncia.repository;

import co.solvers.apilearnlink.domain.comentario.Comentario;
import co.solvers.apilearnlink.domain.comentario.ComentarioStatus;
import co.solvers.apilearnlink.domain.denuncia.Denuncia;
import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import co.solvers.apilearnlink.domain.publicacao.PublicacaoStatus;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import co.solvers.apilearnlink.domain.views.comentariosDenunciados.ComentariosDenunciados;
import co.solvers.apilearnlink.domain.views.publicacoesDenunciadas.PublicacoesDenunciadas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DenunciaRespository extends JpaRepository<Denuncia, Long> {

    Optional<Denuncia> findByPublicacaoAndUsuario(Publicacao publicacao, Usuario usuario);

    Optional<Denuncia> findByComentarioAndUsuario(Comentario comentario, Usuario usuario);

    @Query("SELECT new co.solvers.apilearnlink.domain.views.publicacoesDenunciadas.PublicacoesDenunciadas(" +
            "p, COUNT(d.id)) " +
            "FROM Denuncia d JOIN d.publicacao p " +
            "WHERE d.publicacao IS NOT NULL AND p.status = :status " +
            "GROUP BY p.id")
    List<PublicacoesDenunciadas> buscaPublicacoesDenunciadas(@Param("status") PublicacaoStatus status);

    @Query("SELECT new co.solvers.apilearnlink.domain.views.comentariosDenunciados.ComentariosDenunciados(" +
            "c, COUNT(c.id)) " +
            "FROM Denuncia d JOIN d.comentario c " +
            "WHERE d.comentario IS NOT NULL AND c.status = :status " +
            "GROUP BY c.id")
    List<ComentariosDenunciados> buscaComentariosDenunciados(@Param("status") ComentarioStatus status);

    void deleteAllByPublicacao(Publicacao publicacao);

    void deleteAllByComentario(Comentario comentario);

}
