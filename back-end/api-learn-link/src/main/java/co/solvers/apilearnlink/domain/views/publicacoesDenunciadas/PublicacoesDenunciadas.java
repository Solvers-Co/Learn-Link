package co.solvers.apilearnlink.domain.views.publicacoesDenunciadas;

import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicacoesDenunciadas {
    private Publicacao publicacao;
    private Long quantidadeDenuncias;
}
