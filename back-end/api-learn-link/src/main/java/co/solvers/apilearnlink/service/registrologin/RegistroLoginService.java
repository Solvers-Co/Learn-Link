package co.solvers.apilearnlink.service.registrologin;

import co.solvers.apilearnlink.domain.registroLogin.RegistroLogin;
import co.solvers.apilearnlink.domain.registroLogin.repository.RegistroLoginRepository;
import co.solvers.apilearnlink.domain.usuario.Usuario;
import co.solvers.apilearnlink.domain.usuario.repository.UsuarioRepository;
import co.solvers.apilearnlink.exception.NaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistroLoginService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RegistroLoginRepository registroLoginRepository;

    public RegistroLogin gerarLog(Long idUsuario) {

        RegistroLogin registroLogin = new RegistroLogin();

        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

        if (usuario.isEmpty()) {
            throw new NaoEncontradoException("Usuário");
        }

        registroLogin.setUsuario(usuario.get());

        registroLoginRepository.save(registroLogin);

        return registroLogin;

    }

 /*   public boolean[][] retornarAtividadeMes(Long idUsuario){
        List<RegistroLogin> registros = registroLoginRepository.findAtividadeMes();

        for (RegistroLogin registroLogin : registros){
            System.out.println(registroLogin.getRegistroLogin());
        }

        return null;
    }*/
}
