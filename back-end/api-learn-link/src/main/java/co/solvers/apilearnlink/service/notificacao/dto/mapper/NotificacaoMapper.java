package co.solvers.apilearnlink.service.notificacao.dto.mapper;

import co.solvers.apilearnlink.domain.notificacao.Notificacao;
import co.solvers.apilearnlink.service.notificacao.dto.NotificacaoCriacaoDto;
import co.solvers.apilearnlink.service.notificacao.dto.NotificacaoListagemDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificacaoMapper {

    public static Notificacao toEntity(NotificacaoCriacaoDto dto) {
        Notificacao entidade = new Notificacao();

        entidade.setCorpo(dto.getCorpo());
        entidade.setVista(1);
        entidade.setDataHora(LocalDateTime.now());
        entidade.setIdPublicacao(dto.getIdPublicacao());
        entidade.setIdComentario(dto.getIdComentario());

        return entidade;
    }

    public static NotificacaoListagemDto toListagemDto(Notificacao entidade) {
        NotificacaoListagemDto dto = new NotificacaoListagemDto();

        dto.setId(entidade.getId());
        dto.setCorpo(entidade.getCorpo());
        dto.setDataHora(entidade.getDataHora());
        dto.setIdUsuarioGerador(entidade.getUsuarioGerador().getId());
        dto.setNomeUsuarioGerador(entidade.getUsuarioGerador().getNome());
        dto.setNomeUsuarioRecebedor(entidade.getUsuarioRecebedor().getNome());
        dto.setVista(entidade.getVista());
        dto.setIdPublicacao(entidade.getIdPublicacao());
        dto.setIdComentario(entidade.getIdComentario());

        return dto;
    }

    public static List<NotificacaoListagemDto> toListagemDto(List<Notificacao> entidades) {
        List<NotificacaoListagemDto> dtos = new ArrayList<>();

        for (Notificacao entidade : entidades) {
            dtos.add(toListagemDto(entidade));
        }
        return dtos;
    }
}
