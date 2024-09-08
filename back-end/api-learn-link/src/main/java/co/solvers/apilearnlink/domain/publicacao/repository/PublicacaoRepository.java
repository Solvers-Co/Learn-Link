package co.solvers.apilearnlink.domain.publicacao.repository;

import co.solvers.apilearnlink.domain.publicacao.Publicacao;
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

    List<Publicacao> findAllByOrderByDataHoraDesc();

    List<Publicacao> findAllByOrderByDataHora();

    Page<Publicacao> findAllByOrderByDataHoraDesc(Pageable pageable);

    @Query("SELECT p FROM Publicacao p WHERE p.canal.id = :canalId")
    Page<Publicacao> findByCanalId(@Param("canalId") Long canalId, Pageable pageable);

    List<Publicacao> findAllByTipoPublicacaoTipoOrderByDataHoraDesc(String tipo);

    @Query("SELECT p FROM Publicacao p WHERE p.conteudo LIKE %:palavrachave% ORDER BY p.dataHora DESC")
    List<Publicacao> findByConteudoLikePalavrachaveOrderByDataHoraDesc(@Param("palavrachave") String palavrachave);

    @Query("SELECT new co.solvers.apilearnlink.service.publicacao.dto.QuantidadePublicacaoDiaListagemDto(" +
            "DATE(p.dataHora) as data_publicacao" +
            ", COUNT(p))" +
            " FROM Publicacao p WHERE YEAR(p.dataHora) = :ano AND MONTH(p.dataHora) = :mes " +
            "GROUP BY DATE(p.dataHora) " +
            "ORDER BY data_publicacao")
    List<QuantidadePublicacaoDiaListagemDto> buscaQuantidadeDePublicacaoPorDia(@Param("mes") int mes, @Param("ano") int ano);

/*    @Query("SELECT co.solvers.apilearnlink.service.publicacao.dto.QuantidadePublicacaoMesCanalListagemDto(" +
            "c.nome" +
            ", COUNT(p.id))" +
            "FROM Publicacao p JOIN p.canal c" +
            "WHERE YEAR(p.dataHora) = 2024 AND MONTH(p.dataHora) = 5 " +
            "GROUP BY c.nome " +
            "ORDER BY p.id DESC")
    List<QuantidadePublicacaoMesCanalListagemDto> buscaQuantidadeDePublicacoesPorMesNosCanais();*/

    @Query("SELECT new co.solvers.apilearnlink.service.publicacao.dto.QuantidadePublicacaoMesCanalListagemDto(" +
            "c.nome" +
            ", COUNT(p.id)) " +
            "FROM Publicacao p JOIN p.canal c " +
            "WHERE YEAR(p.dataHora) = :ano AND MONTH(p.dataHora) = :mes " +
            "GROUP BY c.nome " +
            "ORDER BY COUNT(p.id) DESC")
    List<QuantidadePublicacaoMesCanalListagemDto> buscaQuantidadeDePublicacoesEmCadaCanal(@Param("mes") int mes, @Param("ano") int ano);

    @Query("SELECT new co.solvers.apilearnlink.service.publicacao.dto.QuantidadePublicacaoMesCanalListagemDto(" +
            "c.nome" +
            ", COUNT(p.id)) " +
            "FROM Publicacao p JOIN p.canal c " +
            "WHERE YEAR(p.dataHora) = :ano AND MONTH(p.dataHora) = :mes " +
            "GROUP BY c.nome " +
            "ORDER BY COUNT(p.id) DESC " +
            "LIMIT 1")
    Optional<QuantidadePublicacaoMesCanalListagemDto> buscaCanalComMaiorNumeroDePublicacoes(@Param("mes") int mes, @Param("ano") int ano);


//    @Query("SELECT p FROM Publicacao p WHERE p.descricao LIKE %?1% ORDER BY p.dataHoraPublicacao DESC")
//    List<Publicacao> findByPublicacaoLikeOrderByDataHoraPublicacaoDesc(String palavraChave);
}
