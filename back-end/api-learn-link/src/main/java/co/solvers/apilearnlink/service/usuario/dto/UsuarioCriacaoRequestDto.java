package co.solvers.apilearnlink.service.usuario.dto;

import co.solvers.apilearnlink.service.endereco.dto.EnderecoCriacaoDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    private EnderecoCriacaoDto endereco;

    /*@NotBlank
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
    private String cep;*/

}
