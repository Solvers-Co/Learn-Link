package co.solvers.apilearnlink.service.usuario.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCriacaoRequestDto {

    @NotBlank
    @Size(min = 3, max = 100)
    @Schema(description = "Nome do usuário", example = "Jorge")
    private String nome;

    @CPF
    @NotBlank
    @Schema(description = "CPF do usuário", example = "18219822821") //isso é um cpf de verdade não sei de qm é kkkk
    private String cpf;

    @NotBlank
    @Email
    @Schema(description = "Email do usuário", example = "jorge@gmail.com")
    private String email;

    @NotBlank
    @Schema(description = "Senha do usuário", example = "senha")
    private String senha;

}
