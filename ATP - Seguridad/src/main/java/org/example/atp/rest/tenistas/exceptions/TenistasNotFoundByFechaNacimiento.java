package org.example.atp.rest.tenistas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TenistasNotFoundByFechaNacimiento extends RuntimeException {
    public TenistasNotFoundByFechaNacimiento(LocalDate txt) {
        super("No se pudo encontrar el tenista con la fecha de nacimiento: " + txt);
    }
}
