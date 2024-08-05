package co.solvers.apilearnlink.service.reacao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReacaoCriarDto {
    @NotBlank
    private String tipoReacao;
    @NotNull
    @Positive
    private Long idUsuario;
}
