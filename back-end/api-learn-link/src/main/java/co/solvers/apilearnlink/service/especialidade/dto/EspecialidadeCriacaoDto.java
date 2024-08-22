package co.solvers.apilearnlink.service.especialidade.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EspecialidadeCriacaoDto {
    @NotBlank
    private String materia;
}
