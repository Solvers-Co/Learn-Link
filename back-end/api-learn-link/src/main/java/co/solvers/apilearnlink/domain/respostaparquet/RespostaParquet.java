package co.solvers.apilearnlink.domain.respostaparquet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RespostaParquet(
        int status,
        String parquet
) {

}
