package co.solvers.apilearnlink.service.reacao.dto.mapper;

import co.solvers.apilearnlink.domain.reacao.Reacao;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import co.solvers.apilearnlink.service.comentario.dto.mapper.ComentarioMapper;
import co.solvers.apilearnlink.service.publicacao.dto.mapper.PublicacaoMapper;
import co.solvers.apilearnlink.service.reacao.dto.ReacaoComentarioListarDto;
import co.solvers.apilearnlink.service.reacao.dto.ReacaoPublicacaoListarDto;
import co.solvers.apilearnlink.service.reacao.dto.UsuarioReacaoListagemDto;
import co.solvers.apilearnlink.service.tiporeacao.dto.TipoReacaoMapper;

public class ReacaoMapper {

    public static ReacaoComentarioListarDto toReacaoComentarioListarDto(Reacao entity) {
        if (entity == null) return null;

        ReacaoComentarioListarDto dto = new ReacaoComentarioListarDto();
        dto.setId(entity.getId());
        dto.setTipoReacao(TipoReacaoMapper.toDto(entity.getTipoReacao()));
        dto.setComentario(ComentarioMapper.toDto(entity.getComentario()));
        dto.setUsuario(toDto(entity.getUsuario()));

        return dto;
    }

    public static ReacaoPublicacaoListarDto toReacaoPublicacaoListarDto(Reacao entity) {
        if (entity == null) return null;

        ReacaoPublicacaoListarDto dto = new ReacaoPublicacaoListarDto();
        dto.setId(entity.getId());
        dto.setTipoReacao(TipoReacaoMapper.toDto(entity.getTipoReacao()));
        dto.setPublicacao(PublicacaoMapper.toDto(entity.getPublicacao()));
        dto.setUsuario(toDto(entity.getUsuario()));

        return dto;
    }

    private static UsuarioReacaoListagemDto toDto(Usuario entity) {
        if (entity == null) return null;

        UsuarioReacaoListagemDto dto = new UsuarioReacaoListagemDto();
        dto.setNome(entity.getNome());

        return dto;
    }

}
