package co.solvers.apilearnlink.domain.reacao.repository;

import co.solvers.apilearnlink.domain.comentario.Comentario;
import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import co.solvers.apilearnlink.domain.reacao.Reacao;
import co.solvers.apilearnlink.domain.tiporeacao.TipoReacao;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReacaoRepository extends JpaRepository<Reacao, Integer> {

    Optional<Reacao> findByUsuarioAndPublicacaoAndTipoReacao(Usuario usuario, Publicacao publicacao, TipoReacao tipoReacao);

    Optional<Reacao> findByUsuarioAndComentarioAndTipoReacao(Usuario usuario, Comentario comentario, TipoReacao tipoReacao);
}
