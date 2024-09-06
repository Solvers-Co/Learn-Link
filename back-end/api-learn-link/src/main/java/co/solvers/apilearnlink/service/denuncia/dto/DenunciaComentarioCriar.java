package co.solvers.apilearnlink.service.denuncia.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DenunciaComentarioCriar {
    @NotNull
    private int idComentario;
    @NotNull
    private Long idUsuario;
}
