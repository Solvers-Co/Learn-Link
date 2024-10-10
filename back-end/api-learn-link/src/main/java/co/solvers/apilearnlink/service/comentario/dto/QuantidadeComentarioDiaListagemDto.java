package co.solvers.apilearnlink.service.comentario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantidadeComentarioDiaListagemDto {
    private Date dataComentario;
    private Long quantidadeComentarios;
}
