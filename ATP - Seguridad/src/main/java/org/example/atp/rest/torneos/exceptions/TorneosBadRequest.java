package org.example.atp.rest.torneos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TorneosBadRequest extends RuntimeException{
    public TorneosBadRequest(String message) {
        super("Ha realizado un error al realizar la operación de crear un torneo: " + message);
    }
}
