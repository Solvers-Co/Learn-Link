package co.solvers.apilearnlink.api.controller.publicacao;

import co.solvers.apilearnlink.domain.comentario.Comentario;
import co.solvers.apilearnlink.domain.publicacao.Publicacao;
import co.solvers.apilearnlink.domain.reacao.Reacao;
import co.solvers.apilearnlink.domain.views.publicacoesDenunciadas.PublicacoesDenunciadas;
import co.solvers.apilearnlink.service.comentario.ComentarioService;
import co.solvers.apilearnlink.service.comentario.dto.ComentarioCriacaoDto;
import co.solvers.apilearnlink.service.comentario.dto.ComentarioListagemDto;
import co.solvers.apilearnlink.service.comentario.dto.mapper.ComentarioMapper;
import co.solvers.apilearnlink.service.denuncia.DenunciaService;
import co.solvers.apilearnlink.service.denuncia.dto.DenunciaPublicacaoCriarDto;
import co.solvers.apilearnlink.service.denuncia.dto.DenunciaPublicacaoListagemDto;
import co.solvers.apilearnlink.service.imagem.ImagemPerfilDto;
import co.solvers.apilearnlink.service.publicacao.dto.*;
import co.solvers.apilearnlink.service.publicacoesdenunciadas.dto.PublicacoesDenunciadasDto;
import co.solvers.apilearnlink.service.denuncia.dto.mapper.DenunciaMapper;
import co.solvers.apilearnlink.service.publicacao.PublicacaoService;
import co.solvers.apilearnlink.service.publicacao.dto.mapper.PublicacaoMapper;
import co.solvers.apilearnlink.service.publicacoesdenunciadas.dto.mapper.PublicacoesDenunciadasMapper;
import co.solvers.apilearnlink.service.reacao.ReacaoService;
import co.solvers.apilearnlink.service.reacao.dto.ReacaoCriarDto;
import co.solvers.apilearnlink.service.reacao.dto.ReacaoPublicacaoListarDto;
import co.solvers.apilearnlink.service.reacao.dto.mapper.ReacaoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;


import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/publicacoes")
@RequiredArgsConstructor
public class PublicacaoController {

    private final PublicacaoService publicacaoService;
    private final ComentarioService comentarioService;
    private final ReacaoService reacaoService;
    private final DenunciaService denunciaService;

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


