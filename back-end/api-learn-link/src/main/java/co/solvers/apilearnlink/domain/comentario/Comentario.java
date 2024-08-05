package co.solvers.apilearnlink.domain.comentario;

import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import co.solvers.apilearnlink.domain.reacao.Reacao;
import co.solvers.apilearnlink.domain.tipopublicacao.TipoPublicacao;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String comentario;
    @CreationTimestamp
    private LocalDateTime dataHora;
    @ManyToOne
    private Publicacao publicacao;
    @ManyToOne
    private Usuario usuario;
    @OneToMany(mappedBy = "comentario")
    List<Reacao> reacoes;
}
