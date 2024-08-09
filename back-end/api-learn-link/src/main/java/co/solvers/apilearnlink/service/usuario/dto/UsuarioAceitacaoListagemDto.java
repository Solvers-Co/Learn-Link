package co.solvers.apilearnlink.service.usuario.dto;

import co.solvers.apilearnlink.domain.tipostatus.TipoStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioAceitacaoListagemDto {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String tipoStatus;
}
