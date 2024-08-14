package co.solvers.apilearnlink.service.publicacoesNaoRespondidas;

import co.solvers.apilearnlink.domain.publicacoesNaoRespondidas.QtdPublicacoesNaoRespondidasView;
import co.solvers.apilearnlink.domain.publicacoesNaoRespondidas.repository.QtdPublicacoesNaoRespondidasViewRepository;
import co.solvers.apilearnlink.service.publicacoesNaoRespondidas.dto.QtdPublicacoesNaoRespondidasDto;
import co.solvers.apilearnlink.service.publicacoesNaoRespondidas.dto.mapper.QtdPublicacoesNaoRespondidasMapper;
import lombok.RequiredArgsConstructor;
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
