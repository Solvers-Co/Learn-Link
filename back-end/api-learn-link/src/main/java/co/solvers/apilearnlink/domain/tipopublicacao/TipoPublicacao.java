package co.solvers.apilearnlink.domain.tipopublicacao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tipo_publicacao")
@Getter
@Setter
public class TipoPublicacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tipo;

}
