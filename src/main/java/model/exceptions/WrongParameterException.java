package model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by pcorentin on 09/07/2015.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad arguments")
public class WrongParameterException extends Exception {
    public WrongParameterException(String message) {
        super(message);
    }
}
