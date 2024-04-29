package org.example.atp.rest.tenistas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TenistasNotFoundByDebutProfesional extends RuntimeException {
    public TenistasNotFoundByDebutProfesional(LocalDate txt) {
        super("No se pudo encontrar el tenista con el debut Profesional: " + txt);
    }
}
