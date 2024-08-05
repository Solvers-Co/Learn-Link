package co.solvers.apilearnlink.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidoException extends RuntimeException{

    public InvalidoException(String entidade) {super(String.format("%s inválido!", entidade));}
}
