package co.solvers.apilearnlink.service.canal;

import co.solvers.apilearnlink.domain.canal.Canal;
import co.solvers.apilearnlink.domain.canal.repository.CanalRepository;
import co.solvers.apilearnlink.exception.NaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CanalService {
    private final CanalRepository canalRepository;

    public Canal buscarPorId (int id){
        Optional<Canal> canal = canalRepository.findById(id);

        if (canal.isEmpty()) throw new NaoEncontradoException("Canal");

        return canal.get();
    }
}
