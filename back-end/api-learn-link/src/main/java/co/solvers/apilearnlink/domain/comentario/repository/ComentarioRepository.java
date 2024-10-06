package co.solvers.apilearnlink.domain.comentario.repository;

import co.solvers.apilearnlink.domain.comentario.Comentario;
import co.solvers.apilearnlink.domain.comentario.Status;
import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import co.solvers.apilearnlink.service.comentario.dto.QuantidadeComentarioDiaListagemDto;
import co.solvers.apilearnlink.service.publicacao.dto.QuantidadePublicacaoDiaListagemDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

    Optional<Comentario> findByIdAndStatus(Integer id, Status status);

    @Query("SELECT new co.solvers.apilearnlink.service.comentario.dto.QuantidadeComentarioDiaListagemDto(" +
            "DATE(c.dataHora) as dataComentario, COUNT(c)) " +
            "FROM Comentario c WHERE YEAR(c.dataHora) = :ano AND MONTH(c.dataHora) = :mes " +
            "GROUP BY DATE(c.dataHora) " +
            "ORDER BY dataComentario")
    List<QuantidadeComentarioDiaListagemDto> buscaQuantidadeDeComentariosPorDia(@Param("mes") int mes, @Param("ano") int ano);


    List<Comentario> findByPublicacaoAndStatus(Publicacao publicacao, Status status);

    Page<Comentario> findByPublicacaoAndStatus(Publicacao publicacao, Pageable pageable, Status status);

}
