package org.example.atp.rest.tenistas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TenistasNotFoundByPeso extends RuntimeException {
    public TenistasNotFoundByPeso(int peso) {
        super("No se pudo encontrar el tenista con el peso: " + peso);
    }
}
