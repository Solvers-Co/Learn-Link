package co.solvers.apilearnlink.service.reacoesEmComentariosDoUsuario.dto.mapper;

import co.solvers.apilearnlink.domain.views.ReacoesEmComentariosDoUsuario.QtdReacoesComentariosUsuarioView;
import co.solvers.apilearnlink.service.reacoesEmComentariosDoUsuario.dto.QtdContribuicoesRankingDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QtdContribuicoesRankingMapper {

    public static QtdContribuicoesRankingDto toDto(QtdReacoesComentariosUsuarioView qtdContribuicoes){
        QtdContribuicoesRankingDto dto = new QtdContribuicoesRankingDto();

        dto.setQtdReacoes(qtdContribuicoes.getReacoes());
        dto.setUsuarioId(qtdContribuicoes.getUsuarioId());
        dto.setNome(qtdContribuicoes.getNome());

        return dto;
    }

    public static List<QtdContribuicoesRankingDto> toDto(List<QtdReacoesComentariosUsuarioView> listaDeContribuicoes){
        List<QtdContribuicoesRankingDto> dtos = new ArrayList<>();

        for (QtdReacoesComentariosUsuarioView entidade : listaDeContribuicoes){
            dtos.add(toDto(entidade));
        }
        return dtos;
    }

}
