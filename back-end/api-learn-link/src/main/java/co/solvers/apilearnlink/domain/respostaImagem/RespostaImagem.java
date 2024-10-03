package co.solvers.apilearnlink.domain.respostaImagem;

import java.util.Map;

public record RespostaImagem(
        int status,
        Map<String,Object> parametros,
        boolean valido,
        String resultado
) {
}
