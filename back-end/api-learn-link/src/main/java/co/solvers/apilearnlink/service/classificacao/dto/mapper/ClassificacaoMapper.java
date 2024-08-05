package co.solvers.apilearnlink.service.classificacao.dto.mapper;

import co.solvers.apilearnlink.domain.classificacao.Classificacao;
import co.solvers.apilearnlink.service.classificacao.dto.ClassificacaoListagemDto;

public class ClassificacaoMapper {

    public static ClassificacaoListagemDto toDto (Classificacao entity){
        if (entity == null) return null;

        ClassificacaoListagemDto dto = new ClassificacaoListagemDto();
        dto.setClassificacao(entity.getClassificacao());

        return dto;
    }
}
