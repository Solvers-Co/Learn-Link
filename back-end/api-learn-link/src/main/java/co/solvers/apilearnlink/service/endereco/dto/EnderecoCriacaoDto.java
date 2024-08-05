package co.solvers.apilearnlink.service.endereco.dto;

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
    private String logradouro;
    @NotNull
    @PositiveOrZero
    private int numero;
    @NotBlank
    @Size(min = 6, max = 155)
    private String bairro;
    @NotBlank
    @Size(min = 2, max = 45)
    private String cidade;
    @NotBlank
    @Size(min = 2, max = 255)
    private String estado;
    @NotBlank
    @Size(min = 8, max = 8)
    private String cep;
}