    //Listar publicacoes paginado
    @ApiResponse(responseCode = "204", description = "Publicações vazias")
    @ApiResponse(responseCode = "200", description = "Publicações encontradas")
    @Operation(summary = "Listar todas as publicações de maneira páginada", description = "Método que Lista todas as publicações paginadas", tags = {"Publicações"})
    @GetMapping("/publicacoes-mais-recentes-paginado")
    public ResponseEntity<Page<PublicacaoListagemResponseDto>> listarPublicacoes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "desc") String sortDirection) {

        // Define a direção do sort (descendente ou ascendente)
        Sort.Direction direction = sortDirection.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, "dataHora"));
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
            @Parameter(name = "novoCanalId", description = "ID do novo canal", example = "Matemática") String novoCanal,
            @RequestBody(required = false)
            @Parameter(name = "imageBytes", description =  "Array de bytes da imagem") ImagemPerfilDto imagemUrl
    ) {

        Publicacao publicacaoAlterada = publicacaoService.editarConteudo(id, novoConteudo, novoCanal, imagemUrl.getImagemBytes());
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
    @Operation(summary = "Listar publicações por palavra chave com paginação", description = "Método que lista todas as publicações por uma palavra chave de maneira paginada e ordenada pela mais recente", tags = {"Publicações"})
    @GetMapping("/buscar-palavra-chave-paginado")
    public ResponseEntity<Page<PublicacaoListagemResponseDto>> buscarPublicacaoPorPalavraChavePaginado(
            @RequestParam
            @Parameter(name = "palavraChave", description = "Palavra chave", example = "Bhaskara") String palavraChave,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "desc") String sortDirection) {

        // Define a direção do sort (descendente ou ascendente)
        Sort.Direction direction = sortDirection.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, "dataHora"));
        Page<Publicacao> publicacoesPage = publicacaoService.listarPorPalavraChavePaginado(palavraChave, pageable);
        Page<PublicacaoListagemResponseDto> dtosPage = publicacoesPage.map(PublicacaoMapper::toDto);

        if (dtosPage.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dtosPage);
    }

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

    @ApiResponse(responseCode = "200", description = "Denuncia efetuada")
    @ApiResponse(responseCode = "404", description = "Usuario/Publicação não encontrada")
    @Operation(summary = "Denunciar uma publicação", description = "Método que denúncia uma publicação", tags = {"Publicações"})
    @PostMapping("/{idPublicacao}/denunciar")
    public ResponseEntity<DenunciaPublicacaoListagemDto> denunciarPublicacao(
            @PathVariable
            @Parameter(name = "idPublicacao", description = "Publicação id", example = "1") int idPublicacao,
            @RequestBody DenunciaPublicacaoCriarDto denunciaPublicacaoCriarDto) {

        DenunciaPublicacaoListagemDto denunciaPublicacao = DenunciaMapper.toDenunciaPublicacaoListagemDto(denunciaService.denunciarPublicacao(idPublicacao, denunciaPublicacaoCriarDto));

        return ResponseEntity.ok().body(denunciaPublicacao);
    }

    @ApiResponse(responseCode = "200", description = "Publicacoes Denunciadas")
    @ApiResponse(responseCode = "404", description = "Não existem publicações denunciadas")
    @Operation(summary = "Listar publicações denunciadas", description = "Método que lista publicações denunciadas", tags = {"Publicações"})
    @GetMapping("/denuncias")
    public ResponseEntity<List<PublicacoesDenunciadasDto>> listarPublicacoesDenunciadas() {

        List<PublicacoesDenunciadas> publicacoesDenunciadas = denunciaService.buscaPublicacoesDenunciadas();
        List<PublicacoesDenunciadasDto> publicacoesDenunciadasDto = PublicacoesDenunciadasMapper.toPublicacoesDenunciadasDto(publicacoesDenunciadas);

        if (publicacoesDenunciadasDto.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(publicacoesDenunciadasDto);
        }
    }

    @GetMapping("/denuncias/ordenadasPorIa")
    public ResponseEntity<List<PublicacaoDenunciadasListagemComIa>> listarPublicacoesDenunciadasOrdenadasPorIa() {
        try {
            List<PublicacaoDenunciadasListagemComIa> publicacoesClassificadas = denunciaService.buscaPublicacoesDenunciadasOrdenadasPorIa();
            return ResponseEntity.ok(publicacoesClassificadas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @ApiResponse(responseCode = "200", description = "CSV de Denúncias")
    @ApiResponse(responseCode = "404", description = "Não existem denúncias para o tipo especificado")
    @Operation(summary = "CSV de denúncias", description = "Método que grava CSV das denúncias de publicações ou comentários", tags = {"Denúncias"})
    @GetMapping(value = "/denuncias/csv", produces = "text/csv")
    public ResponseEntity<Resource> gravarCsvDenuncias(@RequestParam String tipo) throws IOException {
        Resource resource = denunciaService.gravaDenuncias(tipo);

        if (resource == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("text/csv"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + tipo + "s_denunciados.csv\"")
                .body(resource);
    }

    @ApiResponse(responseCode = "200", description = "Denúncias TXT")
    @ApiResponse(responseCode = "404", description = "Não existem denúncias para o tipo especificado")
    @Operation(summary = "TXT de denúncias", description = "Método que grava TXT das denúncias de publicações ou comentários", tags = {"Denúncias"})
    @GetMapping(value = "/denuncias/txt", produces = "text/plain")
    public ResponseEntity<Resource> gravarTxtDenuncias(@RequestParam String tipo) throws IOException {
        Resource resource = denunciaService.gravaTxtDenuncias(tipo);

        if (resource == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("text/plain"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + tipo + "s_denunciados.txt\"")
                .body(resource);
    }

    @ApiResponse(responseCode = "200", description = "Denúncias JSON")
    @ApiResponse(responseCode = "404", description = "Não existem denúncias para o tipo especificado")
    @Operation(summary = "JSON de denúncias", description = "Método que grava JSON das denúncias de publicações ou comentários", tags = {"Denúncias"})
    @GetMapping(value = "/denuncias/json", produces = "application/json")
    public ResponseEntity<Resource> gravarJsonDenuncias(@RequestParam String tipo) throws IOException {
        Resource resource = denunciaService.gravaJsonDenuncias(tipo);

        if (resource == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + tipo + "s_denunciados.json\"")
                .body(resource);
    }

    @ApiResponse(responseCode = "200", description = "Denúncias XML")
    @ApiResponse(responseCode = "404", description = "Não existem denúncias para o tipo especificado")
    @Operation(summary = "XML de denúncias", description = "Método que grava XML das denúncias de publicações ou comentários", tags = {"Denúncias"})
    @GetMapping(value = "/denuncias/xml", produces = "application/xml")
    public ResponseEntity<Resource> gravarXmlDenuncias(@RequestParam String tipo) throws IOException {
        Resource resource = denunciaService.gravaXmlDenuncias(tipo);

        if (resource == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_XML)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + tipo + "s_denunciados.xml\"")
                .body(resource);
    }


    @ApiResponse(responseCode = "200", description = "Denuncias removidas")
    @ApiResponse(responseCode = "404", description = "Publicação não encontrada")
    @Operation(summary = "Remover denúncias", description = "Método que remover todas as denuncias de uma publicação", tags = {"Publicações"})
    @DeleteMapping("/{idPublicacao}/remover-denuncias")
    public ResponseEntity<Void> removerDenuncias(@PathVariable int idPublicacao) {

        denunciaService.removerDenunciasPublicacao(idPublicacao);

        return ResponseEntity.ok().build();
    }

    //requisição que lista toda as publicacoes de um usuario que recebe o id do usuario
    @ApiResponse(responseCode = "200", description = "Publicações encontradas")
    @ApiResponse(responseCode = "204", description = "Publicações vazias")
    @Operation(summary = "Listar publicações de um usuário", description = "Método que lista todas as publicações de um usuário", tags = {"Publicações"})
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<PublicacaoListagemResponseDto>> listarPublicacoesPorUsuario(@PathVariable Long idUsuario) {

        List<Publicacao> publicacoes = publicacaoService.listarPorUsuario(idUsuario);
        List<PublicacaoListagemResponseDto> dtos = PublicacaoMapper.toDto(publicacoes);

        if (dtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dtos);
    }

    //requisição que altera o statatus da publicacao de ativo para arquivado
    @ApiResponse(responseCode = "200", description = "Publicação arquivada")
    @ApiResponse(responseCode = "404", description = "Publicação não encontrada")
    @Operation(summary = "Arquivar publicação", description = "Método que arquiva uma publicação", tags = {"Publicações"})
    @PatchMapping("/{id}/arquivar")
    public ResponseEntity<PublicacaoListagemResponseDto> arquivarPublicacao(@PathVariable int id) {

        Publicacao publicacao = publicacaoService.arquivarPublicacao(id);
        PublicacaoListagemResponseDto dto = PublicacaoMapper.toDto(publicacao);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/buscar-imagem/{id}")
    public ResponseEntity<String> buscarImagem(@PathVariable Integer id){
        String urlImagem = publicacaoService.buscarImagem(id);

        if (urlImagem == null){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(urlImagem);
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
