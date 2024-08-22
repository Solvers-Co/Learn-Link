package co.solvers.apilearnlink.service.especialidade;

import co.solvers.apilearnlink.domain.especialidade.Especialidade;
import co.solvers.apilearnlink.domain.especialidade.repository.EspecialidadeRepository;
import co.solvers.apilearnlink.exception.NaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EspecialidadeService {

    private final EspecialidadeRepository especialidadeRepository;

    public Especialidade buscarPorId(Long id) {
        Optional<Especialidade> especialidade = especialidadeRepository.findById(id);

        if (especialidade.isEmpty()) {
            throw new NaoEncontradoException("Especialidade");
        } else {
            return especialidade.get();
        }
    }


    public Especialidade buscarPorNome(String nome) {
        Optional<Especialidade> especialidade = especialidadeRepository.findByMateria(nome);

        if (especialidade.isPresent()) {
            throw new NaoEncontradoException("Especialidade");
        } else {
            return especialidade.get();
        }
    }

    public List<Especialidade> listar(){
        return especialidadeRepository.findAll();
    }



}
