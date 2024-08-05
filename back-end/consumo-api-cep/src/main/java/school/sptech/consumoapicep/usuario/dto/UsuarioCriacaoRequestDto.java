package school.sptech.consumoapicep.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioCriacaoRequestDto {

    @NotBlank
    @Size(min = 3, max = 100)
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String senha;

    private String especialidade;
    private Integer pontos;
    @NotNull
    private int numero;
    @NotBlank
    @Size(min = 8, max = 8)
    private String cep;
}
