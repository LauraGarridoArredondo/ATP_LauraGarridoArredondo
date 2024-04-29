package org.example.atp.rest.tenistas.exceptions;

import org.example.atp.rest.tenistas.models.Reves;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TenistasNotFoundByReves extends RuntimeException {
    public TenistasNotFoundByReves(Reves Reves) {
        super("No se pudo encontrar el tenista con el peso: " + Reves);
    }
}
