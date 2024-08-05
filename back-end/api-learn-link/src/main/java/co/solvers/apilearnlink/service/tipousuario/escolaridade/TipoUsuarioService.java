package co.solvers.apilearnlink.service.tipousuario.escolaridade;

import co.solvers.apilearnlink.domain.escolaridade.Escolaridade;
import co.solvers.apilearnlink.domain.escolaridade.repository.EscolaridadeRepository;
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

    public TipoUsuario buscarPorId(int id){
        Optional<TipoUsuario> escolaridadeUsuario = tipoUsuarioRepository.findById(id);

        if (escolaridadeUsuario.isEmpty()) throw new NaoEncontradoException("Tipo de usuário");

        return escolaridadeUsuario.get();
    }

    public TipoUsuario buscarPorTipoUsuario(String tipoUsuario){
        Optional<TipoUsuario> optTipoUsuario = tipoUsuarioRepository.findByTipoUsuario(tipoUsuario);

        if (optTipoUsuario.isEmpty()) throw new NaoEncontradoException("Tipo de usuário");

        return optTipoUsuario.get();
    }
}
