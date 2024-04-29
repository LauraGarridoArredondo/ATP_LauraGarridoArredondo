package org.example.atp.torneos;

import org.example.atp.rest.torneos.models.Categoria;
import org.example.atp.rest.torneos.models.Superficie;
import org.example.atp.rest.torneos.models.Tipo;
import org.example.atp.rest.torneos.models.Torneos;
import org.example.atp.rest.torneos.repositories.TorneosRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class TorneosRepositoryTest {

    @Mock
    private TorneosRepository torneosRepository;

    @Test
    public void testFindByUbicacion() {
        Page<Torneos> page = torneosRepository.findByUbicacion("ubicacion", Pageable.unpaged());
    }

    @Test
    public void testFindByTipo() {
        Page<Torneos> page = torneosRepository.findByTipo(Tipo.INDIVIDUAL, Pageable.unpaged());
    }

    @Test
    public void testFindByCategoria() {
        Page<Torneos> page = torneosRepository.findByCategoria(Categoria.MASTER1000, Pageable.unpaged());
    }

    @Test
    public void testFindByModalidad() {
        Page<Torneos> page = torneosRepository.findByModalidad("modalidad", Pageable.unpaged());
    }

    @Test
    public void testFindBySuperficie() {
        Page<Torneos> page = torneosRepository.findBySuperficie(Superficie.HIERBA, Pageable.unpaged());
    }

    @Test
    public void testFindByVacantes() {
        Page<Torneos> page = torneosRepository.findByVacantes(10, Pageable.unpaged());
    }

    @Test
    public void testFindByPremios() {
        Page<Torneos> page = torneosRepository.findByPremios("premios", Pageable.unpaged());
    }

    @Test
    public void testFindByFechaFin() {
        Page<Torneos> page = torneosRepository.findByFechaFin(LocalDate.now(), Pageable.unpaged());
    }

    @Test
    public void testFindByFechaInicio() {
        Page<Torneos> page = torneosRepository.findByFechaInicio(LocalDate.now(), Pageable.unpaged());
    }
}
