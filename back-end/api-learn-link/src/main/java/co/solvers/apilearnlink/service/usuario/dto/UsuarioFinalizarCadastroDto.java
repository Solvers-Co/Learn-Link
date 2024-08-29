package co.solvers.apilearnlink.service.usuario.dto;

import co.solvers.apilearnlink.domain.especialidade.Especialidade;
import co.solvers.apilearnlink.service.endereco.dto.EnderecoCriacaoDto;
import co.solvers.apilearnlink.service.especialidade.dto.EspecialidadeCriacaoDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioFinalizarCadastroDto {
    @NotNull
    private Long idUsuario;
    @NotNull
    private Long idEspecialidade;
    @NotNull
    private EnderecoCriacaoDto endereco;
}
