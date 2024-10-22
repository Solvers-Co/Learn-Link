package co.solvers.apilearnlink.service.comentario.dto;

import co.solvers.apilearnlink.service.comentario.dto.ComentarioListagemSimplesDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentariosDenunciadosListagemComIa {
    private ComentarioListagemSimplesDto comentario;
    private Long quantidadeDenuncias;
    private String classificacao = null;
}
