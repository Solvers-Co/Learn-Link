package co.solvers.apilearnlink.service.publicacoesnaorespondidas;

import co.solvers.apilearnlink.domain.views.publicacoesNaoRespondidas.repository.QtdPublicacoesNaoRespondidasViewRepository;
import co.solvers.apilearnlink.service.publicacoesnaorespondidas.dto.QtdPublicacoesNaoRespondidasDto;
import co.solvers.apilearnlink.service.publicacoesnaorespondidas.dto.mapper.QtdPublicacoesNaoRespondidasMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QtdPublicacoesNaoRespondidasService {

    @Autowired
    private QtdPublicacoesNaoRespondidasViewRepository repository;

    @Autowired
    private QtdPublicacoesNaoRespondidasMapper mapper;

    public List<QtdPublicacoesNaoRespondidasDto> getMateriasNaoRespondidas() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
