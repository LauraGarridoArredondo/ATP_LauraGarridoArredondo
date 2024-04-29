package org.example.atp.rest.tenistas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TenistasNotFoundBynumVictorias extends RuntimeException {
    public TenistasNotFoundBynumVictorias(int numVictorias) {
        super("No se pudo encontrar el tenista con el numVictorias: " + numVictorias);
    }
}