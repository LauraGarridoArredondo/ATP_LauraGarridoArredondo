package org.example.atp.rest.torneos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TorneosNotFoundVacantes extends RuntimeException{

    public TorneosNotFoundVacantes(Long vacantes) {
        super("No se pudo encontrar el torneo con las vacantes: " + vacantes);
    }
}
