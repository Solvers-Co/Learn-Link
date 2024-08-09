package co.solvers.apilearnlink.service.comentario.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioCriacaoDto {
    @Size(min = 3, max = 500)
    @NotBlank
    @Schema(description = "Conteúdo do comentário", example = "Acho que foi Pedro Álvares Cabral")
    private String comentario;
    @Positive
    @NotNull
    @Schema(description = "Id do usuário", example = "1")
    private Long idUsuario;
}
