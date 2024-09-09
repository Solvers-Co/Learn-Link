package co.solvers.apilearnlink.service.comentariosDenunciados.dto;

import co.solvers.apilearnlink.domain.comentario.Comentario;
import co.solvers.apilearnlink.service.comentario.dto.ComentarioListagemSimplesDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentariosDenunciadosDto {
    private ComentarioListagemSimplesDto comentario;
    private Long quantidadeDenuncias;
}
