package org.example.atp.rest.torneos.exceptions;

import org.example.atp.rest.torneos.models.Categoria;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TorneosNotFoundModalidad extends RuntimeException{

    public TorneosNotFoundModalidad(String modalidad) {
        super("No se pudo encontrar el torneo con la modalidad: " + modalidad);
    }
}
