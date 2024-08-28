package co.solvers.apilearnlink.service.comentario;

import co.solvers.apilearnlink.domain.comentario.Comentario;
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
        Optional<Comentario> comentario = comentarioRepository.findById(id);

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
        Comentario comentario =  buscarPorId(id);
        comentarioRepository.delete(comentario);
    }

    public String[][] buscaQuantidadeDeComentariosPorDiaMatriz(int mes, int ano) {
        List<QuantidadeComentarioDiaListagemDto> quantidadeComentarios = comentarioRepository.buscaQuantidadeDeComentariosPorDia(mes, ano);
        String[][] m = new String[31][2];

        if (quantidadeComentarios.isEmpty()) return null;

        for (int coluna = 0; coluna < m[0].length; coluna++) {

            for (int linha = 0; linha < m.length; linha++) {
                if (coluna == 0) {
                    m[linha][coluna] = quantidadeComentarios.get(linha).getDataComentario().toString();
                } else {
                    m[linha][coluna] = quantidadeComentarios.get(linha).getQuantidadeComentarios().toString();
                }
            }
        }
        return m;
    }

    public List<Comentario> listarPorPublicacao(int idPublicacao) {
        Publicacao publicacao = publicacaoService.listarPorId(idPublicacao);

        return comentarioRepository.findByPublicacao(publicacao);
    }

    public Page<Comentario> listarPorPublicacaoPaginado(int idPublicacao, Pageable pageable) {
        Publicacao publicacao = publicacaoService.listarPorId(idPublicacao);
        return comentarioRepository.findByPublicacao(publicacao, pageable);
    }

}
