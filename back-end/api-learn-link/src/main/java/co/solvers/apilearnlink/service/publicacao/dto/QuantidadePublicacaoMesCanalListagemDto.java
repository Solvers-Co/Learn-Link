package co.solvers.apilearnlink.service.publicacao.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantidadePublicacaoMesCanalListagemDto {
    private String canal;
    private Long totalPublicacoes;
}
