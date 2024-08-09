package co.solvers.apilearnlink.service.publicacao.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicacaoCriacaoRequestDto {
    @Size(min = 3, max = 500)
    @NotBlank
    @Schema(description = "Conteúdo da publicação", example = "Quem descobriu o Brasil?")
    private String conteudo;
    @NotNull
    @Positive
    @Schema(description = "Id do tipo de publicação", example = "1")
    private int idTipoPublicacao;
    @NotNull
    @Positive
    @Schema(description = "Id do usuário", example = "1")
    private Long idUsuario;
    @NotNull
    @Positive
    @Schema(description = "Id do canal", example = "4")
    private int idCanal;
}
