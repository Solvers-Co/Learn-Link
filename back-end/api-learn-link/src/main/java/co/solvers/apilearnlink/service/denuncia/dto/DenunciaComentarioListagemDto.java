package co.solvers.apilearnlink.service.denuncia.dto;

import co.solvers.apilearnlink.service.comentario.dto.ComentarioListagemDto;
import co.solvers.apilearnlink.service.usuario.dto.UsuarioListagemDto;
import co.solvers.apilearnlink.service.usuario.dto.UsuarioListagemSimplesDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DenunciaComentarioListagemDto {
    private Long id;
    private ComentarioListagemDto comentario;
    private UsuarioListagemSimplesDto usuarioDenunciante;
}
