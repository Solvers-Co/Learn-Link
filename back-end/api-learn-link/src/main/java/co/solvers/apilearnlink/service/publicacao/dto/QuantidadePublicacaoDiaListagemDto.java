package co.solvers.apilearnlink.service.publicacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantidadePublicacaoDiaListagemDto {
    private Date dataPublicacao;
    private Long quantidadePublicacoes;
}
