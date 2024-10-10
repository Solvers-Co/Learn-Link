package co.solvers.apilearnlink.service.usuario.dto.mapper;

import co.solvers.apilearnlink.domain.registroLogin.RegistroLogin;
import co.solvers.apilearnlink.service.classificacao.dto.mapper.ClassificacaoMapper;
import co.solvers.apilearnlink.service.endereco.dto.mapper.EnderecoMapper;
import co.solvers.apilearnlink.service.especialidade.dto.mapper.EspecialidadeMapper;
import co.solvers.apilearnlink.service.tipousuario.dto.mapper.TipoUsuarioMapper;
import co.solvers.apilearnlink.service.usuario.autenticacao.dto.UsuarioTokenDto;
import co.solvers.apilearnlink.service.usuario.dto.*;
import co.solvers.apilearnlink.domain.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioMapper {

    public static UsuarioListagemDto toUsuarioListagemResponseDto(Usuario entity) {
        if (entity == null) return null;

        UsuarioListagemDto dto = new UsuarioListagemDto();

        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setCpf(entity.getCpf());

        UsuarioListagemDto.TipoStatusDto tipoStatusDto = new UsuarioListagemDto.TipoStatusDto();
        tipoStatusDto.setId(entity.getTipoStatus().getId());
        tipoStatusDto.setStatus(entity.getTipoStatus().getStatus());

        dto.setTipoStatus(tipoStatusDto);
        dto.setTipoUsuario(TipoUsuarioMapper.toDto(entity.getTipoUsuario()));
        dto.setClassificacao(ClassificacaoMapper.toDto(entity.getClassificacao()));
        dto.setEndereco(EnderecoMapper.toDto(entity.getEndereco()));
        dto.setEspecialidade(EspecialidadeMapper.toDto(entity.getEspecialidade()));

        return dto;
    }

    public static List<UsuarioListagemDto> toUsuarioListagemResponseDto(List<Usuario> entities) {
        return entities
                .stream()
                .map(UsuarioMapper::toUsuarioListagemResponseDto)
                .toList();
    }

    public static UsuarioListagemSimplesDto toUsuarioListagemSimplesDto(Usuario entity) {
        if (entity == null) return null;

        UsuarioListagemSimplesDto dto = new UsuarioListagemSimplesDto();
        dto.setEmail(entity.getEmail());
        dto.setNome(entity.getNome());

        return dto;
    }

    public static UsuarioListagemRecuperarSenhaDto toUsuarioListagemRecuperarSenhaDto(Usuario entity) {
        if (entity == null) return null;

        UsuarioListagemRecuperarSenhaDto dto = new UsuarioListagemRecuperarSenhaDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setNome(entity.getNome());

        return dto;
    }

    public static List<UsuarioListagemSimplesDto> toUsuarioListagemSimplesDto(List<Usuario> entities) {
        if (entities == null) return null;

        List<UsuarioListagemSimplesDto> dtos = new ArrayList<UsuarioListagemSimplesDto>();
        for (Usuario entity : entities) {
            dtos.add(toUsuarioListagemSimplesDto(entity));
        }

        return dtos;
    }

    public static Usuario toEntity(UsuarioCriacaoRequestDto dto) {
        if (dto == null) return null;

        Usuario usuario = new Usuario();

        usuario.setEmail(dto.getEmail());
        usuario.setCpf(dto.getCpf());
        usuario.setNome(dto.getNome());
        usuario.setSenha(dto.getSenha());

        return usuario;
    }

    public static Usuario of(UsuarioCriacaoRequestDto usuarioCriacaoDto) {
        if (usuarioCriacaoDto == null) return null;

        Usuario usuario = new Usuario();

        usuario.setEmail(usuarioCriacaoDto.getEmail());
        usuario.setNome(usuarioCriacaoDto.getNome());
        usuario.setSenha(usuarioCriacaoDto.getSenha());

        return usuario;
    }

    public static UsuarioTokenDto of(Usuario usuario, String token, RegistroLogin registroLogin) {
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setUserId(usuario.getId());
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setToken(token);
        usuarioTokenDto.setConectado(true);
        usuarioTokenDto.setTipoUsuario(TipoUsuarioMapper.toDto(usuario.getTipoUsuario()));

        UsuarioTokenDto.RegistroLoginDto registroLoginDto = new UsuarioTokenDto.RegistroLoginDto();
        registroLoginDto.setRegistroLogin(registroLogin.getRegistroLogin());

        usuarioTokenDto.setDataHoraLogin(registroLoginDto);

        return usuarioTokenDto;
    }

    public static UsuarioTokenDto desconectar(Usuario usuario) {
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setUserId(usuario.getId());
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setConectado(false);

        return usuarioTokenDto;
    }

}
