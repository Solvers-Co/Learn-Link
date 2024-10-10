package co.solvers.apilearnlink.api.controller.livrosFuvest;

import co.solvers.apilearnlink.service.livrosFuvest.LivrosFuvestService;
import co.solvers.apilearnlink.service.livrosFuvest.dto.LivrosFuvestCriacaoDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivrosFuvestController {

    private final LivrosFuvestService livrosFuvestService;

    @GetMapping("/listar")
    public ResponseEntity<List<LivrosFuvestCriacaoDto>> listarLivros() {
        List<LivrosFuvestCriacaoDto> listaLivros = livrosFuvestService.lerArquivoTxt();

        if (listaLivros.isEmpty()) {
            return ResponseEntity.noContent().build(); // Se a lista estiver vazia, retorna 204 No Content
        } else {
            return ResponseEntity.ok(listaLivros); // Retorna 200 OK com a lista de livros
        }
    }
}
