package co.solvers.apilearnlink.domain.notificacao;

import co.solvers.apilearnlink.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String corpo;
    private LocalDateTime dataHora;
    private int vista;

    @OneToOne
    private Usuario usuarioGerador;

    @OneToOne
    private Usuario usuarioRecebedor;
}
