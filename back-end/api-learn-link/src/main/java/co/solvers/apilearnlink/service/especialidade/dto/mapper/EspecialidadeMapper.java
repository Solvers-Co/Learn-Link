package co.solvers.apilearnlink.service.especialidade.dto.mapper;

import co.solvers.apilearnlink.domain.especialidade.Especialidade;
import co.solvers.apilearnlink.service.especialidade.dto.EspecialidadeCriacaoDto;
import co.solvers.apilearnlink.service.especialidade.dto.EspecialidadeListagemDto;

import java.util.List;

public class EspecialidadeMapper {

    public static Especialidade toEntity(EspecialidadeCriacaoDto especialidadeCriacaoDto) {
        if (especialidadeCriacaoDto == null) return null;

        Especialidade especialidade = new Especialidade();

        especialidade.setMateria(especialidadeCriacaoDto.getMateria());

        return especialidade;
    }

    public static EspecialidadeListagemDto toDto(Especialidade especialidade) {
        if (especialidade == null) return null;

        EspecialidadeListagemDto dto = new EspecialidadeListagemDto();

        dto.setMateria(especialidade.getMateria());

        return dto;
    }

    public static List<EspecialidadeListagemDto> toDto(List<Especialidade> especialidades) {
        return especialidades
                .stream()
                .map(EspecialidadeMapper::toDto)
                .toList();
    }
}
