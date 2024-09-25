package co.solvers.apilearnlink.service.notificacao;

import co.solvers.apilearnlink.domain.notificacao.Notificacao;
import co.solvers.apilearnlink.domain.notificacao.repository.NotificacaoRepository;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import co.solvers.apilearnlink.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificacaoService {
    private final NotificacaoRepository notificacaoRepository;
    private final UsuarioService usuarioService;

    public Notificacao criarNotificacao(
            Notificacao notificacao, Long usuarioGeradorId, Long usuarioRecebedorId){

        Usuario usuarioGerador = usuarioService.buscarPorId(usuarioRecebedorId);
        Usuario usuarioRecebedor = usuarioService.buscarPorId(usuarioGeradorId);

        notificacao.setUsuarioGerador(usuarioGerador);
        notificacao.setUsuarioRecebedor(usuarioRecebedor);

        return notificacaoRepository.save(notificacao);
    }
}
