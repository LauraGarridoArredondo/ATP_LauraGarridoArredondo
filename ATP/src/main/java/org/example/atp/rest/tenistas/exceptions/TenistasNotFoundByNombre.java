package org.example.atp.rest.tenistas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TenistasNotFoundByNombre extends RuntimeException {
    public TenistasNotFoundByNombre(String txt) {
        super("No se pudo encontrar el tenista con el nombre: " + txt);
    }
}
