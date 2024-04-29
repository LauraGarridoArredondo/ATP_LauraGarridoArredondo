package org.example.atp.rest.tenistas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TenistasBadRequest extends RuntimeException{
    public TenistasBadRequest(String message) {
        super("Ha realizado un error al realizar la operación: " + message);
    }
}
