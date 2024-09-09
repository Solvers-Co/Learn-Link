package co.solvers.apilearnlink.service.denuncia.dto.mapper;

import co.solvers.apilearnlink.domain.denuncia.Denuncia;
import co.solvers.apilearnlink.service.comentario.dto.mapper.ComentarioMapper;
import co.solvers.apilearnlink.service.denuncia.dto.DenunciaComentarioListagemDto;
import co.solvers.apilearnlink.service.denuncia.dto.DenunciaPublicacaoListagemDto;
import co.solvers.apilearnlink.service.publicacoesDenunciadas.dto.PublicacoesDenunciadasDto;
import co.solvers.apilearnlink.domain.views.publicacoesDenunciadas.PublicacoesDenunciadas;
import co.solvers.apilearnlink.service.publicacao.dto.mapper.PublicacaoMapper;
import co.solvers.apilearnlink.service.usuario.dto.mapper.UsuarioMapper;

public class DenunciaMapper {

    public static DenunciaPublicacaoListagemDto toDenunciaPublicacaoListagemDto(Denuncia entity) {
        if (entity == null) return null;

        DenunciaPublicacaoListagemDto dto = new DenunciaPublicacaoListagemDto();

        dto.setId(entity.getId());
        dto.setPublicacao(PublicacaoMapper.toPublicacaoListagemSimples(entity.getPublicacao()));
        dto.setUsuarioDenunciante(UsuarioMapper.toUsuarioListagemSimplesDto(entity.getUsuario()));

        return dto;
    }

    public static DenunciaComentarioListagemDto toDenunciaComentarioListagemDto(Denuncia entity) {
        if (entity == null) return null;

        DenunciaComentarioListagemDto dto = new DenunciaComentarioListagemDto();

        dto.setId(entity.getId());
        dto.setComentario(ComentarioMapper.toComentarioListagemSimples(entity.getComentario()));
        dto.setUsuarioDenunciante(UsuarioMapper.toUsuarioListagemSimplesDto(entity.getUsuario()));

        return dto;
    }


}
