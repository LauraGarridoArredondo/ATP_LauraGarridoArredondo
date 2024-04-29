package org.example.atp.rest.tenistas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TenistasNotFoundByEdad extends RuntimeException {
    public TenistasNotFoundByEdad(int edad) {
        super("No se pudo encontrar el tenista con la edad: " + edad);
    }
}
