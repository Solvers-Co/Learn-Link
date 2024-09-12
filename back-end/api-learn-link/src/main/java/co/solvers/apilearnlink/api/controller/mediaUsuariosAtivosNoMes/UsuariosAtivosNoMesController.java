package co.solvers.apilearnlink.api.controller.mediaUsuariosAtivosNoMes;

import co.solvers.apilearnlink.domain.views.mediaUsuariosAtivosNoMes.MediaUsuariosAtivosNoMes;
import co.solvers.apilearnlink.service.mediaUsuariosAtivosNoMes.UsuariosAtivosNoMesService;
import co.solvers.apilearnlink.service.mediaUsuariosAtivosNoMes.dto.UsuariosAtivosNoMesListagemDto;
import co.solvers.apilearnlink.service.mediaUsuariosAtivosNoMes.dto.mapper.UsuariosAtivosNoMesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/media-usuarios-ativos")
@RequiredArgsConstructor
public class UsuariosAtivosNoMesController {

    private final UsuariosAtivosNoMesService usuariosAtivosNoMesService;

    @GetMapping("/media-mes")
    private ResponseEntity<List<UsuariosAtivosNoMesListagemDto>> listagemMediaUsuariosAtivosNoMes(@RequestParam int mes){
        List<MediaUsuariosAtivosNoMes> mediaUsuariosAtivosNoMes = usuariosAtivosNoMesService.listagemUsuariosAtivosNoMes(mes);

        List<UsuariosAtivosNoMesListagemDto> dtos = UsuariosAtivosNoMesMapper.toDto(mediaUsuariosAtivosNoMes);
        return ResponseEntity.ok(dtos);
    }
}
