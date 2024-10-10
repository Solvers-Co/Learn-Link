package co.solvers.apilearnlink.service.tipoStatus;

import co.solvers.apilearnlink.domain.tipostatus.TipoStatus;
import co.solvers.apilearnlink.domain.tipostatus.repository.TipoStatusRepository;
import co.solvers.apilearnlink.exception.NaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TipoStatusService {
    private final TipoStatusRepository tipoStatusRepository;

    public TipoStatus buscarPorId(int id) {
        Optional<TipoStatus> tipoStatus = tipoStatusRepository.findById(id);

        if (tipoStatus.isEmpty()) throw new NaoEncontradoException("Tipo status");

        return tipoStatus.get();
    }

    public TipoStatus buscarPorStatus(String status) {
        Optional<TipoStatus> tipoStatus = tipoStatusRepository.findByStatusIgnoreCase(status);

        if (tipoStatus.isEmpty()) throw new NaoEncontradoException("Tipo status");

        return tipoStatus.get();
    }
}
