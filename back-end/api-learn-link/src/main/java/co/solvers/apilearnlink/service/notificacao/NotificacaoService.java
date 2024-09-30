package co.solvers.apilearnlink.service.notificacao;

import co.solvers.apilearnlink.domain.notificacao.Notificacao;
import co.solvers.apilearnlink.domain.notificacao.repository.NotificacaoRepository;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import co.solvers.apilearnlink.exception.NaoEncontradoException;
import co.solvers.apilearnlink.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificacaoService {
    private final NotificacaoRepository notificacaoRepository;
    private final UsuarioService usuarioService;

    public Notificacao criarNotificacao(
            Notificacao notificacao, Long usuarioGeradorId, Long usuarioRecebedorId){

        Usuario usuarioGerador = usuarioService.buscarPorId(usuarioGeradorId);
        Usuario usuarioRecebedor = usuarioService.buscarPorId(usuarioRecebedorId);

        notificacao.setCorpo(usuarioGerador.getNome() + notificacao.getCorpo());
        notificacao.setUsuarioGerador(usuarioGerador);
        notificacao.setUsuarioRecebedor(usuarioRecebedor);

        return notificacaoRepository.save(notificacao);
    }

    public List<Notificacao> listarNotificacoesUsuario(Long id){

        return notificacaoRepository.findByUsuarioRecebedorId(id);
    }

    public Notificacao visualizarNotificacao(Long id){

        Notificacao notificacao = notificacaoRepository.findById(id)
                        .orElseThrow(
                                () -> new NaoEncontradoException("Notificação")
                        );

        notificacao.setVista(0);

        return notificacaoRepository.save(notificacao);
    }
}
