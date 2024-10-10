package co.solvers.apilearnlink.service.tiporeacao.dto;

import co.solvers.apilearnlink.domain.tiporeacao.TipoReacao;

public class TipoReacaoMapper {

    public static TipoReacaoListagemDto toDto(TipoReacao entity) {
        if (entity == null) return null;

        TipoReacaoListagemDto dto = new TipoReacaoListagemDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());

        return dto;
    }
}
