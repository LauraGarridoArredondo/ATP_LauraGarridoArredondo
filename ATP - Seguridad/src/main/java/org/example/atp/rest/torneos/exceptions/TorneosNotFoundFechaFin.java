package org.example.atp.rest.torneos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TorneosNotFoundFechaFin extends RuntimeException{

    public TorneosNotFoundFechaFin(LocalDate fechaFin) {
        super("No se pudo encontrar el torneo con la fecha de finalizacion: " + fechaFin);
    }
}
