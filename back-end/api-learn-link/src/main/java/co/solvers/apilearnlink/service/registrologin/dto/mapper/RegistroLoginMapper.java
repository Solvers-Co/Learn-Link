package co.solvers.apilearnlink.service.registrologin.dto.mapper;

import co.solvers.apilearnlink.domain.registroLogin.RegistroLogin;
import co.solvers.apilearnlink.service.registrologin.dto.RegistroLoginListagemDto;
import co.solvers.apilearnlink.service.registrologin.dto.RegistroLoginPerfilListagemDto;

import java.util.ArrayList;
import java.util.List;

public class RegistroLoginMapper {

    public static RegistroLoginListagemDto toDto(RegistroLogin registroLogin) {
        RegistroLoginListagemDto dto = new RegistroLoginListagemDto();

        dto.setId(registroLogin.getId());
        dto.setRegistroLogin(registroLogin.getRegistroLogin());

        RegistroLoginListagemDto.UsuarioDto usuarioDto = new RegistroLoginListagemDto.UsuarioDto();
        usuarioDto.setId(registroLogin.getUsuario().getId());
        usuarioDto.setNome(registroLogin.getUsuario().getNome());
        usuarioDto.setEmail(registroLogin.getUsuario().getEmail());

        dto.setUsuario(usuarioDto);
        return dto;
    }

    public static List<RegistroLoginListagemDto> toDto(List<RegistroLogin> entidades) {
        List<RegistroLoginListagemDto> dtos = new ArrayList<>();

        for (RegistroLogin registroLogin : entidades) {
            dtos.add(toDto(registroLogin));
        }

        return dtos;
    }

    public static RegistroLoginPerfilListagemDto toPerfilDto(RegistroLogin registroLogin) {
        RegistroLoginPerfilListagemDto dto = new RegistroLoginPerfilListagemDto();

        dto.setRegistroLogin(registroLogin.getRegistroLogin());

        return dto;
    }

    public static List<RegistroLoginPerfilListagemDto> toPerfilDto(List<RegistroLogin> registroLogins) {
        List<RegistroLoginPerfilListagemDto> dtos = new ArrayList<>();

        for (RegistroLogin registroLogin : registroLogins) {
            dtos.add(toPerfilDto(registroLogin));
        }
        return dtos;
    }
}
