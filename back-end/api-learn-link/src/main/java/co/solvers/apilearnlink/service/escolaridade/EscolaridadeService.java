package co.solvers.apilearnlink.service.escolaridade;

import co.solvers.apilearnlink.domain.classificacao.Classificacao;
import co.solvers.apilearnlink.domain.escolaridade.Escolaridade;
import co.solvers.apilearnlink.domain.escolaridade.repository.EscolaridadeRepository;
import co.solvers.apilearnlink.exception.NaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EscolaridadeService {
    private final EscolaridadeRepository escolaridadeRepository;

    public Escolaridade buscarPorId(int id){
        Optional<Escolaridade> escolaridadeUsuario = escolaridadeRepository.findById(id);

        if (escolaridadeUsuario.isEmpty()) throw new NaoEncontradoException("Escolaridade");

        return escolaridadeUsuario.get();
    }

    public Escolaridade buscarPorEscolaridade(String escolaridade){
        Optional<Escolaridade> escolaridadeUsuario = escolaridadeRepository.findByEscolaridade(escolaridade);

        if (escolaridadeUsuario.isEmpty()) throw new NaoEncontradoException("Classificação");

        return escolaridadeUsuario.get();
    }
}
