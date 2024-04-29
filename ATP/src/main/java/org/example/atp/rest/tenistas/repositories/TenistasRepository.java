package org.example.atp.rest.tenistas.repositories;
import org.example.atp.rest.tenistas.models.Mano;
import org.example.atp.rest.tenistas.models.Reves;
import org.springframework.data.domain.Page;
import org.example.atp.rest.tenistas.models.Tenistas;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;

public interface TenistasRepository extends JpaRepository<Tenistas, Long>, JpaSpecificationExecutor<Tenistas> {
Page<Tenistas> findByRanking(int ranking, Pageable pageable);
Page<Tenistas> findByPuntos(int puntos, Pageable pageable);
Page<Tenistas> findByNombreCompletoIgnoreCase(String txt, Pageable pageable);

Page<Tenistas> findByPaisIgnoreCase(String txt, Pageable pageable);

Page<Tenistas> findByAltura(int altura, Pageable pageable);

Page<Tenistas> findByPeso(int peso, Pageable pageable);

Page<Tenistas> findByEdad(int edad, Pageable pageable);

Page<Tenistas> findByFechaNacimiento(LocalDate fechaNacimiento, Pageable pageable);
Page<Tenistas> findByDebutProfesional(LocalDate debutProfesional, Pageable pageable);

Page<Tenistas> findByMano(Mano mano, Pageable pageable);

Page<Tenistas> findByReves(Reves reves, Pageable pageable);

Page<Tenistas> findByEntrenador(String txt, Pageable pageable);

Page<Tenistas> findByDineroGanado(Long dineroGanado, Pageable pageable);

Page<Tenistas> findByMejorRanking(Long mejorRanking, Pageable pageable);

Page<Tenistas> findByNumVictorias(int numVictorias, Pageable pageable);

Page<Tenistas> findByNumDerrotas(int numDerrotas, Pageable pageable);

}
