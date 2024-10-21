package co.solvers.apilearnlink.domain.respostaImagem;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RespostaImagem(
        int status,
        Map<String,Object> parametros,
        boolean valido,
        String resultado
) {
}
