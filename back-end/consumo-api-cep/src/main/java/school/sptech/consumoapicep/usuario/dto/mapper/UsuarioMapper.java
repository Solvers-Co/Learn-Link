package school.sptech.consumoapicep.usuario.dto.mapper;


import jakarta.validation.Valid;
import school.sptech.consumoapicep.domain.endereco.Endereco;
import school.sptech.consumoapicep.domain.usuario.Usuario;
import school.sptech.consumoapicep.usuario.dto.UsuarioCriacaoRequestDto;
import school.sptech.consumoapicep.usuario.dto.UsuarioListagemResponseDto;

/*@Mapper(componentModel = "spring")*/
public interface UsuarioMapper {

    public static Usuario toEntity(@Valid UsuarioCriacaoRequestDto dto, Endereco endereco){
        if (dto == null) return null;

        Usuario entity = new Usuario();
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setSenha(dto.getSenha());
        entity.setEspecialidade(dto.getEspecialidade());
        entity.setEndereco(endereco);

        return entity;
    }

    public static UsuarioListagemResponseDto toDto(@Valid Usuario entity){
        if (entity == null) return null;

        UsuarioListagemResponseDto dto = new UsuarioListagemResponseDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setEspecialidade(entity.getEspecialidade());
        dto.setPontos(entity.getPontos());
        dto.setEndereco(entity.getEndereco());

        return dto;
    }



}
