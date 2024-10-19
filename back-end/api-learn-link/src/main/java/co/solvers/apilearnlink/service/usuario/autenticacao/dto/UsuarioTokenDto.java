package co.solvers.apilearnlink.service.usuario.autenticacao.dto;

import co.solvers.apilearnlink.service.tipousuario.dto.TipoUsuarioListagemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class UsuarioTokenDto {

    private Long userId;
    private String nome;
    private String email;
    private String token;
    private boolean conectado;
    private RegistroLoginDto dataHoraLogin;
    private TipoUsuarioListagemDto tipoUsuario;

    public TipoUsuarioListagemDto getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuarioListagemDto tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RegistroLoginDto {
        private LocalDateTime registroLogin;
    }

    public RegistroLoginDto getDataHoraLogin() {
        return dataHoraLogin;
    }

    public void setDataHoraLogin(RegistroLoginDto dataHoraLogin) {
        this.dataHoraLogin = dataHoraLogin;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }
}
