package co.solvers.apilearnlink.service.reacao.dto;

import co.solvers.apilearnlink.service.publicacao.dto.PublicacaoListagemResponseDto;
import co.solvers.apilearnlink.service.tiporeacao.dto.TipoReacaoListagemDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReacaoPublicacaoListarDto {
    private Integer id;
    private TipoReacaoListagemDto tipoReacao;
    private PublicacaoListagemResponseDto publicacao;
    private UsuarioReacaoListagemDto usuario;
}
