package co.solvers.apilearnlink.domain.usuario.repository;

import co.solvers.apilearnlink.domain.tipostatus.TipoStatus;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import co.solvers.apilearnlink.service.usuario.dto.UsuarioAceitacaoListagemDto;
import co.solvers.apilearnlink.service.usuario.dto.UsuarioListagemRankingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByNome(String nome);

    Optional<Usuario> findById(Long id);

    Usuario deleteAllById(long id);

    @Query("SELECT new co.solvers.apilearnlink.service.usuario.dto.UsuarioListagemRankingDto(u.id, u.nome, u.email, COUNT(r.id)) " +
            "FROM Reacao r " +
            "JOIN r.comentario c " +
            "JOIN r.usuario u " +
            "GROUP BY u.id " +
            "ORDER BY COUNT(r.id) " +
            "LIMIT 10")
    List<UsuarioListagemRankingDto> findRanking();

    List<Usuario> findByTipoStatus(TipoStatus tipoStatus);

    /*@Query("SELECT tipo_status.status FROM usuario JOIN tipo_status ON tipo_status_id = tipo_status.id WHERE usuario.id = ?1")
    String findStatusById(long id);*/

    @Query("SELECT new co.solvers.apilearnlink.service.usuario.dto.UsuarioAceitacaoListagemDto(u.id, u.nome, u.cpf, u.email, ts.status) " +
            "FROM Usuario u JOIN u.tipoStatus ts JOIN u.tipoUsuario tu " +
            "WHERE tu.tipoUsuario = 'COMUM' " +
            "ORDER BY ts.status DESC")
    Page<UsuarioAceitacaoListagemDto> findAllUsuariosPaginado(Pageable pageable);

    @Query("SELECT new co.solvers.apilearnlink.service.usuario.dto.UsuarioAceitacaoListagemDto(u.id, u.nome, u.cpf, u.email, ts.status) " +
            "FROM Usuario u JOIN u.tipoStatus ts JOIN u.tipoUsuario tu " +
            "WHERE ts.status = 'APROVADO' " +
            "AND tu.tipoUsuario = 'COMUM' " +
            "ORDER BY ts.status DESC")
    Page<UsuarioAceitacaoListagemDto> findAllUsuariosAtivosPaginado(Pageable pageable);

    @Query("SELECT new co.solvers.apilearnlink.service.usuario.dto.UsuarioAceitacaoListagemDto(u.id, u.nome, u.cpf, u.email, ts.status) " +
            "FROM Usuario u JOIN u.tipoStatus ts JOIN u.tipoUsuario tu " +
            "WHERE ts.status = 'PENDENTE' " +
            "AND tu.tipoUsuario = 'COMUM' " +
            "ORDER BY ts.status DESC")
    Page<UsuarioAceitacaoListagemDto> findAllUsuariosPendentesPaginado(Pageable pageable);

    @Query("SELECT new co.solvers.apilearnlink.service.usuario.dto.UsuarioAceitacaoListagemDto(u.id, u.nome, u.cpf, u.email, ts.status) " +
            "FROM Usuario u JOIN u.tipoStatus ts JOIN u.tipoUsuario tu " +
            "WHERE ts.status = 'NEGADO' " +
            "AND tu.tipoUsuario = 'COMUM' " +
            "ORDER BY ts.status DESC")
    Page<UsuarioAceitacaoListagemDto> findAllUsuariosNegadosPaginado(Pageable pageable);

    //    @Query("SELECT new co.solvers.apilearnlink.service.usuario.dto.UsuarioAceitacaoListagemDto(u.id, u.nome, u.cpf, u.email, ts.status) " +
//            "FROM Usuario u JOIN u.tipoStatus ts " +
//            "ORDER BY ts.status DESC")
//    List<UsuarioAceitacaoListagemDto> findAllUsuarios();

//    @Query("SELECT new co.solvers.apilearnlink.service.usuario.dto.UsuarioAceitacaoListagemDto(u.id, u.nome, u.cpf, u.email, ts.status) " +
//            "FROM Usuario u JOIN u.tipoStatus ts " +
//            "WHERE ts.status = 'APROVADO' " +
//            "ORDER BY ts.status DESC")
//    List<UsuarioAceitacaoListagemDto> findAllUsuariosAtivos();

//    @Query("SELECT new co.solvers.apilearnlink.service.usuario.dto.UsuarioAceitacaoListagemDto(u.id, u.nome, u.cpf, u.email, ts.status) " +
//            "FROM Usuario u JOIN u.tipoStatus ts " +
//            "WHERE ts.status = 'PENDENTE' " +
//            "ORDER BY ts.status DESC")
//    List<UsuarioAceitacaoListagemDto> findAllUsuariosPendentes();

//    @Query("SELECT new co.solvers.apilearnlink.service.usuario.dto.UsuarioAceitacaoListagemDto(u.id, u.nome, u.cpf, u.email, ts.status) " +
//            "FROM Usuario u JOIN u.tipoStatus ts " +
//            "WHERE ts.status = 'NEGADO' " +
//            "ORDER BY ts.status DESC")
//    List<UsuarioAceitacaoListagemDto> findAllUsuariosNegados();
}
