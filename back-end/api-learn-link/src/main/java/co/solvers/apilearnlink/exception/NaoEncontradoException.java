package co.solvers.apilearnlink.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NaoEncontradoException extends RuntimeException {

    public NaoEncontradoException(String entidade) {
        super(String.format("%s não encontrado!", entidade));
    }
}
