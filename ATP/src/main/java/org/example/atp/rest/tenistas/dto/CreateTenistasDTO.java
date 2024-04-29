package org.example.atp.rest.tenistas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.atp.rest.tenistas.models.Mano;
import org.example.atp.rest.tenistas.models.Reves;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
@Builder
@Getter @Setter
@Schema(description = "Tenistas a crear")
public class CreateTenistasDTO {
    private static final String IMAGE_DEFAULT = "https://via.placeholder.com/150";
    @Schema(description = "Ranking del tenista", example = "1")
    @Min(value = 0, message = "El ranking no puede ser negativo")
    @Max(value = 900, message = "El ranking no puede ser mayor a 900")
    private long Ranking;
    @NotBlank(message = "El nombre no puede estar vacío")
    @Length(min = 3, message = "El nombre debe tener al menos 3 caracteres")
    @Schema(description = "Nombre del tenista", example = "Rafael Nadal")
    private String NombreCompleto;
    @Schema(description = "Pais del tenista", example = "España")
    @NotBlank(message = "El pais no puede estar vacío")
    private String Pais;
    @NotBlank(message = "La fecha de nacimiento no puede estar vacío")
    @Schema(description = "Fecha de nacimiento del tenista", example = "2000-01-01")
    private LocalDate fechaNacimiento;
    @Min(value = 18, message = "La edad no puede ser menor a 18")
    @Max(value = 99, message = "La edad no puede ser mayor a 41")
    @Schema(description = "Edad del tenista", example = "1")
    @NotBlank(message = "La edad no puede estar vacío")
    private int Edad;
    @Schema(description = "Altura del tenista", example = "1")
    @NotBlank(message = "La altura no puede estar vacío")
    private int Altura;
    @Schema(description = "Peso del tenista", example = "1")
    @NotBlank(message = "El peso no puede estar vacío")
    private int Peso;
    @Schema(description = "Fecha de debut del tenista", example = "2000-01-01")
    @NotBlank(message = "La fecha de debut no puede estar vacío")
    private LocalDate DebutProfesional;
    @Schema(description = "Mano del tenista", example = "1")
    @NotBlank(message = "La mano que utiliza no puede estar vacío")
    private Mano Mano;
    @Schema(description = "Reves del tenista", example = "1")
    @NotBlank(message = "El reves que utiliza no puede estar vacío")
    private Reves Reves;
    @Schema(description = "Entrenador del tenista", example = "Cristiano")
    @NotBlank(message = "El entrenador no puede estar vacío")
    private String Entrenador;
    @Schema(description = "Dinero ganado por el tenista", example = "1")
    @NotBlank(message = "El dinero ganado no puede estar vacío")
    @Min(value = 0, message = "El dinero ganado no puede ser negativo")
    private Long DineroGanado;
    @Schema(description = "Mejor ranking del tenista", example = "1")
    @NotBlank(message = "El mejor ranking no puede estar vacío")
    @Min(value = 0, message = "El mejor ranking no puede ser negativo")
    private Long MejorRanking;
    @Schema(description = "Numero de victorias del tenista", example = "1")
    @NotBlank(message = "El numero de victorias no puede estar vacío")
    @Min(value = 0, message = "El numero de victorias no puede ser negativo")
    private int numVictorias;
    @NotBlank(message = "El numero de derrotas no puede estar vacío")
    @Min(value = 0, message = "El numero de derrotas no puede ser negativo")
    @Schema(description = "Numero de derrotas del tenista", example = "1")
    private int numDerrotas;
    @Schema(description = "Imagen del tenista", example = "https://via.placeholder.com/150")
    @Builder.Default
    private String imagen = IMAGE_DEFAULT;
    @Schema(description = "Puntos del tenista", example = "1")
    @NotBlank(message = "El numero de puntos no puede estar vacío")
    @Min(value = 0, message = "El numero de puntos no puede ser negativo")
    private int puntos;
}
