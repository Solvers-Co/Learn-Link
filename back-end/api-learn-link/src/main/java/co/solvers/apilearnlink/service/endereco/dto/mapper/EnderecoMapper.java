package co.solvers.apilearnlink.service.endereco.dto.mapper;

import co.solvers.apilearnlink.domain.endereco.Endereco;
import co.solvers.apilearnlink.service.endereco.dto.EnderecoCriacaoDto;
import co.solvers.apilearnlink.service.endereco.dto.EnderecoListagemDto;

import java.util.List;

public class EnderecoMapper {

    public static Endereco toEntity (EnderecoCriacaoDto dto){
        if (dto == null) return null;

        Endereco entity = new Endereco();
        entity.setBairro(dto.getBairro());
        entity.setCidade(dto.getCidade());
        entity.setEstado(dto.getEstado());
        entity.setCep(dto.getCep());

        return entity;
    }

    public static EnderecoListagemDto toDto (Endereco entity){
        if (entity == null) return null;

        EnderecoListagemDto dto = new EnderecoListagemDto();
        dto.setBairro(entity.getBairro());
        dto.setCidade(entity.getCidade());
        dto.setEstado(entity.getEstado());
        dto.setCep(entity.getCep());

        return dto;
    }

    public static List<EnderecoListagemDto> toDto (List<Endereco> entities){
        if (entities == null) return null;

        return entities
                .stream()
                .map(EnderecoMapper::toDto)
                .toList();
    }
}
