package co.solvers.apilearnlink.service.classificacao;

import co.solvers.apilearnlink.domain.classificacao.Classificacao;
import co.solvers.apilearnlink.domain.classificacao.repository.ClassificacaoRepository;
import co.solvers.apilearnlink.exception.NaoEncontradoException;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassificacaoService {

    private final ClassificacaoRepository classificacaoRepository;

    public Classificacao buscarPorId(int id){
        Optional<Classificacao> classificacaoUsuario = classificacaoRepository.findById(id);

        if (classificacaoUsuario.isEmpty()) throw new NaoEncontradoException("Classificação");

        return classificacaoUsuario.get();
    }

    public Classificacao buscarPorClassificacao(String classificacao){
        Optional<Classificacao> classificacaoUsuario = classificacaoRepository.findByClassificacao(classificacao);

        if (classificacaoUsuario.isEmpty()) throw new NaoEncontradoException("Classificação");

        return classificacaoUsuario.get();
    }
}
