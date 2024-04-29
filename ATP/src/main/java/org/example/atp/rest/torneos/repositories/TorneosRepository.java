package org.example.atp.rest.torneos.repositories;

import org.example.atp.rest.torneos.models.Categoria;
import org.example.atp.rest.torneos.models.Superficie;
import org.example.atp.rest.torneos.models.Tipo;
import org.example.atp.rest.torneos.models.Torneos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;

public interface TorneosRepository extends JpaRepository<Torneos, Long>, JpaSpecificationExecutor<Torneos> {
    Page<Torneos> findByUbicacion(String ubicacion, Pageable pageable);
    Page<Torneos> findByTipo(Tipo tipo, Pageable pageable);
    Page<Torneos> findByCategoria(Categoria categoria, Pageable pageable);
    Page<Torneos> findByModalidad(String modalidad, Pageable pageable);
    Page<Torneos> findBySuperficie(Superficie superficie, Pageable pageable);

    Page<Torneos> findByVacantes(long vacantes, Pageable pageable);
    Page<Torneos> findByPremios(String premios, Pageable pageable);
    Page<Torneos> findByFechaFin(LocalDate fechafin, Pageable pageable);
    Page<Torneos> findByFechaInicio(LocalDate fechainicio, Pageable pageable);

}
