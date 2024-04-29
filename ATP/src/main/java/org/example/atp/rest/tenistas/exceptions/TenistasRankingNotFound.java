package org.example.atp.rest.tenistas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TenistasRankingNotFound extends RuntimeException {

    public TenistasRankingNotFound(int ranking) {
        super("No se pudo encontrar el tenista con el ranking: " + ranking);
    }
}
