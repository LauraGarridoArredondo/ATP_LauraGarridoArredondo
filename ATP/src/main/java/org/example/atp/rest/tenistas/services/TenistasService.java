package org.example.atp.rest.tenistas.services;

import lombok.RequiredArgsConstructor;
import org.example.atp.rest.storage.controller.FicherosController;
import org.example.atp.rest.storage.services.StorageService;
import org.example.atp.rest.tenistas.models.Mano;
import org.example.atp.rest.tenistas.models.Reves;
import org.example.atp.rest.tenistas.models.Tenistas;
import org.example.atp.rest.tenistas.repositories.TenistasRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.example.atp.rest.tenistas.dto.CreateTenistasDTO;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TenistasService extends ServiceImpl<Tenistas, Long, TenistasRepository> {
    private final TenistasRepository tenistasRepository;
    private final StorageService storageService;

    public Tenistas createTenista (CreateTenistasDTO nuevo, MultipartFile file) {
        String urlImagen = null;

        if (!file.isEmpty()) {
            String imagen = storageService.store(file);
            urlImagen = MvcUriComponentsBuilder
                    .fromMethodName(FicherosController.class, "serveFile", imagen, null)
                    .build().toUriString();
        }
        Tenistas nuevoTenista = Tenistas.builder().build();
        nuevoTenista.setReves(nuevo.getReves());
        nuevoTenista.setMano(nuevo.getMano());
        nuevoTenista.setPais(nuevo.getPais());
        nuevoTenista.setAltura(nuevo.getAltura());
        nuevoTenista.setPeso(nuevo.getPeso());
        nuevoTenista.setDebutProfesional(nuevo.getDebutProfesional());
        nuevoTenista.setDineroGanado(nuevo.getDineroGanado());
        nuevoTenista.setFechaNacimiento(nuevo.getFechaNacimiento());
        nuevoTenista.setEntrenador(nuevo.getEntrenador());
        nuevoTenista.setMejorRanking(nuevo.getMejorRanking());
        nuevoTenista.setNumDerrotas(nuevo.getNumDerrotas());
        nuevoTenista.setNumVictorias(nuevo.getNumVictorias());
        nuevoTenista.setRanking(nuevo.getRanking());
        nuevoTenista.setNombreCompleto(nuevo.getNombreCompleto());
        nuevoTenista.setImagen(urlImagen);
        nuevoTenista.setEdad(nuevo.getEdad());
        nuevoTenista.setPuntos(nuevo.getPuntos());
        return this.save(nuevoTenista);
    }

    public Page<Tenistas> findByRanking(int ranking, Pageable pageable) {
        return tenistasRepository.findByRanking(ranking, pageable);
    }
    public Page<Tenistas> findbyPuntos(int puntos, Pageable pageable) {
        return tenistasRepository.findByRanking(puntos, pageable);
    }

    public Page<Tenistas> findByNombre(String txt, Pageable pageable) {
        return this.repositorio.findByNombreCompletoIgnoreCase(txt, pageable);
    }

    public Page<Tenistas> findByPais(String txt, Pageable pageable) {
        return this.repositorio.findByPaisIgnoreCase(txt, pageable);
    }

    public Page<Tenistas> findByAltura(int altura, Pageable pageable) {
        return this.repositorio.findByAltura(altura, pageable);
    }

    public Page<Tenistas> findByPeso(int peso, Pageable pageable) {
        return this.repositorio.findByPeso(peso, pageable);
    }

    public Page<Tenistas> findByEdad(int edad, Pageable pageable) {
        return this.repositorio.findByEdad(edad, pageable);
    }

    public Page<Tenistas> findByFechaNacimiento(LocalDate fechaNacimiento, Pageable pageable) {
        return this.repositorio.findByFechaNacimiento(fechaNacimiento, pageable);
    }

    public Page<Tenistas> findByDebutProfesional(LocalDate debutProfesional, Pageable pageable) {
        return this.repositorio.findByDebutProfesional(debutProfesional, pageable);
    }

    public Page<Tenistas> findByMano(Mano mano, Pageable pageable) {
        return this.repositorio.findByMano(mano, pageable);
    }

    public Page<Tenistas> findByReves(Reves reves, Pageable pageable) {
        return this.repositorio.findByReves(reves, pageable);
    }

    public Page<Tenistas> findByEntrenador(String txt, Pageable pageable) {
        return this.repositorio.findByEntrenador(txt, pageable);
    }

    public Page<Tenistas> findByDineroGanado(Long dineroGanado, Pageable pageable) {
        return this.repositorio.findByDineroGanado(dineroGanado, pageable);
    }

    public Page<Tenistas> findByMejorRanking(Long mejorRanking, Pageable pageable) {
        return this.repositorio.findByMejorRanking(mejorRanking, pageable);
    }

    public Page<Tenistas> findByNumVictorias(int numVictorias, Pageable pageable) {
        return this.repositorio.findByNumVictorias(numVictorias, pageable);
    }

    public Page<Tenistas> findByNumDerrotas(int numDerrotas, Pageable pageable) {
        return this.repositorio.findByNumDerrotas(numDerrotas, pageable);
    }
}
