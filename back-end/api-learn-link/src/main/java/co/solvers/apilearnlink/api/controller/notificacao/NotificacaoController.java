package co.solvers.apilearnlink.api.controller.notificacao;

import co.solvers.apilearnlink.domain.notificacao.Notificacao;
import co.solvers.apilearnlink.service.notificacao.NotificacaoService;
import co.solvers.apilearnlink.service.notificacao.dto.NotificacaoCriacaoDto;
import co.solvers.apilearnlink.service.notificacao.dto.NotificacaoListagemDto;
import co.solvers.apilearnlink.service.notificacao.dto.mapper.NotificacaoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    private ResponseEntity<List<NotificacaoListagemDto>> listarNotificacoesUsuario(@PathVariable Long id){
        List<Notificacao> notificacoes = notificacaoService.listarNotificacoesUsuario(id);

        return ResponseEntity.ok(NotificacaoMapper.toListagemDto(notificacoes));
    }

    @PatchMapping("/visualizar-notificacao/{id}")
    private ResponseEntity<NotificacaoListagemDto> visualizarNotificacao(@PathVariable Long id){
        Notificacao notificacao = notificacaoService.visualizarNotificacao(id);

        return  ResponseEntity.ok(NotificacaoMapper.toListagemDto(notificacao));
    }
}
