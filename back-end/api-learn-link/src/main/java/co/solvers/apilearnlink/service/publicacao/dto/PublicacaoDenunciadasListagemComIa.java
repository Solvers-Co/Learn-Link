package co.solvers.apilearnlink.service.publicacao.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicacaoDenunciadasListagemComIa {
    private PublicacaoListagemSimplesDto publicacao;
    private Long quantidadeDenuncias;
    private String classificacao = null;
}