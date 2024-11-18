package co.solvers.apilearnlink.api.controller.qtdpublicacoesnaorespondidas;

import co.solvers.apilearnlink.service.publicacoesnaorespondidas.QtdPublicacoesNaoRespondidasService;
import co.solvers.apilearnlink.service.publicacoesnaorespondidas.dto.QtdPublicacoesNaoRespondidasDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/qtd-materias-nao-respondidas")
public class QtdPublicacoesNaoRespondidasController {

    @Autowired
    private QtdPublicacoesNaoRespondidasService service;

    @ApiResponse(responseCode = "200", description = "Quantidade de publicações não respondidas encontrada")
    @ApiResponse(responseCode = "404", description = "Nenhuma quantidade de publicações não respondidas encontrada")
    @Operation(summary = "Buscar quantidade de publicações não respondidas", description = "Método que busca a quantidade de publicações não respondidas", tags = {"Publicações"})
    @GetMapping
    public List<QtdPublicacoesNaoRespondidasDto> getMateriasNaoRespondidas() {
        return service.getMateriasNaoRespondidas();
    }
}

