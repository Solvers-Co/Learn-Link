package co.solvers.apilearnlink.service.denuncia.dto;

import co.solvers.apilearnlink.service.comentario.dto.ComentarioListagemDto;
import co.solvers.apilearnlink.service.publicacao.dto.PublicacaoListagemResponseDto;
import co.solvers.apilearnlink.service.publicacao.dto.PublicacaoListagemSimplesDto;
import co.solvers.apilearnlink.service.usuario.dto.UsuarioListagemSimplesDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DenunciaPublicacaoListagemDto {
    private Long id;
    private PublicacaoListagemSimplesDto publicacao;
    private UsuarioListagemSimplesDto usuarioDenunciante;
}
