package co.solvers.apilearnlink.service.publicacoesnaorespondidas.dto.mapper;

import co.solvers.apilearnlink.domain.views.publicacoesNaoRespondidas.QtdPublicacoesNaoRespondidasView;
import co.solvers.apilearnlink.service.publicacoesnaorespondidas.dto.QtdPublicacoesNaoRespondidasDto;
import org.springframework.stereotype.Component;

@Component
public class QtdPublicacoesNaoRespondidasMapper {

    public QtdPublicacoesNaoRespondidasDto toDto(QtdPublicacoesNaoRespondidasView entity) {
        QtdPublicacoesNaoRespondidasDto dto = new QtdPublicacoesNaoRespondidasDto();
        dto.setNomeMateria(entity.getNomeMateria());
        dto.setQtdPublicacoesNaoRespondidas(entity.getQtdPublicacoesNaoRespondidas());
        dto.setQtdPublicacoesRespondidas(entity.getQtdPublicacoesRespondidas());
        return dto;
    }
}
