package co.solvers.apilearnlink.service.registrologin.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RegistroLoginListagemDto {

    private Integer id;
    private LocalDateTime registroLogin;
    private UsuarioDto usuario;

    @Data
    public static class UsuarioDto {
        private long id;
        private String nome;
        private String email;
    }

}
