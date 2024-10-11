package co.solvers.apilearnlink.service.livrosFuvest.dto.mapper;

import co.solvers.apilearnlink.domain.livrosFuvest.LivrosFuvest;
import co.solvers.apilearnlink.service.livrosFuvest.dto.LivrosFuvestCriacaoDto;

public class LivrosFuvestMapper {

    public static LivrosFuvestCriacaoDto toDto(LivrosFuvest entity) {
        LivrosFuvestCriacaoDto dto = new LivrosFuvestCriacaoDto();
        dto.setId(entity.getId());
        dto.setAnoVestibular(entity.getAnoVestibular());
        dto.setTitulo(entity.getTitulo());
        dto.setAutor(entity.getAutor());
        return dto;
    }

    public static LivrosFuvest toEntity(LivrosFuvestCriacaoDto dto) {
        LivrosFuvest entity = new LivrosFuvest();
        entity.setId(dto.getId());
        entity.setAnoVestibular(dto.getAnoVestibular());
        entity.setTitulo(dto.getTitulo());
        entity.setAutor(dto.getAutor());
        return entity;
    }
}