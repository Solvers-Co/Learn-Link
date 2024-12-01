package co.solvers.apilearnlink.api.controller.mediaUsuariosAtivosNoMes;

import co.solvers.apilearnlink.service.mediaUsuariosAtivosNoMes.UsuariosAtivosNoMesService;
import co.solvers.apilearnlink.service.mediaUsuariosAtivosNoMes.dto.UsuariosAtivosNoMesListagemDto;
import co.solvers.apilearnlink.service.mediaUsuariosAtivosNoMes.dto.mapper.UsuariosAtivosNoMesMapper;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/media-usuarios-ativos")
@RequiredArgsConstructor
public class UsuariosAtivosNoMesController {

    private final UsuariosAtivosNoMesService usuariosAtivosNoMesService;

    @GetMapping("/mes")
    private ResponseEntity<List<UsuariosAtivosNoMesListagemDto>> listagemMediaUsuariosAtivosNoMes(
            @RequestParam
            @Parameter(name = "mes", description = "Mês do ano", example = "8") int mes,
            @RequestParam
            @Parameter(name = "ano", description = "Ano Publicação", example = "2024") int ano
    ) {
        int mediaUsuariosAtivosNoMes = usuariosAtivosNoMesService.listagemUsuariosAtivosNoMes(mes, ano);

        List<UsuariosAtivosNoMesListagemDto> dtos = UsuariosAtivosNoMesMapper.toDto(mediaUsuariosAtivosNoMes);
        return ResponseEntity.ok(dtos);
    }
}
