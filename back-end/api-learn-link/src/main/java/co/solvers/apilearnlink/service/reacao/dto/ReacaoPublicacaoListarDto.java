package co.solvers.apilearnlink.service.reacao.dto;

import co.solvers.apilearnlink.domain.comentario.Comentario;
import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import co.solvers.apilearnlink.domain.tiporeacao.TipoReacao;
import co.solvers.apilearnlink.service.publicacao.dto.PublicacaoListagemResponseDto;
import co.solvers.apilearnlink.service.tiporeacao.dto.TipoReacaoListagemDto;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReacaoPublicacaoListarDto {
    private Integer id;
    private TipoReacaoListagemDto tipoReacao;
    private PublicacaoListagemResponseDto publicacao;
    private UsuarioReacaoListagemDto usuario;
}
