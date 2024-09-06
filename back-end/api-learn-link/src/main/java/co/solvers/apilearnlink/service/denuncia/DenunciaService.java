package co.solvers.apilearnlink.service.denuncia;

import co.solvers.apilearnlink.domain.canal.repository.CanalRepository;
import co.solvers.apilearnlink.domain.comentario.Comentario;
import co.solvers.apilearnlink.domain.denuncia.Denuncia;
import co.solvers.apilearnlink.domain.denuncia.repository.DenunciaRespository;
import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import co.solvers.apilearnlink.domain.publicacao.repository.PublicacaoRepository;
import co.solvers.apilearnlink.domain.tipopublicacao.repository.TipoPublicacaoRepository;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import co.solvers.apilearnlink.service.canal.CanalService;
import co.solvers.apilearnlink.service.comentario.ComentarioService;
import co.solvers.apilearnlink.service.denuncia.dto.DenunciaComentarioCriarDto;
import co.solvers.apilearnlink.service.denuncia.dto.DenunciaPublicacaoCriarDto;
import co.solvers.apilearnlink.service.denuncia.dto.PublicacoesDenunciadasDto;
import co.solvers.apilearnlink.service.publicacao.PublicacaoService;
import co.solvers.apilearnlink.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DenunciaService {

    private final DenunciaRespository denunciaRespository;
    private final PublicacaoService publicacaoService;
    private final ComentarioService comentarioService;
    private final UsuarioService usuarioService;

    public Denuncia denunciarPublicacao(int idPublicacao, DenunciaPublicacaoCriarDto denunciaPublicacaoCriarDto) {
        Publicacao publicacao = publicacaoService.listarPorId(idPublicacao);
        Usuario usuario = usuarioService.buscarPorId(denunciaPublicacaoCriarDto.getIdUsuario());

        if (usuario.getId() == publicacao.getUsuario().getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O usuário não pode denúnciar sua própria publicação");
        }

        if (verificaSeUsuarioJaDenunciouPublicacao(publicacao, usuario)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esse usuário já denunciou essa publicação");
        }

        Denuncia denuncia = new Denuncia();

        denuncia.setPublicacao(publicacao);
        denuncia.setUsuario(usuario);

        return denunciaRespository.save(denuncia);
    }

    public Denuncia denunciarComentario(int idComentario, DenunciaComentarioCriarDto denunciaComentarioCriarDto) {
        Comentario comentario = comentarioService.buscarPorId(idComentario);
        Usuario usuario = usuarioService.buscarPorId(denunciaComentarioCriarDto.getIdUsuario());

        if (usuario.getId() == comentario.getUsuario().getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O usuário não pode denúnciar seu próprio comentário");
        }

        if (verificaSeUsuarioJaDenunciouComentario(comentario, usuario)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esse usuário já denunciou esse comentário");
        }

        Denuncia denuncia = new Denuncia();

        denuncia.setComentario(comentario);
        denuncia.setUsuario(usuario);

        return denunciaRespository.save(denuncia);
    }

    private Boolean verificaSeUsuarioJaDenunciouPublicacao(Publicacao publicacao, Usuario usuario) {
        Optional<Denuncia> denuncia = denunciaRespository.findByPublicacaoAndUsuario(publicacao, usuario);

        if (denuncia.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean verificaSeUsuarioJaDenunciouComentario(Comentario comentario, Usuario usuario) {
        Optional<Denuncia> denuncia = denunciaRespository.findByComentarioAndUsuario(comentario, usuario);

        if (denuncia.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

}
