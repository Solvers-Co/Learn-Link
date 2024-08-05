package co.solvers.apilearnlink.service.tipousuario.escolaridade.dto.mapper;

import co.solvers.apilearnlink.domain.escolaridade.Escolaridade;
import co.solvers.apilearnlink.domain.tipousuario.TipoUsuario;
import co.solvers.apilearnlink.service.tipousuario.escolaridade.dto.TipoUsuarioListagemDto;

public class TipoUsuarioMapper {
    public static TipoUsuarioListagemDto toDto (TipoUsuario entity){
        if (entity == null) return null;

        TipoUsuarioListagemDto dto = new TipoUsuarioListagemDto();
        dto.setTipoUsuario(entity.getTipoUsuario());

        return dto;
    }
}
