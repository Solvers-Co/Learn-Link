package co.solvers.apilearnlink.domain.publicacao.repository;

import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import co.solvers.apilearnlink.domain.publicacao.PublicacaoStatus;
import co.solvers.apilearnlink.service.publicacao.dto.QuantidadePublicacaoDiaListagemDto;
import co.solvers.apilearnlink.service.publicacao.dto.QuantidadePublicacaoMesCanalListagemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublicacaoRepository extends JpaRepository<Publicacao, Integer> {

    Page<Publicacao> findByStatus(Pageable pageable, PublicacaoStatus status);

    @Query("SELECT p FROM Publicacao p WHERE p.canal.id = :canalId AND p.status = :status")
    Page<Publicacao> findByCanalIdAndStatus(@Param("canalId") Long canalId, @Param("status") PublicacaoStatus status, Pageable pageable);

    List<Publicacao> findAllByTipoPublicacaoTipoAndStatusOrderByDataHoraDesc(String tipo, PublicacaoStatus status);


    @Query("SELECT p FROM Publicacao p WHERE p.conteudo LIKE %:palavrachave% AND p.status = :status ORDER BY p.dataHora DESC")
    Page<Publicacao> findByConteudoLikePalavrachaveAndStatusOrderByDataHoraDesc(@Param("palavrachave") String palavrachave, @Param("status") PublicacaoStatus status, Pageable pageable);

    @Query("SELECT new co.solvers.apilearnlink.service.publicacao.dto.QuantidadePublicacaoDiaListagemDto(" +
            "DATE(p.dataHora) as dataPublicacao, COUNT(p)) " +
            "FROM Publicacao p WHERE YEAR(p.dataHora) = :ano AND MONTH(p.dataHora) = :mes " +
            "GROUP BY DATE(p.dataHora) " +
            "ORDER BY dataPublicacao")
    List<QuantidadePublicacaoDiaListagemDto> buscaQuantidadeDePublicacaoPorDia(@Param("mes") int mes, @Param("ano") int ano);

    @Query("SELECT new co.solvers.apilearnlink.service.publicacao.dto.QuantidadePublicacaoMesCanalListagemDto(" +
            "c.nome" +
            ", COUNT(p.id)) " +
            "FROM Publicacao p JOIN p.canal c " +
            "WHERE YEAR(p.dataHora) = :ano AND MONTH(p.dataHora) = :mes AND p.status = :status " +
            "GROUP BY c.nome " +
            "ORDER BY COUNT(p.id) DESC")
    List<QuantidadePublicacaoMesCanalListagemDto> buscaQuantidadeDePublicacoesEmCadaCanal(@Param("mes") int mes, @Param("ano") int ano, @Param("status") PublicacaoStatus status);

    @Query("SELECT new co.solvers.apilearnlink.service.publicacao.dto.QuantidadePublicacaoMesCanalListagemDto(" +
            "c.nome" +
            ", COUNT(p.id)) " +
            "FROM Publicacao p JOIN p.canal c " +
            "WHERE YEAR(p.dataHora) = :ano AND MONTH(p.dataHora) = :mes AND p.status = :status " +
            "GROUP BY c.nome " +
            "ORDER BY COUNT(p.id) DESC " +
            "LIMIT 1")
    Optional<QuantidadePublicacaoMesCanalListagemDto> buscaCanalComMaiorNumeroDePublicacoes(@Param("mes") int mes, @Param("ano") int ano, @Param("status") PublicacaoStatus status);

}
