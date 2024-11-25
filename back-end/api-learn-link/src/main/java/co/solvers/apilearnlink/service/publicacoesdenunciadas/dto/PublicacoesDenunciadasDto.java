package co.solvers.apilearnlink.service.publicacoesdenunciadas.dto;

import co.solvers.apilearnlink.service.publicacao.dto.PublicacaoListagemSimplesDto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicacoesDenunciadasDto {
    private PublicacaoListagemSimplesDto publicacao;
    private Long quantidadeDenuncias;
}
