package org.example.atp.rest.torneos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.atp.rest.torneos.models.Categoria;
import org.example.atp.rest.torneos.models.Superficie;
import org.example.atp.rest.torneos.models.Tipo;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Torneos a devolver")
public class TorneosDTO {
    @Schema(description = "Identificador del torneo", example = "1")
    private long id;
    @Schema(description = "Ubicacion del torneo", example = "Cordoba")
    private String Ubicacion;
    @Schema(description = "Tipo del torneo", example = "INDIVIDUAL")
    private Tipo Tipo;
    @Schema(description = "Categoria del torneo", example = "MASTER1000")
    private Categoria Categoria;
    @Schema(description = "Modalidad del torneo", example = "Grupo A")
    private String Modalidad;
    @Schema(description = "Superficie del torneo", example = "DURA")
    private Superficie Superficie;
    @Schema(description = "Vacantes del torneo", example = "1")
    private Long Vacantes;
    @Schema(description = "Premios del torneo", example = "1")
    private String Premios;
    @Schema(description = "Fecha de inicio del torneo", example = "2000-01-01")
    private LocalDate fechaInicio;
    @Schema(description = "Fecha de fin del torneo", example = "2000-01-01")
    private LocalDate fechaFin;
    @Schema(description = "Imagen del torneo", example = "imagen.jpg")
    private String Imagen;
}
