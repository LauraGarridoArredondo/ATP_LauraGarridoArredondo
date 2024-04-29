package org.example.atp.rest.tenistas.exceptions;

import org.example.atp.rest.tenistas.models.Mano;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TenistasNotFoundMano extends RuntimeException{
    public TenistasNotFoundMano(Mano mano) {
        super("No se pudo registrar el tenista con la mano: " + mano);
    }
}
