package org.example.atp.rest.tenistas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TenistasNotFoundByEntrenador extends RuntimeException {
    public TenistasNotFoundByEntrenador(String entrenador) {
        super("No se pudo encontrar el entrenador del tenista con el nombre: " + entrenador);
    }
}
