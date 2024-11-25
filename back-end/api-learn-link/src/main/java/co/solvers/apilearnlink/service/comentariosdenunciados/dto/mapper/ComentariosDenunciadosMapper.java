package co.solvers.apilearnlink.service.comentariosdenunciados.dto.mapper;

import co.solvers.apilearnlink.domain.views.comentariosDenunciados.ComentariosDenunciados;
import co.solvers.apilearnlink.service.comentario.dto.ComentariosDenunciadosListagemComIa;
import co.solvers.apilearnlink.service.comentario.dto.mapper.ComentarioMapper;
import co.solvers.apilearnlink.service.comentariosdenunciados.dto.ComentariosDenunciadosDto;

import java.util.List;
import java.util.stream.Collectors;

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

    public static List<ComentariosDenunciadosListagemComIa> toIaList(List<ComentariosDenunciados> comentariosDenunciados) {
        return comentariosDenunciados.stream()
                .map(entity -> {
                    ComentariosDenunciadosListagemComIa dto = new ComentariosDenunciadosListagemComIa();
                    dto.setComentario(ComentarioMapper.toComentarioListagemSimples(entity.getComentario()));
                    dto.setQuantidadeDenuncias(entity.getQuantidadeDenuncias());
                    dto.setClassificacao(null);
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
