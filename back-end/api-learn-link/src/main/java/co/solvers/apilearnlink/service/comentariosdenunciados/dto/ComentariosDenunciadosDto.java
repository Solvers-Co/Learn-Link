package co.solvers.apilearnlink.service.comentariosdenunciados.dto;

import co.solvers.apilearnlink.service.comentario.dto.ComentarioListagemSimplesDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentariosDenunciadosDto {
    private ComentarioListagemSimplesDto comentario;
    private Long quantidadeDenuncias;
}
