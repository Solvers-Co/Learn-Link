package co.solvers.apilearnlink.api.controller.mediausuariosativosnomes;

import co.solvers.apilearnlink.domain.views.mediaUsuariosAtivosNoMes.MediaUsuariosAtivosNoMes;
import co.solvers.apilearnlink.service.mediausuariosativosnomes.UsuariosAtivosNoMesService;
import co.solvers.apilearnlink.service.mediausuariosativosnomes.dto.UsuariosAtivosNoMesListagemDto;
import co.solvers.apilearnlink.service.mediausuariosativosnomes.dto.mapper.UsuariosAtivosNoMesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/media-usuarios-ativos")
@RequiredArgsConstructor
public class UsuariosAtivosNoMesController {

    private final UsuariosAtivosNoMesService usuariosAtivosNoMesService;

    @GetMapping("/mes")
    private ResponseEntity<List<UsuariosAtivosNoMesListagemDto>> listagemMediaUsuariosAtivosNoMes(){
        List<MediaUsuariosAtivosNoMes> mediaUsuariosAtivosNoMes = usuariosAtivosNoMesService.listagemUsuariosAtivosNoMes();

        List<UsuariosAtivosNoMesListagemDto> dtos = UsuariosAtivosNoMesMapper.toDto(mediaUsuariosAtivosNoMes);
        return ResponseEntity.ok(dtos);
    }
}
