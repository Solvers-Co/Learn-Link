package co.solvers.apilearnlink.domain.views.ComentariosDenunciados.PublicacoesDenunciadas;


import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vw_publicacoes_denunciadas")
@Getter
@Setter
public class PublicacoesDenunciadasView {
    @Id
    private Long id;
    @ManyToOne
    private Publicacao publicacao;
    private int quantidadeDenuncias;
}
