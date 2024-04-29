package org.example.atp.rest.tenistas.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tenistas {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    @Schema(description = "Ranking del tenista", example = "1")
    @Min(value = 0, message = "El ranking no puede ser negativo")
    @Max(value = 900, message = "El ranking no puede ser mayor a 900")
    private long ranking;
    @NotBlank(message = "El nombre no puede estar vacío")
    @Length(min = 3, message = "El nombre debe tener al menos 3 caracteres")
    @Schema(description = "Nombre del tenista", example = "Rafael Nadal")
    private String nombreCompleto;
    @Schema(description = "Pais del tenista", example = "España")
    @NotBlank(message = "El pais no puede estar vacío")
    private String pais;
    @NotBlank(message = "La fecha de nacimiento no puede estar vacío")
    @Schema(description = "Fecha de nacimiento del tenista", example = "2000-01-01")
    private LocalDate fechaNacimiento;
    @Min(value = 18, message = "La edad no puede ser menor a 18")
    @Max(value = 99, message = "La edad no puede ser mayor a 41")
    @Schema(description = "Edad del tenista", example = "1")
    @NotBlank(message = "La edad no puede estar vacío")
    private int edad;
    @Schema(description = "Altura del tenista", example = "1")
    @NotBlank(message = "La altura no puede estar vacío")
    private int altura;
    @Schema(description = "Peso del tenista", example = "1")
    @NotBlank(message = "El peso no puede estar vacío")
    private int peso;
    @Schema(description = "Fecha de debut del tenista", example = "2000-01-01")
    @NotBlank(message = "La fecha de debut no puede estar vacío")
    private LocalDate debutProfesional;
    @Schema(description = "Mano del tenista", example = "1")
    @NotBlank(message = "La mano que utiliza no puede estar vacío")
    private Mano mano;
    @Schema(description = "Reves del tenista", example = "1")
    @NotBlank(message = "El reves que utiliza no puede estar vacío")
    private Reves reves;
    @Schema(description = "Entrenador del tenista", example = "Cristiano")
    @NotBlank(message = "El entrenador no puede estar vacío")
    private String entrenador;
    @Schema(description = "Dinero ganado por el tenista", example = "1")
    @NotBlank(message = "El dinero ganado no puede estar vacío")
    @Min(value = 0, message = "El dinero ganado no puede ser negativo")
    private Long dineroGanado;
    @Schema(description = "Mejor ranking del tenista", example = "1")
    @NotBlank(message = "El mejor ranking no puede estar vacío")
    @Min(value = 0, message = "El mejor ranking no puede ser negativo")
    private Long mejorRanking;
    @Schema(description = "Numero de victorias del tenista", example = "1")
    @NotBlank(message = "El numero de victorias no puede estar vacío")
    @Min(value = 0, message = "El numero de victorias no puede ser negativo")
    private int numVictorias;
    @NotBlank(message = "El numero de derrotas no puede estar vacío")
    @Min(value = 0, message = "El numero de derrotas no puede ser negativo")
    @Schema(description = "Numero de derrotas del tenista", example = "1")
    private int numDerrotas;
    @Schema(description = "Imagen del tenista", example = "https://via.placeholder.com/150")
    private String imagen;
    @Schema(description = "Puntos del tenista", example = "1")
    @NotBlank(message = "El numero de puntos no puede estar vacío")
    @Min(value = 0, message = "El numero de puntos no puede ser negativo")
    private int puntos;
}
