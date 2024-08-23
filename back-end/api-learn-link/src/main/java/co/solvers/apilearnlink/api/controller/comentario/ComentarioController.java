package co.solvers.apilearnlink.api.controller.comentario;

import co.solvers.apilearnlink.domain.comentario.Comentario;
import co.solvers.apilearnlink.domain.reacao.Reacao;
import co.solvers.apilearnlink.service.comentario.ComentarioService;
import co.solvers.apilearnlink.service.comentario.dto.ComentarioListagemDto;
import co.solvers.apilearnlink.service.comentario.dto.mapper.ComentarioMapper;
import co.solvers.apilearnlink.service.reacao.ReacaoService;
import co.solvers.apilearnlink.service.reacao.dto.ReacaoComentarioListarDto;
import co.solvers.apilearnlink.service.reacao.dto.ReacaoCriarDto;
import co.solvers.apilearnlink.service.reacao.dto.mapper.ReacaoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
@RequiredArgsConstructor
public class ComentarioController {

    private final ComentarioService comentarioService;
    private final ReacaoService reacaoService;

    @ApiResponse(responseCode = "200", description = "Comentário encontrado")
    @ApiResponse(responseCode = "404", description = "Comentário não encontrado")
    @Operation(summary = "Listar por id", description = "Método que lista um comentário pelo id", tags = {"Comentários"})
    @GetMapping("/{id}")
    public ResponseEntity<ComentarioListagemDto> buscarPorId(
            @PathVariable
            @Parameter(name = "id", description = "Comentário id", example = "1") int id) {
        Comentario comentario = comentarioService.buscarPorId(id);
        ComentarioListagemDto comentarioListagemDto = ComentarioMapper.toDto(comentario);
        return ResponseEntity.ok(comentarioListagemDto);
    }

    @ApiResponse(responseCode = "200", description = "Comentário atualizado")
    @ApiResponse(responseCode = "404", description = "Comentário não encontrado")
    @Operation(summary = "Editar comentário", description = "Método que atualiza um comentário", tags = {"Comentários"})
    @PatchMapping("/{id}")
    public ResponseEntity<ComentarioListagemDto> editarComentario(
            @PathVariable
            @Parameter(name = "id", description = "Comentário id", example = "1") int id,
            @RequestParam
            @Parameter(name = "comentarioAlterar", description = "Novo comentário", example = "A resposta é 4!") String comentarioAlterar) {
        Comentario comentarioEditado = comentarioService.editarComentario(id, comentarioAlterar);
        ComentarioListagemDto comentarioListagemDto = ComentarioMapper.toDto(comentarioEditado);
        return ResponseEntity.ok(comentarioListagemDto);
    }

    @ApiResponse(responseCode = "200", description = "Comentário reagido")
    @ApiResponse(responseCode = "404", description = "Comentário não encontrado")
    @Operation(summary = "Reagir a um comentário", description = "Método que reage a um comentário", tags = {"Comentários"})
    @PostMapping("/{idComentario}/reagir")
    public ResponseEntity<ReacaoComentarioListarDto> reagirComentario(
            @PathVariable
            @Parameter(name = "idComentario", description = "Comentário id", example = "1") int idComentario,
            @RequestBody ReacaoCriarDto reacaoCriarDto) {

        Reacao reacao = reacaoService.reagirComentario(idComentario, reacaoCriarDto);
        ReacaoComentarioListarDto reacaoDto = ReacaoMapper.toReacaoComentarioListarDto(reacao);
        return ResponseEntity.created(null).body(reacaoDto);
    }

    @ApiResponse(responseCode = "204", description = "Comentário deletado")
    @ApiResponse(responseCode = "404", description = "Comentário não encontrado")
    @Operation(summary = "Remover reacão", description = "Método que remove a reacao", tags = {"Comentários"})
    @DeleteMapping("/{idComentario}/reagir/{idReacao}")
    public ResponseEntity<Void> removerReacao(
            @PathVariable
            @Parameter(name = "idComentario", description = "Comentário id", example = "1") int idComentario,
            @PathVariable
            @Parameter(name = "idReacao", description = "Reação id", example = "1") int idReacao) {
        reacaoService.removerReacao(idComentario, idReacao);
        return ResponseEntity.noContent().build();
    }

    @ApiResponse(responseCode = "200", description = "Comentários encontrados")
    @ApiResponse(responseCode = "404", description = "Comentários não encontrado")
    @Operation(summary = "Listar todos os comentários", description = "Método que lista todos os comentários", tags = {"Comentários"})
    @GetMapping("/quantidade-comentarios-por-dia-mes")
    public ResponseEntity<String[][]> quantidadeDePublicacoesPorDia(
            @RequestParam
            @Parameter(name = "mes", description = "Mês", example = "5") int mes,
            @RequestParam
            @Parameter(name = "ano", description = "Ano", example = "2024") int ano) {
        String[][] quantidadeComentarios = comentarioService.buscaQuantidadeDeComentariosPorDiaMatriz(mes, ano);

        if (quantidadeComentarios == null) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(quantidadeComentarios);
    }

    //endpoint que lista todos os comentarios de uma publicacao pelo id da publicação
    @ApiResponse(responseCode = "200", description = "Comentários encontrados")
    @ApiResponse(responseCode = "404", description = "Comentários não encontrado")
    @Operation(summary = "Listar todos os comentários de uma publicação", description = "Método que lista todos os comentários de uma publicação", tags = {"Comentários"})
    @GetMapping("/publicacao/{idPublicacao}")
    public ResponseEntity<List<ComentarioListagemDto>> listarComentariosPorPublicacao(
            @PathVariable
            @Parameter(name = "idPublicacao", description = "Publicação id", example = "1") int idPublicacao) {
        List<Comentario> comentarios = comentarioService.listarPorPublicacao(idPublicacao);
        List<ComentarioListagemDto> comentarioListagemDto = ComentarioMapper.toDto(comentarios);
        return ResponseEntity.ok(comentarioListagemDto);
    }

}
