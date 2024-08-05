package co.solvers.apilearnlink.domain.reacao;

import co.solvers.apilearnlink.domain.comentario.Comentario;
import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import co.solvers.apilearnlink.domain.tiporeacao.TipoReacao;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Reacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private TipoReacao tipoReacao;
    @ManyToOne
    private Publicacao publicacao;
    @ManyToOne
    private Comentario comentario;
    @ManyToOne
    private Usuario usuario;
}
