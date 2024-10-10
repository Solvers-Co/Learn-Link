package co.solvers.apilearnlink.service.comentario.dto;

import co.solvers.apilearnlink.domain.comentario.ComentarioStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ComentarioListagemSimplesDto {
    private Integer id;
    private String comentario;
    private LocalDateTime dataHora;
    private ComentarioStatus status;
    private ComentarioListagemDto.UsuarioDto usuario;

}
