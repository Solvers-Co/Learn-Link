package co.solvers.apilearnlink.service.escolaridade.dto.mapper;

import co.solvers.apilearnlink.domain.escolaridade.Escolaridade;
import co.solvers.apilearnlink.service.escolaridade.dto.EscolaridadeListagemDto;

public class EscolaridadeMapper {
    public static EscolaridadeListagemDto toDto (Escolaridade entity){
        if (entity == null) return null;

        EscolaridadeListagemDto dto = new EscolaridadeListagemDto();
        dto.setEscolaridade(entity.getEscolaridade());

        return dto;
    }
}
