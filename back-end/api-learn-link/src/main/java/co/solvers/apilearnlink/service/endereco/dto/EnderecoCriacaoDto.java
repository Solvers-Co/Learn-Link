package co.solvers.apilearnlink.service.endereco.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoCriacaoDto {

    @NotBlank
    @Size(min = 6, max = 255)
    @Schema(description = "Logradouro do endereço", example = "Rua das Flores")
    private String logradouro;

    @NotNull
    @PositiveOrZero
    @Schema(description = "Número do endereço", example = "123")
    private int numero;

    @NotBlank
    @Size(min = 6, max = 155)
    @Schema(description = "Bairro do endereço", example = "Centro")
    private String bairro;

    @NotBlank
    @Size(min = 2, max = 45)
    @Schema(description = "Cidade do endereço", example = "São Paulo")
    private String cidade;

    @NotBlank
    @Size(min = 2, max = 255)
    @Schema(description = "Estado do endereço", example = "São Paulo")
    private String estado;

    @NotBlank
    @Size(min = 8, max = 8)
    @Schema(description = "CEP do endereço", example = "12345678")
    private String cep;
}
