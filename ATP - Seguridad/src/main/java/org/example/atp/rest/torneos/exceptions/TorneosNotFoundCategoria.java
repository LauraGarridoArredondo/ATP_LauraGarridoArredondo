package org.example.atp.rest.torneos.exceptions;

import org.example.atp.rest.torneos.models.Categoria;
import org.example.atp.rest.torneos.models.Tipo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TorneosNotFoundCategoria extends RuntimeException{

    public TorneosNotFoundCategoria(Categoria categoria) {
        super("No se pudo encontrar el torneo con la Categoria: " + categoria);
    }
}
