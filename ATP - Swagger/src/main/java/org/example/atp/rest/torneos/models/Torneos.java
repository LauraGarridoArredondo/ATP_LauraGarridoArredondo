package org.example.atp.rest.torneos.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Torneos {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Schema(description = "Ubicacion del torneo", example = "Cordoba")
    @NotBlank(message = "La ubicacion es obligatoria")
    private String ubicacion;
    @Schema(description = "Tipo del torneo", example = "INDIVIDUAL")
    @NotBlank(message = "El tipo es obligatorio")
    private Tipo tipo;
    @Schema(description = "Categoria del torneo", example = "MASTER1000")
    @NotBlank(message = "La categoria es obligatoria")
    private Categoria categoria;
    @Schema(description = "Modalidad del torneo", example = "Grupo A")
    @NotBlank(message = "La modalidad es obligatoria")
    private String modalidad;
    @Schema(description = "Superficie del torneo", example = "DURA")
    @NotBlank(message = "La superficie es obligatoria")
    private Superficie superficie;
    @Schema(description = "Vacantes del torneo", example = "1")
    @NotBlank(message = "Las vacantes son obligatorias")
    private Long vacantes;
    @Schema(description = "Premios del torneo", example = "1")
    @NotBlank(message = "Los premios son obligatorios")
    private String premios;
    @Schema(description = "Fecha de inicio del torneo", example = "2000-01-01")
    @NotBlank(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;
    @Schema(description = "Fecha de fin del torneo", example = "2000-01-01")
    @NotBlank(message = "La fecha de fin es obligatoria")
    private LocalDate fechaFin;
    @Schema(description = "Imagen del Torneo donde se celebro", example = "https://via.placeholder.com/150")
    private String imagen;
}
