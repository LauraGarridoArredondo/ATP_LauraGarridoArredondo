package org.example.atp.rest.torneos.exceptions;

import org.example.atp.rest.torneos.models.Tipo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TorneosNotFoundTipo extends RuntimeException{

    public TorneosNotFoundTipo(Tipo tipo) {
        super("No se pudo encontrar el torneo con el tipo: " + tipo);
    }
}
