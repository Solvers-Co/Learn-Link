package co.solvers.apilearnlink.domain.registroLogin;

import co.solvers.apilearnlink.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class RegistroLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    private LocalDateTime registroLogin;

    @ManyToOne
    private Usuario usuario;
}
