package co.solvers.apilearnlink.service.tiporeacao;

import co.solvers.apilearnlink.domain.tiporeacao.TipoReacao;
import co.solvers.apilearnlink.domain.tiporeacao.repository.TipoReacaoRepository;
import co.solvers.apilearnlink.exception.NaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TipoReacaoService {
    private final TipoReacaoRepository tipoReacaoRepository;

    public TipoReacao buscarPorId(int id) {
        Optional<TipoReacao> optTipoReacao = tipoReacaoRepository.findById(id);

        if (optTipoReacao.isEmpty()) throw new NaoEncontradoException("Tipo de reação");

        return optTipoReacao.get();
    }

    public TipoReacao buscarPorNome(String nome) {
        Optional<TipoReacao> optTipoReacao = tipoReacaoRepository.findByNomeIgnoreCase(nome);

        if (optTipoReacao.isEmpty()) throw new NaoEncontradoException("Tipo de reação");

        return optTipoReacao.get();
    }
}
