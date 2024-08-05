package co.solvers.apilearnlink.service.usuario.autenticacao;

import co.solvers.apilearnlink.domain.usuario.Usuario;
import co.solvers.apilearnlink.domain.usuario.repository.UsuarioRepository;
import co.solvers.apilearnlink.service.usuario.autenticacao.dto.UsuarioDetalhesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(username);

        if (usuarioOpt.isEmpty()){
            throw new UsernameNotFoundException(String.format("usuário: % não encontrado", username));
        }
        return new UsuarioDetalhesDto(usuarioOpt.get());
    }
}
