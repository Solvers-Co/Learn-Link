package co.solvers.apilearnlink.service.usuario.autenticacao.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UsuarioLoginDto {

    @Schema(description = "Email do usuário", example = "jorge@gmail.com")
    private String email;
    @Schema(description = "Senha do usuário", example = "senha")
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
