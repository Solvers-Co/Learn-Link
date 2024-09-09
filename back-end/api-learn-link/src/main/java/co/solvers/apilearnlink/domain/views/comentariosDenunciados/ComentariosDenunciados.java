package co.solvers.apilearnlink.domain.views.comentariosDenunciados;

import co.solvers.apilearnlink.domain.comentario.Comentario;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComentariosDenunciados {
    private Comentario comentario;
    private Long quantidadeDenuncias;
}
