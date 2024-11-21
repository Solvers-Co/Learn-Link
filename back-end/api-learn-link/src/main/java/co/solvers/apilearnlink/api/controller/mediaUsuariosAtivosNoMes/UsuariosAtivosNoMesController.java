package co.solvers.apilearnlink.api.controller.mediaUsuariosAtivosNoMes;

import co.solvers.apilearnlink.service.mediaUsuariosAtivosNoMes.UsuariosAtivosNoMesService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/media-usuarios-ativos")
@RequiredArgsConstructor
public class UsuariosAtivosNoMesController {

    private final UsuariosAtivosNoMesService usuariosAtivosNoMesService;

    @GetMapping("/mes")
    private ResponseEntity<Integer> listagemMediaUsuariosAtivosNoMes(
            @RequestParam
            @Parameter(name = "mes", description = "Mês do ano", example = "8") int mes,
            @RequestParam
            @Parameter(name = "ano", description = "Ano Publicação", example = "2024") int ano
    ) {
        Integer mediaUsuariosAtivosNoMes = usuariosAtivosNoMesService.listagemUsuariosAtivosNoMes(mes, ano);

//        List<UsuariosAtivosNoMesListagemDto> dtos = UsuariosAtivosNoMesMapper.toDto(mediaUsuariosAtivosNoMes);
        return ResponseEntity.ok(mediaUsuariosAtivosNoMes);
    }
}
