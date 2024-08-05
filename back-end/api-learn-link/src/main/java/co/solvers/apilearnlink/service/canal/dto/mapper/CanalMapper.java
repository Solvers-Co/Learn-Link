package co.solvers.apilearnlink.service.canal.dto.mapper;

import co.solvers.apilearnlink.domain.canal.Canal;
import co.solvers.apilearnlink.service.canal.dto.CanalListagemDto;

public class CanalMapper {

    public static CanalListagemDto toDto (Canal entity){
        if (entity == null) return null;

        CanalListagemDto dto = new CanalListagemDto();
        dto.setNome(entity.getNome());

        return dto;
    }
}
