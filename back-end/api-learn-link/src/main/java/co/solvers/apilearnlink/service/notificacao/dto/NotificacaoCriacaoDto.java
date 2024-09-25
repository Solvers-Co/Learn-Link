package co.solvers.apilearnlink.service.notificacao.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotificacaoCriacaoDto {

    private String corpo;
    private int vista;
    private Long usuarioGeradorId;
    private Long usuarioRecebedorId;
}
