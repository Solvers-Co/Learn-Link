package co.solvers.apilearnlink.domain.publicacao;

import co.solvers.apilearnlink.domain.canal.Canal;
import co.solvers.apilearnlink.domain.comentario.Comentario;
import co.solvers.apilearnlink.domain.reacao.Reacao;
import co.solvers.apilearnlink.domain.tipopublicacao.TipoPublicacao;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;


@Entity
@Getter
@Setter
public class Publicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String conteudo;
    @NotNull
    private LocalDateTime dataHora;
    @ManyToOne
    private TipoPublicacao tipoPublicacao;
    @Enumerated(EnumType.STRING)
    private PublicacaoStatus status;
    @ManyToOne
    @JoinColumn(name = "canal_id")
    private Canal canal;
    @ManyToOne
    private Usuario usuario;
    @OneToMany(mappedBy = "publicacao", cascade = CascadeType.REMOVE)
    List<Comentario> comentarios;
    @OneToMany(mappedBy = "publicacao", cascade = CascadeType.REMOVE)
    List<Reacao> reacoes;
    private String urlImagem;
}