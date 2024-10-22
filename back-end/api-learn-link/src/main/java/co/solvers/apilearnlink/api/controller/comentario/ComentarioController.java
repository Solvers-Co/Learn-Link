package co.solvers.apilearnlink.api.controller.comentario;

import co.solvers.apilearnlink.domain.comentario.Comentario;
import co.solvers.apilearnlink.domain.reacao.Reacao;
import co.solvers.apilearnlink.domain.views.comentariosDenunciados.ComentariosDenunciados;
import co.solvers.apilearnlink.domain.views.publicacoesDenunciadas.PublicacoesDenunciadas;
import co.solvers.apilearnlink.service.comentario.ComentarioService;
import co.solvers.apilearnlink.service.comentario.dto.ComentarioListagemDto;
import co.solvers.apilearnlink.service.comentario.dto.ComentariosDenunciadosListagemComIa;
import co.solvers.apilearnlink.service.comentario.dto.QuantidadeComentarioDiaListagemDto;
import co.solvers.apilearnlink.service.comentario.dto.mapper.ComentarioMapper;
import co.solvers.apilearnlink.service.comentariosDenunciados.dto.ComentariosDenunciadosDto;
import co.solvers.apilearnlink.service.comentariosDenunciados.dto.mapper.ComentariosDenunciadosMapper;
import co.solvers.apilearnlink.service.denuncia.DenunciaService;
import co.solvers.apilearnlink.service.denuncia.dto.DenunciaComentarioCriarDto;
import co.solvers.apilearnlink.service.denuncia.dto.DenunciaComentarioListagemDto;
import co.solvers.apilearnlink.service.denuncia.dto.DenunciaPublicacaoCriarDto;
import co.solvers.apilearnlink.service.denuncia.dto.DenunciaPublicacaoListagemDto;
import co.solvers.apilearnlink.service.denuncia.dto.mapper.DenunciaMapper;
import co.solvers.apilearnlink.service.publicacao.dto.PublicacaoDenunciadasListagemComIa;
import co.solvers.apilearnlink.service.publicacoesDenunciadas.dto.PublicacoesDenunciadasDto;
import co.solvers.apilearnlink.service.publicacoesDenunciadas.dto.mapper.PublicacoesDenunciadasMapper;
import co.solvers.apilearnlink.service.reacao.ReacaoService;
import co.solvers.apilearnlink.service.reacao.dto.ReacaoComentarioListarDto;
import co.solvers.apilearnlink.service.reacao.dto.ReacaoCriarDto;
import co.solvers.apilearnlink.service.reacao.dto.mapper.ReacaoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/comentarios")
@RequiredArgsConstructor
public class ComentarioController {

