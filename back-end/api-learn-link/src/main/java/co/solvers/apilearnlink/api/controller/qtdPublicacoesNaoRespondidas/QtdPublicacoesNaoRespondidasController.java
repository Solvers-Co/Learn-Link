package co.solvers.apilearnlink.api.controller.qtdPublicacoesNaoRespondidas;

import co.solvers.apilearnlink.domain.publicacoesNaoRespondidas.QtdPublicacoesNaoRespondidasView;
import co.solvers.apilearnlink.service.publicacoesNaoRespondidas.QtdPublicacoesNaoRespondidasService;
import co.solvers.apilearnlink.service.publicacoesNaoRespondidas.dto.QtdPublicacoesNaoRespondidasDto;
import co.solvers.apilearnlink.service.publicacoesNaoRespondidas.dto.mapper.QtdPublicacoesNaoRespondidasMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/qtd-materias-nao-respondidas")
public class QtdPublicacoesNaoRespondidasController {

    @Autowired
    private QtdPublicacoesNaoRespondidasService service;

    @GetMapping
    public List<QtdPublicacoesNaoRespondidasDto> getMateriasNaoRespondidas() {
        return service.getMateriasNaoRespondidas();
    }
}


//public class QtdPublicacoesNaoRespondidasController {
//
//    private final QtdPublicacoesNaoRespondidasService publicacaoService;
//
//    @GetMapping("/publicacoes-nao-respondidas")
//    public ResponseEntity<List<QtdPublicacoesNaoRespondidasDto>> listarPublicacoesNaoRespondidas() {
//        List<QtdPublicacoesNaoRespondidasView> qtdPublicacoes = publicacaoService.buscaQtdPublicacoesNaoRespondidas();
//        List<QtdPublicacoesNaoRespondidasDto> dtos = QtdPublicacoesNaoRespondidasMapper.toDto(qtdPublicacoes);
//
//        if (dtos.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//
//        return ResponseEntity.status(200).body(dtos);
//    }
//}
