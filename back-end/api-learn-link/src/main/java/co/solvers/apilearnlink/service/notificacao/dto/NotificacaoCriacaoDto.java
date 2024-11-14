package co.solvers.apilearnlink.service.notificacao.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificacaoCriacaoDto {

    private String corpo;
    private Long usuarioGeradorId;
    private Long usuarioRecebedorId;
    private int idPublicacao;
    private int idComentario;
}
