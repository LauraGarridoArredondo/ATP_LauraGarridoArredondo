package org.example.atp.rest.tenistas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TenistasNotFoundByDineroGanado extends RuntimeException {
    public TenistasNotFoundByDineroGanado(long dineroGanado) {
        super("No se pudo encontrar el tenista con el dinero ganado: " + dineroGanado);
    }
}
