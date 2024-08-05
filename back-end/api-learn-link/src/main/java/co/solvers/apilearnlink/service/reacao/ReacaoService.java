package co.solvers.apilearnlink.service.reacao;

import co.solvers.apilearnlink.domain.comentario.Comentario;
import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import co.solvers.apilearnlink.domain.reacao.Reacao;
import co.solvers.apilearnlink.domain.reacao.repository.ReacaoRepository;
import co.solvers.apilearnlink.domain.tiporeacao.TipoReacao;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import co.solvers.apilearnlink.exception.NaoEncontradoException;
import co.solvers.apilearnlink.service.comentario.ComentarioService;
import co.solvers.apilearnlink.service.publicacao.PublicacaoService;
import co.solvers.apilearnlink.service.reacao.dto.ReacaoCriarDto;
import co.solvers.apilearnlink.service.tiporeacao.TipoReacaoService;
import co.solvers.apilearnlink.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReacaoService {

    private final ReacaoRepository reacaoRepository;
    private final UsuarioService usuarioService;
    private final PublicacaoService publicacaoService;
    private final ComentarioService comentarioService;
    private final TipoReacaoService tipoReacaoService;

    public Reacao reagirComentario (int idComentario, ReacaoCriarDto reacaoDto){
        Reacao reacao = new Reacao();
        Usuario usuario = usuarioService.buscarPorId(reacaoDto.getIdUsuario());
        Comentario comentario = comentarioService.buscarPorId(idComentario);
        TipoReacao tipoReacao = tipoReacaoService.buscarPorNome(reacaoDto.getTipoReacao());

        reacao.setUsuario(usuario);
        reacao.setComentario(comentario);
        reacao.setTipoReacao(tipoReacao);

        return reacaoRepository.save(reacao);
    }

    public Reacao reagirPublicacao (int idPublicacao, ReacaoCriarDto reacaoDto){
        Reacao reacao = new Reacao();
        Usuario usuario = usuarioService.buscarPorId(reacaoDto.getIdUsuario());
        Publicacao publicacao = publicacaoService.listarPorId(idPublicacao);
        TipoReacao tipoReacao = tipoReacaoService.buscarPorNome(reacaoDto.getTipoReacao());

        reacao.setUsuario(usuario);
        reacao.setPublicacao(publicacao);
        reacao.setTipoReacao(tipoReacao);

        return reacaoRepository.save(reacao);
    }

    public Reacao buscarPorId (int id){
        Optional<Reacao> optReacao = reacaoRepository.findById(id);

        if (optReacao.isEmpty()) throw new NaoEncontradoException("Reação");

        return optReacao.get();
    }

    public void removerReacao (int idComentario, int idReacao){
        Reacao reacao = buscarPorId(idReacao);

        reacaoRepository.deleteById(idReacao);
    }
}
