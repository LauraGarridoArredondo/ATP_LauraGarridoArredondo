package org.example.atp.rest.tenistas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TenistasNotFoundBymejorRanking extends RuntimeException {
    public TenistasNotFoundBymejorRanking(long mejorRanking) {
        super("No se pudo encontrar el tenista con el mejor ranking actual: " + mejorRanking);
    }
}
