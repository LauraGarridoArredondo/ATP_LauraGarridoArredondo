package org.example.atp.tenistas;

import org.example.atp.rest.tenistas.models.Tenistas;
import org.example.atp.rest.tenistas.repositories.TenistasRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.example.atp.rest.tenistas.models.Mano;
import org.example.atp.rest.tenistas.models.Reves;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TenistasRepositoryTest {

    @Autowired
    private TenistasRepository tenistasRepository;

    @Test
    public void testFindByRanking() {
        Page<Tenistas> tenistas = tenistasRepository.findByRanking(1, PageRequest.of(0, 10));
        assertEquals(1, tenistas.getTotalElements());
    }

    @Test
    public void testFindByPuntos() {
        Page<Tenistas> tenistas = tenistasRepository.findByPuntos(1000, PageRequest.of(0, 10));
    }

    @Test
    public void testFindByNombreCompletoIgnoreCase() {
        Page<Tenistas> tenistas = tenistasRepository.findByNombreCompletoIgnoreCase("Roger Federer", PageRequest.of(0, 10));
    }

    @Test
    public void testFindByPaisIgnoreCase() {
        Page<Tenistas> tenistas = tenistasRepository.findByPaisIgnoreCase("Suiza", PageRequest.of(0, 10));
    }

    @Test
    public void testFindByAltura() {
        Page<Tenistas> tenistas = tenistasRepository.findByAltura(185, PageRequest.of(0, 10));
        assertTrue(tenistas.getTotalElements() > 0);
    }

    @Test
    public void testFindByPeso() {
        Page<Tenistas> tenistas = tenistasRepository.findByPeso(80, PageRequest.of(0, 10));
        assertTrue(tenistas.getTotalElements() > 0);
    }

    @Test
    public void testFindByEdad() {
        Page<Tenistas> tenistas = tenistasRepository.findByEdad(35, PageRequest.of(0, 10));
    }

    @Test
    public void testFindByFechaNacimiento() {
        LocalDate fechaNacimiento = LocalDate.of(1985, 10, 28);
        Page<Tenistas> tenistas = tenistasRepository.findByFechaNacimiento(fechaNacimiento, PageRequest.of(0, 10));
    }

    @Test
    public void testFindByDebutProfesional() {
        LocalDate debutProfesional = LocalDate.of(2000, 1, 1);
        Page<Tenistas> tenistas = tenistasRepository.findByDebutProfesional(debutProfesional, PageRequest.of(0, 10));
    }

    @Test
    public void testFindByMano() {
        Page<Tenistas> tenistas = tenistasRepository.findByMano(Mano.DIESTRO, PageRequest.of(0, 10));
        assertTrue(tenistas.getTotalElements() > 0);
    }

    @Test
    public void testFindByReves() {
        Page<Tenistas> tenistas = tenistasRepository.findByReves(Reves.UNAMANO, PageRequest.of(0, 10));
        assertTrue(tenistas.getTotalElements() > 0);
    }

    @Test
    public void testFindByEntrenador() {
        Page<Tenistas> tenistas = tenistasRepository.findByEntrenador("Nombre del Entrenador", PageRequest.of(0, 10));
    }

    @Test
    public void testFindByDineroGanado() {
        Page<Tenistas> tenistas = tenistasRepository.findByDineroGanado(1000000L, PageRequest.of(0, 10));
        assertTrue(tenistas.getTotalElements() > 0);
    }

    @Test
    public void testFindByMejorRanking() {
        Page<Tenistas> tenistas = tenistasRepository.findByMejorRanking(1L, PageRequest.of(0, 10));
        assertTrue(tenistas.getTotalElements() > 0);
    }

    @Test
    public void testFindByNumVictorias() {
        Page<Tenistas> tenistas = tenistasRepository.findByNumVictorias(50, PageRequest.of(0, 10));
    }

    @Test
    public void testFindByNumDerrotas() {
        Page<Tenistas> tenistas = tenistasRepository.findByNumDerrotas(20, PageRequest.of(0, 10));
    }

}
