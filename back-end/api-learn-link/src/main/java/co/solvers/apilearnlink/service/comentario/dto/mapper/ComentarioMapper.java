package co.solvers.apilearnlink.service.comentario.dto.mapper;

import co.solvers.apilearnlink.domain.comentario.Comentario;
import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import co.solvers.apilearnlink.domain.reacao.Reacao;
import co.solvers.apilearnlink.domain.tipopublicacao.TipoPublicacao;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import co.solvers.apilearnlink.service.comentario.dto.ComentarioCriacaoDto;
import co.solvers.apilearnlink.service.comentario.dto.ComentarioListagemDto;
import co.solvers.apilearnlink.service.comentario.dto.ComentarioListagemSimplesDto;
import co.solvers.apilearnlink.service.especialidade.dto.mapper.EspecialidadeMapper;
import co.solvers.apilearnlink.service.publicacao.dto.PublicacaoCriacaoRequestDto;
import co.solvers.apilearnlink.service.publicacao.dto.PublicacaoListagemResponseDto;
import co.solvers.apilearnlink.service.publicacao.dto.mapper.PublicacaoMapper;
import co.solvers.apilearnlink.service.tiporeacao.dto.TipoReacaoMapper;
import co.solvers.apilearnlink.service.usuario.dto.mapper.UsuarioMapper;

import java.util.ArrayList;
import java.util.List;

public class ComentarioMapper {

    public static Comentario toEntity(ComentarioCriacaoDto dto) {
        if (dto == null) return null;

        Comentario entity = new Comentario();
        entity.setComentario(dto.getComentario());

        return entity;
    }

    public static ComentarioListagemDto toDto(Comentario entity) {
        if (entity == null) return null;

        ComentarioListagemDto dto = new ComentarioListagemDto();
        dto.setId(entity.getId());
        dto.setComentario(entity.getComentario());
        dto.setDataHora(entity.getDataHora());
        dto.setReacoes(toReacaoDto(entity.getReacoes()));
        dto.setPublicacao(toPublicacaoDto(entity.getPublicacao()));
        dto.setUsuario(toUsuarioDto(entity.getUsuario()));

        return dto;
    }

    public static ComentarioListagemSimplesDto toComentarioListagemSimples(Comentario entity) {
        if (entity == null) return null;

        ComentarioListagemSimplesDto dto = new ComentarioListagemSimplesDto();
        dto.setId(entity.getId());
        dto.setComentario(entity.getComentario());
        dto.setDataHora(entity.getDataHora());
        dto.setUsuario(toUsuarioDto(entity.getUsuario()));

        return dto;
    }


    public static List<ComentarioListagemDto> toDto(List<Comentario> entity) {
        return entity
                .stream()
                .map(ComentarioMapper::toDto)
                .toList();
    }

    private static ComentarioListagemDto.UsuarioDto toUsuarioDto (Usuario entity){
        if (entity == null) return null;

        ComentarioListagemDto.UsuarioDto dto = new ComentarioListagemDto.UsuarioDto();
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setEspecialidade(EspecialidadeMapper.toDto(entity.getEspecialidade()));

        return dto;
    }

    private static ComentarioListagemDto.TipoPublicacaoDto toTipoPublicacaoDto (TipoPublicacao entity){
        if (entity == null) return null;

        ComentarioListagemDto.TipoPublicacaoDto dto = new ComentarioListagemDto.TipoPublicacaoDto();
        dto.setId(entity.getId());
        dto.setTipo(entity.getTipo());

        return dto;
    }

    private static ComentarioListagemDto.ReacaoDto toReacaoDto(Reacao entity){
        if (entity == null) return null;

        ComentarioListagemDto.ReacaoDto dto = new ComentarioListagemDto.ReacaoDto();
        dto.setId(entity.getId());
        dto.setTipoReacao(TipoReacaoMapper.toDto(entity.getTipoReacao()));
        dto.setUsuario(toUsuarioDto(entity.getUsuario()));

        return dto;
    };

    private static List<ComentarioListagemDto.ReacaoDto> toReacaoDto(List<Reacao> entities){
        if (entities == null) return null;

        return entities
                .stream()
                .map(ComentarioMapper::toReacaoDto)
                .toList();
    };

    private static ComentarioListagemDto.PublicacaoDto toPublicacaoDto (Publicacao entity){
        if (entity == null) return null;

        ComentarioListagemDto.PublicacaoDto publicacaoDto = new ComentarioListagemDto.PublicacaoDto();
        publicacaoDto.setId(entity.getId());
        publicacaoDto.setTipoPublicacao(toTipoPublicacaoDto(entity.getTipoPublicacao()));
        publicacaoDto.setConteudo(entity.getConteudo());
        publicacaoDto.setDataHora(entity.getDataHora());
        publicacaoDto.setUsuario(UsuarioMapper.toUsuarioListagemResponseDto(entity.getUsuario()));

        return publicacaoDto;
    }
}
