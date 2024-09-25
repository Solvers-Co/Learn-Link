package co.solvers.apilearnlink.api.controller.notificacao;

import co.solvers.apilearnlink.domain.notificacao.Notificacao;
import co.solvers.apilearnlink.service.notificacao.NotificacaoService;
import co.solvers.apilearnlink.service.notificacao.dto.NotificacaoCriacaoDto;
import co.solvers.apilearnlink.service.notificacao.dto.NotificacaoListagemDto;
import co.solvers.apilearnlink.service.notificacao.dto.mapper.NotificacaoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notificacoes")
@RequiredArgsConstructor
public class NotificacaoController {

    private final NotificacaoService notificacaoService;

    @PostMapping
    private ResponseEntity<NotificacaoListagemDto> criarNotificacao(
            @RequestBody NotificacaoCriacaoDto notificacaoCriacaoDto){

        Notificacao notificacao = notificacaoService.criarNotificacao(NotificacaoMapper.toEntity(notificacaoCriacaoDto), notificacaoCriacaoDto.getUsuarioGeradorId(), notificacaoCriacaoDto.getUsuarioRecebedorId());

        return ResponseEntity.status(201).body(NotificacaoMapper.toListagemDto(notificacao));
    }
}
