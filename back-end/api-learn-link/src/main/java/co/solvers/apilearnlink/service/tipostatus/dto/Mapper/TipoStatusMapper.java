package co.solvers.apilearnlink.service.tipostatus.dto.Mapper;


import co.solvers.apilearnlink.domain.tipostatus.TipoStatus;
import co.solvers.apilearnlink.service.tipostatus.dto.TipoStatusCriacaoRequestDto;
import org.springframework.stereotype.Service;

@Service
public class TipoStatusMapper {

    public static TipoStatus toEntity(TipoStatusCriacaoRequestDto dto) {
        TipoStatus tipoStatus = new TipoStatus();

        tipoStatus.setId(dto.getId());
        tipoStatus.setStatus(dto.getStatus());

        return tipoStatus;
    }

}
