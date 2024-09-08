package co.solvers.apilearnlink.service.denuncia.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DenunciaPublicacaoCriarDto {
    @NotNull
    private Long idUsuario;
}
