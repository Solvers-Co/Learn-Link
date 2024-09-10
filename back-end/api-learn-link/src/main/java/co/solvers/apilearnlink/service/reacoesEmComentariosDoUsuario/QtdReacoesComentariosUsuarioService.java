package co.solvers.apilearnlink.service.reacoesEmComentariosDoUsuario;

import co.solvers.apilearnlink.domain.views.ReacoesEmComentariosDoUsuario.QtdReacoesComentariosUsuarioView;
import co.solvers.apilearnlink.domain.views.ReacoesEmComentariosDoUsuario.repository.QtdReacoesComentariosUsuarioViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QtdReacoesComentariosUsuarioService {
    private final QtdReacoesComentariosUsuarioViewRepository qtdReacoesComentariosUsuarioViewRepository;

    public Optional<QtdReacoesComentariosUsuarioView> listagemQtdReacoesComentarios(Long id){
        return qtdReacoesComentariosUsuarioViewRepository.findByUsuarioId(id);
    }
}
