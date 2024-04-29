package org.example.atp.rest.tenistas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.atp.rest.tenistas.models.Mano;
import org.example.atp.rest.tenistas.models.Reves;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Tenistas a devolver")
public class TenistasDTO {
    @Schema(description = "Identificador del tenista", example = "1")
    private long id;
    @Schema(description = "Ranking del tenista", example = "1")
    private long Ranking;
    @Schema(description = "Nombre del tenista", example = "Rafael Nadal")
    private String NombreCompleto;
    @Schema(description = "Pais del tenista", example = "España")
    private String Pais;
    @Schema(description = "Fecha de nacimiento del tenista", example = "2000-01-01")
    private LocalDate fechaNacimiento;
    @Schema(description = "Edad del tenista", example = "1")
    private int Edad;
    @Schema(description = "Altura del tenista", example = "1")
    private int Altura;
    @Schema(description = "Peso del tenista", example = "1")
    private int Peso;
    @Schema(description = "Fecha de debut del tenista", example = "2000-01-01")
    private LocalDate DebutProfesional;
    @Schema(description = "Mano del tenista", example = "1")
    private Mano Mano;
    @Schema(description = "Reves del tenista", example = "1")
    private Reves Reves;
    @Schema(description = "Entrenador del tenista", example = "Cristiano")
    private String Entrenador;
    @Schema(description = "Dinero ganado por el tenista", example = "1")
    private Long DineroGanado;
    @Schema(description = "Mejor ranking del tenista", example = "1")
    private Long MejorRanking;
    @Schema(description = "Numero de victorias del tenista", example = "1")
    private int numVictorias;
    @Schema(description = "Numero de derrotas del tenista", example = "1")
    private int numDerrotas;
    @Schema(description = "Imagen del tenista", example = "imagen.jpg")
    private String imagen;
    @Schema(description = "Puntos del tenista", example = "1")
    private int puntos;
}
