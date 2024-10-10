package co.solvers.apilearnlink.service.usuario.dto;

import co.solvers.apilearnlink.service.endereco.dto.EnderecoCriacaoDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
