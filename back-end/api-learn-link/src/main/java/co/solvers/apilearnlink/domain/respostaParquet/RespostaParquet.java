package co.solvers.apilearnlink.domain.respostaParquet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.core.io.Resource;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RespostaParquet(
        int status,
        String parquet
) {

}
