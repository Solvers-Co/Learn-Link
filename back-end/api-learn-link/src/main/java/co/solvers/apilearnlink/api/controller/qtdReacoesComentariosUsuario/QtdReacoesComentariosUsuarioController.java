package co.solvers.apilearnlink.api.controller.qtdReacoesComentariosUsuario;

import co.solvers.apilearnlink.domain.views.ReacoesEmComentariosDoUsuario.QtdReacoesComentariosUsuarioView;
import co.solvers.apilearnlink.service.reacoesEmComentariosDoUsuario.QtdReacoesComentariosUsuarioService;
import co.solvers.apilearnlink.service.reacoesEmComentariosDoUsuario.dto.QtdContribuicoesRankingDto;
import co.solvers.apilearnlink.service.reacoesEmComentariosDoUsuario.dto.QtdReacoesComentariosUsuarioDto;
import co.solvers.apilearnlink.service.reacoesEmComentariosDoUsuario.dto.mapper.QtdContribuicoesRankingMapper;
import co.solvers.apilearnlink.service.reacoesEmComentariosDoUsuario.dto.mapper.QtdReacoesComentariosUsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/qtd-reacoes-comentario-usuarios")
@RequiredArgsConstructor
public class QtdReacoesComentariosUsuarioController {

    private final QtdReacoesComentariosUsuarioService qtdReacoesComentariosUsuarioService;

    @GetMapping("/buscar-nivel-de-classificacao-do-usuario/{id}")
    public ResponseEntity<Optional<QtdReacoesComentariosUsuarioDto>> listagemQtdReacoesComentarios(
            @PathVariable Long id){

        Optional<QtdReacoesComentariosUsuarioView> qtdReacoes = qtdReacoesComentariosUsuarioService.listagemQtdReacoesComentarios(id);

        Optional<QtdReacoesComentariosUsuarioDto> qtdReacoesDto = QtdReacoesComentariosUsuarioMapper.toDto(qtdReacoes);
        return ResponseEntity.ok(qtdReacoesDto);
    }

    @GetMapping("/buscar-nivel-de-classificacao-de-todos-usuarios")
    public ResponseEntity<List<QtdContribuicoesRankingDto>> qtdContribuicoesRanking(){
        List<QtdReacoesComentariosUsuarioView> qtdContribuicoesRanking = qtdReacoesComentariosUsuarioService.qtdContribuicoesRanking();

        List<QtdContribuicoesRankingDto> dtos = QtdContribuicoesRankingMapper.toDto(qtdContribuicoesRanking);
        return ResponseEntity.ok(dtos);
    }




}
