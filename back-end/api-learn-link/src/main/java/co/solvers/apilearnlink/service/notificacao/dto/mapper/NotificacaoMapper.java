package co.solvers.apilearnlink.service.notificacao.dto.mapper;

import co.solvers.apilearnlink.domain.notificacao.Notificacao;
import co.solvers.apilearnlink.service.notificacao.dto.NotificacaoCriacaoDto;
import co.solvers.apilearnlink.service.notificacao.dto.NotificacaoListagemDto;

import java.time.LocalDateTime;

public class NotificacaoMapper {

    public static Notificacao toEntity(NotificacaoCriacaoDto dto){
        Notificacao entidade = new Notificacao();

        entidade.setCorpo(dto.getCorpo());
        entidade.setVista(dto.getVista());
        entidade.setDataHora(LocalDateTime.now());

        return entidade;
    }

    public static NotificacaoListagemDto toListagemDto(Notificacao entidade){
        NotificacaoListagemDto dto = new NotificacaoListagemDto();

        dto.setCorpo(entidade.getCorpo());
        dto.setDataHora(entidade.getDataHora());
        dto.setNomeUsuarioGerador(entidade.getUsuarioGerador().getNome());
        dto.setNomeUsuarioRecebedor(entidade.getUsuarioRecebedor().getNome());

        return dto;
    }
}
