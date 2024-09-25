package co.solvers.apilearnlink.service.notificacao.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotificacaoListagemDto {

    private LocalDateTime dataHora;
    private String corpo;
    private String nomeUsuarioGerador;
    private String nomeUsuarioRecebedor;
}
