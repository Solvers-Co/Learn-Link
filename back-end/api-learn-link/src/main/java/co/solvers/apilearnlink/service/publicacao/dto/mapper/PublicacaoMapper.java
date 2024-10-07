package co.solvers.apilearnlink.service.publicacao.dto.mapper;

import co.solvers.apilearnlink.domain.comentario.Comentario;
import co.solvers.apilearnlink.domain.reacao.Reacao;
import co.solvers.apilearnlink.domain.tipopublicacao.TipoPublicacao;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import co.solvers.apilearnlink.service.canal.dto.mapper.CanalMapper;
import co.solvers.apilearnlink.service.especialidade.dto.mapper.EspecialidadeMapper;
import co.solvers.apilearnlink.service.publicacao.dto.PublicacaoCriacaoRequestDto;
import co.solvers.apilearnlink.service.publicacao.dto.PublicacaoListagemResponseDto;
import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import co.solvers.apilearnlink.service.publicacao.dto.PublicacaoListagemSimplesDto;
import co.solvers.apilearnlink.service.reacao.dto.ReacaoComentarioListarDto;
import co.solvers.apilearnlink.service.tiporeacao.dto.TipoReacaoMapper;
import co.solvers.apilearnlink.service.usuario.dto.mapper.UsuarioMapper;


import java.util.ArrayList;
import java.util.List;


public class PublicacaoMapper {
    public static Publicacao toEntity(PublicacaoCriacaoRequestDto dto) {
        if (dto == null) return null;

        Publicacao entity = new Publicacao();
        entity.setConteudo(dto.getConteudo());

        return entity;
    }

    public static PublicacaoListagemResponseDto toDto(Publicacao entity) {
        if (entity == null) return null;

        PublicacaoListagemResponseDto dto = new PublicacaoListagemResponseDto();
        dto.setId(entity.getId());
        dto.setConteudo(entity.getConteudo());
        dto.setDataHora(entity.getDataHora());
        dto.setTipoPublicacao(toTipoPublicacaoDto(entity.getTipoPublicacao()));
        dto.setStatus(entity.getStatus());
        dto.setCanal(CanalMapper.toDto(entity.getCanal()));
        dto.setUsuario(toUsuarioDto(entity.getUsuario()));
        dto.setReacoes(toReacaoDto(entity.getReacoes()));
        /*dto.setComentarios(toComentariosDto(entity.getComentarios()));*/

        return dto;
    }

    public static PublicacaoListagemSimplesDto toPublicacaoListagemSimples(Publicacao entity) {
        if (entity == null) return null;

        PublicacaoListagemSimplesDto dto = new PublicacaoListagemSimplesDto();
        dto.setId(entity.getId());
        dto.setConteudo(entity.getConteudo());
        dto.setDataHora(entity.getDataHora());
        dto.setTipoPublicacao(toTipoPublicacaoDto(entity.getTipoPublicacao()));
        dto.setStatus(entity.getStatus());
        dto.setCanal(CanalMapper.toDto(entity.getCanal()));
        dto.setUsuario(toUsuarioDto(entity.getUsuario()));

        return dto;
    }

    public static List<PublicacaoListagemResponseDto> toDto(List<Publicacao> entity) {
        return entity
                .stream()
                .map(PublicacaoMapper::toDto)
                .toList();
    }

    public static List<PublicacaoListagemResponseDto.ComentarioDto> toComentariosDto (List<Comentario> entities){
        if (entities == null) return null;

        List<PublicacaoListagemResponseDto.ComentarioDto> comentariosDto = new ArrayList<>();

        for (Comentario comentario : entities){
            PublicacaoListagemResponseDto.ComentarioDto comentarioDto = new PublicacaoListagemResponseDto.ComentarioDto();
            comentarioDto.setId(comentario.getId());
            comentarioDto.setComentario(comentario.getComentario());
            comentarioDto.setDataHora(comentario.getDataHora());
            comentarioDto.setUsuario(UsuarioMapper.toUsuarioListagemResponseDto(comentario.getUsuario()));
            comentarioDto.setReacoes(toReacaoDto(comentario.getReacoes()));

            comentariosDto.add(comentarioDto);
        }

        return comentariosDto;
    }

    private static PublicacaoListagemResponseDto.TipoPublicacaoDto toTipoPublicacaoDto (TipoPublicacao entity){
        if (entity == null) return null;

        PublicacaoListagemResponseDto.TipoPublicacaoDto dto = new PublicacaoListagemResponseDto.TipoPublicacaoDto();

        dto.setId(entity.getId());
        dto.setTipo(entity.getTipo());

        return dto;

    }

    private static PublicacaoListagemResponseDto.UsuarioPublicacaoListagemDto toUsuarioDto (Usuario entity){
        PublicacaoListagemResponseDto.UsuarioPublicacaoListagemDto usuarioDto = new PublicacaoListagemResponseDto.UsuarioPublicacaoListagemDto();
        if (entity == null) return null;

        usuarioDto.setId(entity.getId());
        usuarioDto.setNome(entity.getNome());
        usuarioDto.setEmail(entity.getEmail());
        usuarioDto.setEspecialidade(EspecialidadeMapper.toDto(entity.getEspecialidade()));

        return usuarioDto;
    }


    private static PublicacaoListagemResponseDto.ReacaoDto toReacaoDto(Reacao entity){
        if (entity == null) return null;

        PublicacaoListagemResponseDto.ReacaoDto dto = new PublicacaoListagemResponseDto.ReacaoDto();
        dto.setId(entity.getId());
        dto.setTipoReacao(TipoReacaoMapper.toDto(entity.getTipoReacao()));
        dto.setUsuario(toUsuarioDto(entity.getUsuario()));

        return dto;
    };

    private static List<PublicacaoListagemResponseDto.ReacaoDto> toReacaoDto(List<Reacao> entities){
        if (entities == null) return null;

        return entities
                .stream()
                .map(PublicacaoMapper::toReacaoDto)
                .toList();
    };
}
