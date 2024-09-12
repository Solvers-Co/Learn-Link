package co.solvers.apilearnlink.api.controller.registro_login;

import co.solvers.apilearnlink.domain.registroLogin.RegistroLogin;
import co.solvers.apilearnlink.service.registrologin.RegistroLoginService;
import co.solvers.apilearnlink.service.registrologin.dto.RegistroLoginListagemDto;
import co.solvers.apilearnlink.service.registrologin.dto.RegistroLoginPerfilListagemDto;
import co.solvers.apilearnlink.service.registrologin.dto.mapper.RegistroLoginMapper;
import co.solvers.apilearnlink.service.usuario.autenticacao.dto.UsuarioTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("registro-logins")
@RequiredArgsConstructor
public class RegistroLoginController {
    private final RegistroLoginService registroLoginService;

    @GetMapping("/{idUsuario}")
    private ResponseEntity<List<RegistroLoginListagemDto>> listagemPorId(@PathVariable Long idUsuario) {
        List<RegistroLogin> listaRegistros = registroLoginService.listagemPorId(idUsuario);

        return ResponseEntity.ok(RegistroLoginMapper.toDto(listaRegistros));
    }
}
