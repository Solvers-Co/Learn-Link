package co.solvers.apilearnlink.service.publicacoesNaoRespondidas.dto.mapper;

import co.solvers.apilearnlink.domain.publicacoesNaoRespondidas.QtdPublicacoesNaoRespondidasView;
import co.solvers.apilearnlink.service.publicacoesNaoRespondidas.dto.QtdPublicacoesNaoRespondidasDto;
import org.springframework.stereotype.Component;

@Component
public class QtdPublicacoesNaoRespondidasMapper {

    public QtdPublicacoesNaoRespondidasDto toDto(QtdPublicacoesNaoRespondidasView entity) {
        QtdPublicacoesNaoRespondidasDto dto = new QtdPublicacoesNaoRespondidasDto();
        dto.setNomeMateria(entity.getNomeMateria());
        dto.setQtdPublicacoesNaoRespondidas(entity.getQtdPublicacoesNaoRespondidas());
        return dto;
    }
}
