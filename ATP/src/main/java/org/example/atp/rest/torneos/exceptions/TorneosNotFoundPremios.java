package org.example.atp.rest.torneos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TorneosNotFoundPremios extends RuntimeException{

    public TorneosNotFoundPremios(String premios) {
        super("No se pudo encontrar el torneo con los premios: " + premios);
    }
}
