package co.solvers.apilearnlink.service.endereco;

import co.solvers.apilearnlink.domain.endereco.Endereco;
import co.solvers.apilearnlink.domain.endereco.repository.EnderecoRepository;
import co.solvers.apilearnlink.exception.NaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public Endereco buscarPorId(Integer id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);

        if (endereco.isEmpty()) throw new NaoEncontradoException("Endereço");

        return endereco.get();
    }

public Endereco salvar(Endereco endereco) {
    if (endereco == null) {
        throw new IllegalArgumentException("Endereço não pode ser nulo");
    }
    return enderecoRepository.save(endereco);
}
}
