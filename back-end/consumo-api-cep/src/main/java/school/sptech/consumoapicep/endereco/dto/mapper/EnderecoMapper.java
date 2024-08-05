package school.sptech.consumoapicep.endereco.dto.mapper;

import school.sptech.consumoapicep.domain.endereco.Endereco;
import school.sptech.consumoapicep.endereco.dto.EnderecoApiExternaDto;
import school.sptech.consumoapicep.endereco.dto.EnderecoListagemResponseDto;

public interface EnderecoMapper {

    static Endereco toEntity (EnderecoApiExternaDto dto, int numero){
        if (dto == null) return null;

        Endereco entity = new Endereco();
        entity.setRua(dto.getRua());
        entity.setNumero(numero);
        entity.setBairro(dto.getBairro());
        entity.setCidade(dto.getCidade());
        entity.setEstado(dto.getEstado());
        entity.setCep(dto.getCep());

        return entity;
    }
}
