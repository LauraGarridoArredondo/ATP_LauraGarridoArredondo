package org.example.atp.rest.torneos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.atp.rest.torneos.models.Categoria;
import org.example.atp.rest.torneos.models.Superficie;
import org.example.atp.rest.torneos.models.Tipo;

import java.time.LocalDate;
@Getter
@Setter
@Builder
@Schema(description = "Torneos a crear")
public class CreateTorneosDTO {
    private static final String IMAGE_DEFAULT = "https://via.placeholder.com/150";

    @Schema(description = "Ubicacion del torneo", example = "Cordoba")
    @NotBlank(message = "La ubicacion es obligatoria")
    private String Ubicacion;
    @Schema(description = "Tipo del torneo", example = "INDIVIDUAL")
    @NotBlank(message = "El tipo es obligatorio")
    private Tipo Tipo;
    @Schema(description = "Categoria del torneo", example = "MASTER1000")
    @NotBlank(message = "La categoria es obligatoria")
    private Categoria Categoria;
    @Schema(description = "Modalidad del torneo", example = "Grupo A")
    @NotBlank(message = "La modalidad es obligatoria")
    private String Modalidad;
    @Schema(description = "Superficie del torneo", example = "DURA")
    @NotBlank(message = "La superficie es obligatoria")
    private Superficie Superficie;
    @Schema(description = "Vacantes del torneo", example = "1")
    @NotBlank(message = "Las vacantes son obligatorias")
    private Long Vacantes;
    @Schema(description = "Premios del torneo", example = "1")
    @NotBlank(message = "Los premios son obligatorios")
    private String Premios;
    @Schema(description = "Fecha de inicio del torneo", example = "2000-01-01")
    @NotBlank(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;
    @Schema(description = "Fecha de fin del torneo", example = "2000-01-01")
    @NotBlank(message = "La fecha de fin es obligatoria")
    private LocalDate fechaFin;
    @Schema(description = "Imagen del Torneo donde se celebro", example = "https://via.placeholder.com/150")
    @Builder.Default
    private String Imagen = IMAGE_DEFAULT;
}
