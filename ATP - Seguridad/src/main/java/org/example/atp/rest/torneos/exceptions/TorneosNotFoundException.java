package org.example.atp.rest.torneos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TorneosNotFoundException extends RuntimeException{

    public TorneosNotFoundException(Long id) {
        super("No se pudo encontrar el torneo con la id: " + id);
    }
}
