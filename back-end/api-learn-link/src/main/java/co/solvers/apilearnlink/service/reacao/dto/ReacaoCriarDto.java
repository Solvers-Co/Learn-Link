package co.solvers.apilearnlink.service.reacao.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReacaoCriarDto {
    @NotBlank
    @Schema(description = "Tipo de reação", example = "CURTIDA")
    private String tipoReacao;
    @NotNull
    @Positive
    @Schema(description = "Id do comentário", example = "1")
    private Long idUsuario;
}
