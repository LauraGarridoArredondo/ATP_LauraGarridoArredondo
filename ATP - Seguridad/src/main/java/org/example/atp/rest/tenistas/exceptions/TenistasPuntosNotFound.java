package org.example.atp.rest.tenistas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TenistasPuntosNotFound extends RuntimeException {
    public TenistasPuntosNotFound(int puntos) {
        super("No se pudo encontrar el tenista con los puntos: " + puntos);
    }
}
