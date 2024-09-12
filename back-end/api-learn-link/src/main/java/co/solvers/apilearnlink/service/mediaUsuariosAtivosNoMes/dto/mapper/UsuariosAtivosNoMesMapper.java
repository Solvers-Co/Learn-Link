package co.solvers.apilearnlink.service.mediaUsuariosAtivosNoMes.dto.mapper;

import co.solvers.apilearnlink.domain.views.mediaUsuariosAtivosNoMes.MediaUsuariosAtivosNoMes;
import co.solvers.apilearnlink.service.mediaUsuariosAtivosNoMes.dto.UsuariosAtivosNoMesListagemDto;

import java.util.ArrayList;
import java.util.List;

public class UsuariosAtivosNoMesMapper {

    public static UsuariosAtivosNoMesListagemDto toDto(MediaUsuariosAtivosNoMes entidade){
        UsuariosAtivosNoMesListagemDto dto = new UsuariosAtivosNoMesListagemDto();

        dto.setAno(entidade.getAno());
        dto.setMes(entidade.getMes());
        dto.setUsuariosAtivos(entidade.getUsuariosAtivos());

        return dto;
    }

    public static List<UsuariosAtivosNoMesListagemDto> toDto(List<MediaUsuariosAtivosNoMes> entidades){
        List<UsuariosAtivosNoMesListagemDto> dtos = new ArrayList<>();

        for (MediaUsuariosAtivosNoMes entidade : entidades){
            dtos.add(toDto(entidade));
        }
        return dtos;
    }
}
