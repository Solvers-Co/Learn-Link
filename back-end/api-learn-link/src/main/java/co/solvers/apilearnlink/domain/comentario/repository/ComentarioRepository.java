package co.solvers.apilearnlink.domain.comentario.repository;

import co.solvers.apilearnlink.domain.comentario.Comentario;
import co.solvers.apilearnlink.domain.comentario.ComentarioStatus;
import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import co.solvers.apilearnlink.service.comentario.dto.QuantidadeComentarioDiaListagemDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

    Optional<Comentario> findByIdAndStatus(Integer id, ComentarioStatus status);

    @Query("SELECT new co.solvers.apilearnlink.service.comentario.dto.QuantidadeComentarioDiaListagemDto(" +
            "DATE(c.dataHora) as dataComentario, COUNT(c)) " +
            "FROM Comentario c WHERE YEAR(c.dataHora) = :ano AND MONTH(c.dataHora) = :mes AND c.status = :status " +
            "GROUP BY DATE(c.dataHora) " +
            "ORDER BY dataComentario")
    List<QuantidadeComentarioDiaListagemDto> buscaQuantidadeDeComentariosPorDia(@Param("mes") int mes, @Param("ano") int ano, @Param("status") ComentarioStatus status);


    List<Comentario> findByPublicacaoAndStatus(Publicacao publicacao, ComentarioStatus status);

    Page<Comentario> findByPublicacaoAndStatus(Publicacao publicacao, Pageable pageable, ComentarioStatus status);

}