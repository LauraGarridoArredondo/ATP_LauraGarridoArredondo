package org.example.atp.rest.tenistas.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.atp.rest.torneos.models.Torneos;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Tenistas {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private long ranking;
    private String nombreCompleto;
    private String pais;
    private LocalDate fechaNacimiento;
    //La edad se calcula automaticamente
    private int edad;
    //La altura se mide en cm
    private int altura;
    //El peso se mide en kg
    private int peso;
    private LocalDate debutProfesional;
    private Mano mano;
    private Reves reves;
    private String entrenador;
    private Long dineroGanado;
    private Long mejorRanking;
    private int numVictorias;
    private int numDerrotas;
    private String imagen;
    private int puntos;
    @OneToMany(mappedBy = "vacantes")
    private List<Torneos> tenistas; //Relacion de uno a muchos entre tenistas y torneos
}
