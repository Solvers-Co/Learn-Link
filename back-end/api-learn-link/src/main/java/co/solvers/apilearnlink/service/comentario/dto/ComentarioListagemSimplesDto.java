package co.solvers.apilearnlink.service.comentario.dto;

import co.solvers.apilearnlink.domain.comentario.Status;
import co.solvers.apilearnlink.service.especialidade.dto.EspecialidadeListagemDto;
import co.solvers.apilearnlink.service.tiporeacao.dto.TipoReacaoListagemDto;
import co.solvers.apilearnlink.service.usuario.dto.UsuarioListagemDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ComentarioListagemSimplesDto {
    private Integer id;
    private String comentario;
    private LocalDateTime dataHora;
    private Status status;
    private ComentarioListagemDto.UsuarioDto usuario;






}
