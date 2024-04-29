package org.example.atp.rest.torneos.exceptions;

import org.example.atp.rest.torneos.models.Superficie;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TorneosNotFoundSuperficie extends RuntimeException{

    public TorneosNotFoundSuperficie(Superficie superficie) {
        super("No se pudo encontrar el torneo con la superficie: " + superficie);
    }
}
