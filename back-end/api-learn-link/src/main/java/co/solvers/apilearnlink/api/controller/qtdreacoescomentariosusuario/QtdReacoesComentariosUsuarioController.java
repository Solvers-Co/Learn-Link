package co.solvers.apilearnlink.api.controller.qtdreacoescomentariosusuario;

import co.solvers.apilearnlink.domain.views.ReacoesEmComentariosDoUsuario.QtdReacoesComentariosUsuarioView;
import co.solvers.apilearnlink.service.reacoesemcomentariosdousuario.QtdReacoesComentariosUsuarioService;
import co.solvers.apilearnlink.service.reacoesemcomentariosdousuario.dto.QtdContribuicoesRankingDto;
import co.solvers.apilearnlink.service.reacoesemcomentariosdousuario.dto.QtdReacoesComentariosUsuarioDto;
import co.solvers.apilearnlink.service.reacoesemcomentariosdousuario.dto.mapper.QtdContribuicoesRankingMapper;
import co.solvers.apilearnlink.service.reacoesemcomentariosdousuario.dto.mapper.QtdReacoesComentariosUsuarioMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    
    @ApiResponse(responseCode = "200", description = "Quantidade de reações encontrada")
    @ApiResponse(responseCode = "404", description = "Nenhuma quantidade de reações encontrada")
    @Operation(summary = "Buscar quantidade de reações por comentário de um usuário", description = "Método que busca a quantidade de reações por comentário de um usuário", tags = {"Ranking"})
    @GetMapping("/buscar-nivel-de-classificacao-do-usuario/{id}")
    public ResponseEntity<Optional<QtdReacoesComentariosUsuarioDto>> listagemQtdReacoesComentarios(
            @PathVariable
            @Parameter(name = "id", description = "Usuario id", example = "1") Long id) {

        Optional<QtdReacoesComentariosUsuarioView> qtdReacoes = qtdReacoesComentariosUsuarioService.listagemQtdReacoesComentarios(id);

        Optional<QtdReacoesComentariosUsuarioDto> qtdReacoesDto = QtdReacoesComentariosUsuarioMapper.toDto(qtdReacoes);
        return ResponseEntity.ok(qtdReacoesDto);
    }

    @ApiResponse(responseCode = "200", description = "Quantidade de reações encontrada")
    @ApiResponse(responseCode = "404", description = "Nenhuma quantidade de reações encontrada")
    @Operation(summary = "Buscar quantidade de reações por comentário de todos os usuários", description = "Método que busca a quantidade de reações por comentário de todos os usuários", tags = {"Ranking"})
    @GetMapping("/buscar-nivel-de-classificacao-de-todos-usuarios")
    public ResponseEntity<List<QtdContribuicoesRankingDto>> qtdContribuicoesRanking() {

        List<QtdReacoesComentariosUsuarioView> qtdContribuicoesRanking = qtdReacoesComentariosUsuarioService.qtdContribuicoesRanking();

        List<QtdContribuicoesRankingDto> dtos = QtdContribuicoesRankingMapper.toDto(qtdContribuicoesRanking);
        return ResponseEntity.ok(dtos);
    }


}
