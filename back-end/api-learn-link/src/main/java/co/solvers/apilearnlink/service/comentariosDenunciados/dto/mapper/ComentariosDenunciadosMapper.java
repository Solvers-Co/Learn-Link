package co.solvers.apilearnlink.service.comentariosDenunciados.dto.mapper;

import co.solvers.apilearnlink.domain.views.comentariosDenunciados.ComentariosDenunciados;
import co.solvers.apilearnlink.service.comentario.dto.mapper.ComentarioMapper;
import co.solvers.apilearnlink.service.comentariosDenunciados.dto.ComentariosDenunciadosDto;

import java.util.List;

public class ComentariosDenunciadosMapper {

    public static ComentariosDenunciadosDto toComentariosDenunciadosDto(ComentariosDenunciados entity) {
        if (entity == null) return null;

        ComentariosDenunciadosDto dto = new ComentariosDenunciadosDto();

        dto.setComentario(ComentarioMapper.toComentarioListagemSimples(entity.getComentario()));
        dto.setQuantidadeDenuncias(entity.getQuantidadeDenuncias());

        return dto;
    }

    public static List<ComentariosDenunciadosDto> toComentariosDenunciadosDto(List<ComentariosDenunciados> entities) {
        return entities
                .stream()
                .map(ComentariosDenunciadosMapper::toComentariosDenunciadosDto)
                .toList();
    }
}
