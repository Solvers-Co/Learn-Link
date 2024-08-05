package co.solvers.apilearnlink.service.publicacao.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequisicaoMesAnoDto {
    @Min(1)
    @Max(12)
    @NotNull
    int mes;
    @Min(2020)
    @Max(2050)
    @NotNull
    int ano;
}
