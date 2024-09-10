package co.solvers.apilearnlink.service.reacoesEmComentariosDoUsuario.dto.mapper;

import co.solvers.apilearnlink.domain.views.ReacoesEmComentariosDoUsuario.QtdReacoesComentariosUsuarioView;
import co.solvers.apilearnlink.service.reacoesEmComentariosDoUsuario.dto.QtdReacoesComentariosUsuarioDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class QtdReacoesComentariosUsuarioMapper {

    public static Optional<QtdReacoesComentariosUsuarioDto> toDto(Optional<QtdReacoesComentariosUsuarioView> qtdReacoesComentariosUsuarioView){
        QtdReacoesComentariosUsuarioDto dto = new QtdReacoesComentariosUsuarioDto();
        dto.setQtdReacoes(qtdReacoesComentariosUsuarioView.get().getReacoes());
        dto.setPontuacao(qtdReacoesComentariosUsuarioView.get().getPontuacao());

        return Optional.of(dto);
    }

}
