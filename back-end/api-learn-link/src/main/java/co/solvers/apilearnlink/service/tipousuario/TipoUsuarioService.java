package co.solvers.apilearnlink.service.tipousuario;

import co.solvers.apilearnlink.domain.tipousuario.TipoUsuario;
import co.solvers.apilearnlink.domain.tipousuario.respository.TipoUsuarioRepository;
import co.solvers.apilearnlink.exception.NaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TipoUsuarioService {
    private final TipoUsuarioRepository tipoUsuarioRepository;

    public TipoUsuario buscarPorId(int id) {
        Optional<TipoUsuario> tipoUsuario = tipoUsuarioRepository.findById(id);

        if (tipoUsuario.isEmpty()) throw new NaoEncontradoException("Tipo de usuário");

        return tipoUsuario.get();
    }

    public TipoUsuario buscarPorTipoUsuario(String tipoUsuario) {
        Optional<TipoUsuario> optTipoUsuario = tipoUsuarioRepository.findByTipoUsuario(tipoUsuario);

        if (optTipoUsuario.isEmpty()) throw new NaoEncontradoException("Tipo de usuário");

        return optTipoUsuario.get();
    }
}
