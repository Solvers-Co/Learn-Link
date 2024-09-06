package co.solvers.apilearnlink.api.controller.publicacao;

import co.solvers.apilearnlink.domain.canal.Canal;
import co.solvers.apilearnlink.domain.comentario.Comentario;
import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import co.solvers.apilearnlink.domain.reacao.Reacao;
import co.solvers.apilearnlink.service.comentario.ComentarioService;
import co.solvers.apilearnlink.service.comentario.dto.ComentarioCriacaoDto;
import co.solvers.apilearnlink.service.comentario.dto.ComentarioListagemDto;
import co.solvers.apilearnlink.service.comentario.dto.mapper.ComentarioMapper;
import co.solvers.apilearnlink.service.publicacao.PublicacaoService;
import co.solvers.apilearnlink.service.publicacao.dto.PublicacaoCriacaoRequestDto;
import co.solvers.apilearnlink.service.publicacao.dto.PublicacaoListagemResponseDto;
import co.solvers.apilearnlink.service.publicacao.dto.QuantidadePublicacaoDiaListagemDto;
import co.solvers.apilearnlink.service.publicacao.dto.QuantidadePublicacaoMesCanalListagemDto;
import co.solvers.apilearnlink.service.publicacao.dto.mapper.PublicacaoMapper;
import co.solvers.apilearnlink.service.reacao.ReacaoService;
import co.solvers.apilearnlink.service.reacao.dto.ReacaoCriarDto;
import co.solvers.apilearnlink.service.reacao.dto.ReacaoPublicacaoListarDto;
import co.solvers.apilearnlink.service.reacao.dto.mapper.ReacaoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/publicacoes")
@RequiredArgsConstructor
public class PublicacaoController {

    private final PublicacaoService publicacaoService;
    private final ComentarioService comentarioService;
    private final ReacaoService reacaoService;

    @ApiResponse(responseCode = "201", description = "Publicação criada com sucesso")
    @ApiResponse(responseCode = "400", description = "Tipo de publicação inválido")
    @Operation(summary = "Criar uma publicação", description = "Método que cria uma publicação", tags = {"Publicações"})
    @PostMapping
    public ResponseEntity<PublicacaoListagemResponseDto> publicar(
            @RequestBody @Valid PublicacaoCriacaoRequestDto novaPublicacao) {

        Publicacao publicacao = publicacaoService.criarPublicacao(novaPublicacao);
        PublicacaoListagemResponseDto dto = PublicacaoMapper.toDto(publicacao);
        return ResponseEntity.status(201).body(dto);
    }

    @ApiResponse(responseCode = "204", description = "Publicações vazias")
    @ApiResponse(responseCode = "200", description = "Publicações encontradas")
    @Operation(summary = "Listar todas as publicações", description = "Método que Lista todas as publicações", tags = {"Publicações"})
    @GetMapping("/publicacoes-mais-recentes")
    public ResponseEntity<List<PublicacaoListagemResponseDto>> listarPublicacoes() {

        List<Publicacao> publicacoes = publicacaoService.listarMaisRecentesPilha();
        List<PublicacaoListagemResponseDto> dtos = PublicacaoMapper.toDto(publicacoes);

        if (dtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(200).body(dtos);
    }

    //Listar publicacoes paginado

    @ApiResponse(responseCode = "204", description = "Publicações vazias")
    @ApiResponse(responseCode = "200", description = "Publicações encontradas")
    @Operation(summary = "Listar todas as publicações de maneira páginada", description = "Método que Lista todas as publicações paginadas", tags = {"Publicações"})
    @GetMapping("/publicacoes-mais-recentes-paginado")
    public ResponseEntity<Page<PublicacaoListagemResponseDto>> listarPublicacoes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("dataHora").descending());
        Page<Publicacao> publicacoesPage = publicacaoService.listarMaisRecentesPaginado(pageable);
        Page<PublicacaoListagemResponseDto> dtosPage = publicacoesPage.map(PublicacaoMapper::toDto);

