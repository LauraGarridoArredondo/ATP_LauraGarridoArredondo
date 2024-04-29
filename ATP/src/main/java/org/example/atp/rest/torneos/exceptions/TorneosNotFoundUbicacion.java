package org.example.atp.rest.torneos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TorneosNotFoundUbicacion extends RuntimeException{

    public TorneosNotFoundUbicacion(String ubicacion) {
        super("No se pudo encontrar el torneo en la ubicacion: " + ubicacion);
    }
}