    private final ComentarioService comentarioService;
    private final ReacaoService reacaoService;
    private final DenunciaService denunciaService;

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
    @Operation(summary = "Excluir comentário", description = "Método que exclui o comentário", tags = {"Comentários"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirComentario(
            @PathVariable
            @Parameter(name = "idComentario", description = "Comentário id", example = "1") int id) {

        comentarioService.deletar(id);
        return ResponseEntity.status(204).build();
    }

    @ApiResponse(responseCode = "204", description = "Comentário deletado")
    @ApiResponse(responseCode = "404", description = "Comentário não encontrado")
    @Operation(summary = "Remover reacão", description = "Método que remove a reacao", tags = {"Comentários"})
    @DeleteMapping("/{idComentario}/reagir")
    public ResponseEntity<Void> removerReacao(
            @PathVariable
            @Parameter(name = "idComentario", description = "Comentário id", example = "1") int idComentario,
            @RequestBody ReacaoCriarDto reacaoCriarDto) {
        reacaoService.removerReacaoComentario(idComentario, reacaoCriarDto);
        return ResponseEntity.noContent().build();
    }

    @ApiResponse(responseCode = "200", description = "Comentários encontrados")
    @ApiResponse(responseCode = "404", description = "Comentários não encontrados")
    @Operation(summary = "Listar quantidade de comentários por dia e mês", description = "Método que lista a quantidade de comentários por dia em um determinado mês e ano", tags = {"Comentários"})
    @GetMapping("/quantidade-comentarios-por-dia-mes")
    public ResponseEntity<List<QuantidadeComentarioDiaListagemDto>> quantidadeDeComentariosPorDia(
            @RequestParam @Parameter(name = "mes", description = "Mês", example = "5") int mes,
            @RequestParam @Parameter(name = "ano", description = "Ano", example = "2024") int ano) {

        List<QuantidadeComentarioDiaListagemDto> quantidadeComentarios = comentarioService.buscaQuantidadeDeComentariosPorDia(mes, ano);

        if (quantidadeComentarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

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

    @ApiResponse(responseCode = "200", description = "Comentários encontrados")
    @ApiResponse(responseCode = "404", description = "Comentários não encontrados")
    @Operation(summary = "Listar todos os comentários de uma publicação", description = "Método que lista todos os comentários de uma publicação", tags = {"Comentários"})
    @GetMapping("/publicacao/{idPublicacao}/paginado")
    public ResponseEntity<Page<ComentarioListagemDto>> listarComentariosPorPublicacao(
            @PathVariable @Parameter(name = "idPublicacao", description = "Publicação id", example = "1") int idPublicacao,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("dataHora").descending());
        Page<Comentario> comentariosPage = comentarioService.listarPorPublicacaoPaginado(idPublicacao, pageable);
        Page<ComentarioListagemDto> dtosPage = comentariosPage.map(ComentarioMapper::toDto);

        if (dtosPage.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(dtosPage);
    }

    @ApiResponse(responseCode = "200", description = "Denuncia efetuada")
    @ApiResponse(responseCode = "404", description = "Usuario/Publicação não encontrada")
    @Operation(summary = "Denunciar um comentário", description = "Método que denúncia um comentário", tags = {"Comentários"})
    @PostMapping("/{idComentario}/denunciar")
    public ResponseEntity<DenunciaComentarioListagemDto> denunciarComentario(
            @PathVariable
            @Parameter(name = "idComentario", description = "Comentario id", example = "1") int idComentario,
            @RequestBody DenunciaComentarioCriarDto denunciaComentarioCriarDto) {

        DenunciaComentarioListagemDto denunciaComentario = DenunciaMapper.toDenunciaComentarioListagemDto(denunciaService.denunciarComentario(idComentario, denunciaComentarioCriarDto));

        return ResponseEntity.ok().body(denunciaComentario);
    }

    @ApiResponse(responseCode = "200", description = "Comentarios Denunciados")
    @ApiResponse(responseCode = "404", description = "Não existem comentarios denunciados")
    @Operation(summary = "Listar comentarios denunciados", description = "Método que lista comentarios denunciados", tags = {"Comentários"})
    @GetMapping("/denuncias")
    public ResponseEntity<List<ComentariosDenunciadosDto>> listarPublicacoesDenunciadas() {

        List<ComentariosDenunciados> comentariosDenunciados = denunciaService.buscaComentariosDenunciados();
        List<ComentariosDenunciadosDto> comentariosDenunciadosDto = ComentariosDenunciadosMapper.toComentariosDenunciadosDto(comentariosDenunciados);

        if (comentariosDenunciadosDto.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(comentariosDenunciadosDto);
        }
    }

    @GetMapping("/denuncias/ordenadasPorIa")
    public ResponseEntity<List<ComentariosDenunciadosListagemComIa>> listarComentariosDenunciadasOrdenadasPorIa() {
        try {
            List<ComentariosDenunciadosListagemComIa> comentariosClassificados = denunciaService.buscaComentariosDenunciadosOrdenadosPorIa();
            return ResponseEntity.ok(comentariosClassificados);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @ApiResponse(responseCode = "200", description = "Denuncias removidas")
    @ApiResponse(responseCode = "404", description = "Comentario não encontrado")
    @Operation(summary = "Remover denúncias", description = "Método que remover todas as denuncias de um comentário", tags = {"Publicações"})
    @DeleteMapping("/{idComentario}/remover-denuncias")
    public ResponseEntity<Void> removerDenuncias(@PathVariable int idComentario) {

        denunciaService.removerDenunciasComentario(idComentario);

        return ResponseEntity.ok().build();
    }

}