        if (dtosPage.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dtosPage);
    }

    @ApiResponse(responseCode = "204", description = "Publicações vazias")
    @ApiResponse(responseCode = "200", description = "Publicações encontradas")
    @Operation(summary = "Listar publicações por canal e ordenação de forma páginada", description = "Método que lista publicações filtradas por canal e ordenadas", tags = {"Publicações"})
    @GetMapping("/publicacoes-por-canal-paginado")
    public ResponseEntity<Page<PublicacaoListagemResponseDto>> listarPublicacoesPorCanal(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "DESC") String sortDirection,
            @RequestParam Long canalId) {

        Page<Publicacao> publicacoesPage = publicacaoService.listarPublicacoesPorCanal(canalId, page, size, sortDirection);
        Page<PublicacaoListagemResponseDto> dtosPage = publicacoesPage.map(PublicacaoMapper::toDto);

        if (dtosPage.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dtosPage);
    }

    @ApiResponse(responseCode = "204", description = "Publicações vazias")
    @ApiResponse(responseCode = "200", description = "Publicações encontradas")
    @Operation(summary = "Listar todas as publicações pelo horário mais antigo", description = "Método que Lista todas as publicações começando da mais antiga para a mais recente", tags = {"Publicações"})
    @GetMapping("/publicacoes-mais-antigas")
    public ResponseEntity<List<PublicacaoListagemResponseDto>> listarPublicacoesMaisAntigas() {

        List<Publicacao> publicacoes = publicacaoService.listarMaisAntigo();
        List<PublicacaoListagemResponseDto> dtos = PublicacaoMapper.toDto(publicacoes);

        if (dtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(200).body(dtos);
    }

    @ApiResponse(responseCode = "400", description = "Tipo de publicação inválido")
    @ApiResponse(responseCode = "204", description = "Publicação vazia")
    @ApiResponse(responseCode = "200", description = "Publicações encontradas")
    @Operation(summary = "Listar publicação por tipo", description = "Método que Lista todas as publicações pelo tipo", tags = {"Publicações"})
    @GetMapping("/tipo")
    public ResponseEntity<List<PublicacaoListagemResponseDto>> listarPublicacoesTipo(
            @RequestParam
            @Parameter(name = "tipoPublicacao", description = "Tipo publicação", example = "COMUM") String tipoPublicacao) {

        List<Publicacao> publicacoes = publicacaoService.listarPorTipo(tipoPublicacao);
        List<PublicacaoListagemResponseDto> dtos = PublicacaoMapper.toDto(publicacoes);

        if (dtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(200).body(dtos);
    }

    @ApiResponse(responseCode = "404", description = "Publicação não encontrada")
    @ApiResponse(responseCode = "200", description = "Publicação encontrada")
    @Operation(summary = "Listar por id", description = "Método que retorna um registro", tags = {"Publicações"})
    @GetMapping("/{id}")
    public ResponseEntity<PublicacaoListagemResponseDto> listarPublicacaoID(
            @PathVariable
            @Parameter(name = "id", description = "Publicação id", example = "1") int id) {

        Publicacao publicacao = publicacaoService.listarPorId(id);
        PublicacaoListagemResponseDto dto = PublicacaoMapper.toDto(publicacao);

        return ResponseEntity.status(200).body(dto);
    }

    @ApiResponse(responseCode = "404", description = "Publicação não encontrada")
    @ApiResponse(responseCode = "200", description = "Publicação atualizada")
    @ApiResponse(responseCode = "400", description = "Conteúdo inválido")
    @Operation(summary = "Editar conteúdo", description = "Método que edita o conteúdo de uma publicação", tags = {"Publicações"})
    @PatchMapping("/{id}/conteudo")
    public ResponseEntity<PublicacaoListagemResponseDto> editarConteudo(
            @PathVariable
            @Parameter(name = "id", description = "Publicação id", example = "1") int id,
            @RequestParam
            @Parameter(name = "novoConteudo", description = "Novo conteúdo", example = "Qual o valor de PI?") String novoConteudo,
            @RequestParam
            @Parameter(name = "novoCanalId", description = "ID do novo canal", example = "Matemática") String novoCanal
    ) {

        Publicacao publicacaoAlterada = publicacaoService.editarConteudo(id, novoConteudo, novoCanal);
        PublicacaoListagemResponseDto dto = PublicacaoMapper.toDto(publicacaoAlterada);
        return ResponseEntity.ok(dto);
    }

    @ApiResponse(responseCode = "404", description = "Publicação não encontrada")
    @ApiResponse(responseCode = "204", description = "Publicação excluída")
    @Operation(summary = "Excluir publicação", description = "Método que exclui uma publicação", tags = {"Publicações"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable
            @Parameter(name = "id", description = "Publicação id", example = "1") Integer id) {

        publicacaoService.deletar(id);
        return ResponseEntity.status(204).build();
    }

    //swagger incompleto
    @ApiResponse(responseCode = "200", description = "Comentário criado com sucesso")
    @ApiResponse(responseCode = "404", description = "Publicação não encontrada")
    @Operation(summary = "Comentar em uma publicação", description = "Método que comenta em uma publicação", tags = {"Publicações"})
    @PostMapping("/{idPublicacao}/comentar")
    public ResponseEntity<ComentarioListagemDto> comentar(
            @PathVariable
            @Parameter(name = "idPublicacao", description = "Publicação id", example = "1") int idPublicacao,
            @RequestBody @Valid ComentarioCriacaoDto novoComentario) {

        Comentario comentario = comentarioService.comentar(idPublicacao, novoComentario);
        return ResponseEntity.ok(ComentarioMapper.toDto(comentario));
    }

    @ApiResponse(responseCode = "204", description = "Publicações vazias")
    @ApiResponse(responseCode = "200", description = "Publicações encontradas")
    @ApiResponse(responseCode = "400", description = "Palavra chave inválida")
    @Operation(summary = "Listar publicações por palavra chave", description = "Método que lista todas as publicações por uma palavra chave ordenada pela mais recete", tags = {"Publicações"})
    @GetMapping("/buscar-palavra-chave")
    public ResponseEntity<List<PublicacaoListagemResponseDto>> buscarPublicacaoPorPalavraChave(
            @RequestParam
            @Parameter(name = "palavraChave", description = "Palavra chave", example = "Bhaskara") String palavraChave) {

        List<Publicacao> publicacoes = publicacaoService.listarPorPalavraChave(palavraChave);
        List<PublicacaoListagemResponseDto> dtos = PublicacaoMapper.toDto(publicacoes);

        if (dtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(200).body(dtos);
    }

//    @ApiResponse(responseCode = "200", description = "Publicações encontradas")
//    @ApiResponse(responseCode = "204", description = "Publicações vazias")
//    @Operation(summary = "Quantidade de publicações por dia", description = "Método que retorna a quantidade de publicações por dia", tags = {"Publicações"})
//    @GetMapping("/quantidade-publicacoes-por-dia-mes")
//    public ResponseEntity<String[][]> quantidadeDePublicacoesPorDia(
//            @RequestParam
//            @Parameter(name = "mes", description = "Mês do ano", example = "5") int mes,
//            @RequestParam
//            @Parameter(name = "ano", description = "Ano Publicação", example = "2024") int ano) {
//        String[][] quantidadePublicacoes = publicacaoService.buscaQuantidadeDePublicacoesPorDiaMatriz(mes, ano);
//
//        if (quantidadePublicacoes == null) return ResponseEntity.noContent().build();
//
//        return ResponseEntity.ok(quantidadePublicacoes);
//    }

    @ApiResponse(responseCode = "204", description = "Nenhuma publicação encontrada")
    @ApiResponse(responseCode = "200", description = "Publicações encontradas")
    @Operation(summary = "Listar quantidade de publicações por dia", description = "Método que lista a quantidade de publicações por dia em um determinado mês e ano", tags = {"Publicações"})
    @GetMapping("/quantidade-por-dia")
    public ResponseEntity<List<QuantidadePublicacaoDiaListagemDto>> listarQuantidadeDePublicacaoPorDia(
            @RequestParam int mes, @RequestParam int ano) {

        List<QuantidadePublicacaoDiaListagemDto> publicacoes = publicacaoService.listarQuantidadeDePublicacaoPorDia(mes, ano);

        if (publicacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(200).body(publicacoes);
    }


    @ApiResponse(responseCode = "200", description = "Publicações encontradas")
    @ApiResponse(responseCode = "204", description = "Publicações vazias")
    @Operation(summary = "Listar a quantidade de publicações por canal", description = "Método que retorna a quantidade de publicações por canal", tags = {"Publicações"})
    @GetMapping("/quantidade-de-publicacoes-em-cada-canal")
    public ResponseEntity<List<QuantidadePublicacaoMesCanalListagemDto>> buscarQuantidadeDePublicacoesEmCadaCanal(
            @RequestParam
            @Parameter(name = "mes", description = "Mês do ano", example = "5") int mes,
            @RequestParam
            @Parameter(name = "ano", description = "Ano Publicação", example = "2024") int ano) {
        List<QuantidadePublicacaoMesCanalListagemDto> quantidadePublicacoesEmCadaCanal = publicacaoService.buscaQuantidadePublicacoesEmCadaCanal(mes, ano);

        if (quantidadePublicacoesEmCadaCanal.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(quantidadePublicacoesEmCadaCanal);
    }

    @ApiResponse(responseCode = "200", description = "Canal encontrado")
    @ApiResponse(responseCode = "404", description = "Canal não encontrado")
    @Operation(summary = "Canal com maior número de publicações", description = "Método que retorna o canal com maior número de publicações", tags = {"Publicações"})
    @GetMapping("/canal-com-maior-numero-de-publicacoes")
    public ResponseEntity<QuantidadePublicacaoMesCanalListagemDto> buscarCanalComMaiorNumeroDePublicacoes(
            @RequestParam
            @Parameter(name = "mes", description = "Mês do ano", example = "8") int mes,
            @RequestParam
            @Parameter(name = "ano", description = "Ano Publicação", example = "2024") int ano) {
        QuantidadePublicacaoMesCanalListagemDto canalMaisPublicacoes = publicacaoService.buscaCanalComMaiorNumeroDePublicacoes(mes, ano);

        return ResponseEntity.ok(canalMaisPublicacoes);
    }

    @ApiResponse(responseCode = "200", description = "Comentário reagido")
    @ApiResponse(responseCode = "404", description = "Comentário não encontrado")
    @Operation(summary = "Reagir a uma publicação", description = "Método que reage a uma publicação", tags = {"Publicações"})
    @PostMapping("/{idPublicacao}/reagir")
    public ResponseEntity<ReacaoPublicacaoListarDto> reagirPublicacao(
            @PathVariable
            @Parameter(name = "idPublicacao", description = "Publicação id", example = "1") int idPublicacao,
            @RequestBody ReacaoCriarDto reacaoCriarDto) {

        Reacao reacao = reacaoService.reagirPublicacao(idPublicacao, reacaoCriarDto);
        ReacaoPublicacaoListarDto reacaoDto = ReacaoMapper.toReacaoPublicacaoListarDto(reacao);
        return ResponseEntity.created(null).body(reacaoDto);
    }

    @ApiResponse(responseCode = "200", description = "Reação removida")
    @ApiResponse(responseCode = "404", description = "Reação não encontrada")
    @Operation(summary = "Remover reação de uma publicação", description = "Método que remove uma reação de uma publicação", tags = {"Publicações"})
    @DeleteMapping("/{idPublicacao}/remover-reacao")
    public ResponseEntity<Void> removerReacaoPublicacao(
            @PathVariable
            @Parameter(name = "idPublicacao", description = "Publicação id", example = "1") int idPublicacao,
            @RequestBody ReacaoCriarDto reacaoCriarDto) {

        reacaoService.removerReacaoPublicacao(idPublicacao, reacaoCriarDto);
        return ResponseEntity.ok().build();
    }

//    @PostMapping("/comentarios")
//    public ResponseEntity<Publicacao> comentar(@RequestBody Publicacao comentario) {
//
//        Publicacao publicacao = buscarPublicacaoPorID(comentario.getFkPublicacao());
//
//        if (publicacao == null) {
//            return ResponseEntity.status(404).build();
//        }
//
//        if (publicacao.getDescricao() == null ||
//                publicacao.getFkPublicacao() == 0) {
//            return ResponseEntity.status(400).build();
//        }
//
//        publicacao.setComentarios(comentario);
//
//        return ResponseEntity.status(201).body(comentario);
//    }
//
//    @GetMapping("/{id}/comentarios")
//    public ResponseEntity<List<Publicacao>> listarComentarios(@PathVariable int id) {
//
//        Publicacao publicacao = buscarPublicacaoPorID(id);
//
//        if (publicacao == null) {
//            return ResponseEntity.status(404).build();
//        }
//
//        if (publicacao.getComentarios().isEmpty()) {
//            return ResponseEntity.status(204).build();
//        }
//
//        return ResponseEntity.status(200).body(publicacao.getComentarios());
//    }


//    @PutMapping("/{id}")
//    public ResponseEntity<Publicacao> editarPublicacao(@PathVariable int id, @RequestBody Publicacao publicacao) {
//
//        Publicacao publicacaoAtualizar = buscarPublicacaoPorID(id);
//
//        if (publicacaoAtualizar == null) {
//            ResponseEntity.status(404).build();
//        }
//
//        switch (publicacao.getTipoPublicacao()) {
//            case COMUM:
//
//                if (publicacao.getTitulo() == null || publicacao.getDescricao() == null) {
//                    ResponseEntity.status(400).build();
//                }
//
//                publicacaoAtualizar.setTitulo(publicacao.getTitulo());
//                publicacaoAtualizar.setDescricao(publicacao.getDescricao());
//
//                return ResponseEntity.status(200).body(publicacaoAtualizar);
//
//            case COMENTARIO:
//
//                if (publicacao.getDescricao() == null) {
//                    ResponseEntity.status(400).build();
//                }
//
//                publicacaoAtualizar.setDescricao(publicacao.getDescricao());
//
//                return ResponseEntity.status(200).body(publicacaoAtualizar);
//        }
//
//        return ResponseEntity.status(400).build();
//    }


//    public Publicacao buscarPublicacaoPorID(int id) {
//
//        return publicacoes
//                .stream()
//                .filter(publicacao -> publicacao.getId() == id)
//                .findFirst()
//                .orElse(null);
//    }


}
