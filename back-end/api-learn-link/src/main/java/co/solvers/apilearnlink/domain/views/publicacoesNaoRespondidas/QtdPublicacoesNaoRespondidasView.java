package co.solvers.apilearnlink.domain.views.publicacoesNaoRespondidas;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "view_materias_nao_respondidas")
public class QtdPublicacoesNaoRespondidasView {

    // Getters and Setters
    @Id
    private String nomeMateria;
    private Long qtdPublicacoesNaoRespondidas;
    private Long qtdPublicacoesRespondidas;

}
