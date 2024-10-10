package co.solvers.apilearnlink.service.publicacoesDenunciadas.dto.mapper;

import co.solvers.apilearnlink.domain.views.publicacoesDenunciadas.PublicacoesDenunciadas;
import co.solvers.apilearnlink.service.publicacao.dto.mapper.PublicacaoMapper;
import co.solvers.apilearnlink.service.publicacoesDenunciadas.dto.PublicacoesDenunciadasDto;

import java.util.List;

public class PublicacoesDenunciadasMapper {

    public static PublicacoesDenunciadasDto toPublicacoesDenunciadasDto(PublicacoesDenunciadas entity) {
        if (entity == null) return null;

        PublicacoesDenunciadasDto dto = new PublicacoesDenunciadasDto();

        dto.setPublicacao(PublicacaoMapper.toPublicacaoListagemSimples(entity.getPublicacao()));
        dto.setQuantidadeDenuncias(entity.getQuantidadeDenuncias());

        return dto;
    }

    public static List<PublicacoesDenunciadasDto> toPublicacoesDenunciadasDto(List<PublicacoesDenunciadas> entities) {
        return entities
                .stream()
                .map(PublicacoesDenunciadasMapper::toPublicacoesDenunciadasDto)
                .toList();
    }

}
