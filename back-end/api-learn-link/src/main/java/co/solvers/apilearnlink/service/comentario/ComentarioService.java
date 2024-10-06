package co.solvers.apilearnlink.service.comentario;

import co.solvers.apilearnlink.domain.comentario.Comentario;
import co.solvers.apilearnlink.domain.comentario.Status;
import co.solvers.apilearnlink.domain.comentario.repository.ComentarioRepository;
import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import co.solvers.apilearnlink.exception.NaoEncontradoException;
import co.solvers.apilearnlink.service.comentario.dto.ComentarioCriacaoDto;
import co.solvers.apilearnlink.service.comentario.dto.QuantidadeComentarioDiaListagemDto;
import co.solvers.apilearnlink.service.comentario.dto.mapper.ComentarioMapper;
import co.solvers.apilearnlink.service.publicacao.PublicacaoService;
import co.solvers.apilearnlink.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final UsuarioService usuarioService;
    private final PublicacaoService publicacaoService;

    public Comentario comentar(int idPublicacao, ComentarioCriacaoDto comentarioCriacaoDto) {

        Usuario usuario = usuarioService.buscarPorId(comentarioCriacaoDto.getIdUsuario());
        Publicacao publicacao = publicacaoService.listarPorId(idPublicacao);

        Comentario comentario = ComentarioMapper.toEntity(comentarioCriacaoDto);
        comentario.setUsuario(usuario);
        comentario.setPublicacao(publicacao);

        return comentarioRepository.save(comentario);
    }

    public Comentario buscarPorId(int id) {
        Optional<Comentario> comentario = comentarioRepository.findByIdAndStatus(id, Status.ATIVO);

        if (comentario.isEmpty()) {
            throw new NaoEncontradoException("Comentário");
        }

        return comentario.get();
    }

    public Comentario editarComentario(int id, String comentarioAlterar) {
        Comentario comentario = buscarPorId(id);

        comentario.setComentario(comentarioAlterar);

        return comentarioRepository.save(comentario);
    }

    public void deletar(int id) {
        Comentario comentario = buscarPorId(id);

        comentario.setStatus(Status.EXCLUIDO);
        comentarioRepository.save(comentario);
    }

    public List<QuantidadeComentarioDiaListagemDto> buscaQuantidadeDeComentariosPorDia(int mes, int ano) {
        return comentarioRepository.buscaQuantidadeDeComentariosPorDia(mes, ano);
    }

    public List<Comentario> listarPorPublicacao(int idPublicacao) {
        Publicacao publicacao = publicacaoService.listarPorId(idPublicacao);

        return comentarioRepository.findByPublicacaoAndStatus(publicacao, Status.ATIVO);
    }

    public Page<Comentario> listarPorPublicacaoPaginado(int idPublicacao, Pageable pageable) {
        Publicacao publicacao = publicacaoService.listarPorId(idPublicacao);
        return comentarioRepository.findByPublicacaoAndStatus(publicacao, pageable, Status.ATIVO);
    }

}
