package org.example.atp.rest.torneos.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.atp.rest.tenistas.models.Tenistas;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Torneos {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String ubicacion;
    private Tipo tipo;
    private Categoria categoria;
    private String modalidad;
    private Superficie superficie;
    //@ManyToOne
   // @JoinColumn(name = "tenistas_id")
    //private Tenistas vacantes;
    private Long vacantes;
    private String premios;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String imagen;
}
