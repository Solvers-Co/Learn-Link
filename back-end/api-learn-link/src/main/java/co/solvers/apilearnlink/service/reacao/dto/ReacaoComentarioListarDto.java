package co.solvers.apilearnlink.service.reacao.dto;

import co.solvers.apilearnlink.service.comentario.dto.ComentarioListagemDto;
import co.solvers.apilearnlink.service.tiporeacao.dto.TipoReacaoListagemDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReacaoComentarioListarDto {
    private Integer id;
    private TipoReacaoListagemDto tipoReacao;
    private ComentarioListagemDto comentario;
    private UsuarioReacaoListagemDto usuario;
}
