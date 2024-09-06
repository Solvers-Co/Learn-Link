package co.solvers.apilearnlink.domain.denuncia.repository;

import co.solvers.apilearnlink.domain.comentario.Comentario;
import co.solvers.apilearnlink.domain.denuncia.Denuncia;
import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import co.solvers.apilearnlink.service.comentario.dto.QuantidadeComentarioDiaListagemDto;
import co.solvers.apilearnlink.service.denuncia.dto.PublicacoesDenunciadasDto;
import co.solvers.apilearnlink.service.publicacao.dto.QuantidadePublicacaoMesCanalListagemDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DenunciaRespository extends JpaRepository<Denuncia, Long> {

    Optional<Denuncia> findByPublicacaoAndUsuario(Publicacao publicacao, Usuario usuario);

    Optional<Denuncia> findByComentarioAndUsuario(Comentario comentario, Usuario usuario);

}
