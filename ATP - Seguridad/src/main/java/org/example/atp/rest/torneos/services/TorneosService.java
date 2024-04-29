package org.example.atp.rest.torneos.services;

import lombok.RequiredArgsConstructor;
import org.example.atp.rest.storage.controller.FicherosController;
import org.example.atp.rest.storage.services.StorageService;
import org.example.atp.rest.torneos.dto.CreateTorneosDTO;
import org.example.atp.rest.torneos.models.Categoria;
import org.example.atp.rest.torneos.models.Superficie;
import org.example.atp.rest.torneos.models.Tipo;
import org.example.atp.rest.torneos.models.Torneos;
import org.example.atp.rest.torneos.repositories.TorneosRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TorneosService extends ServiceImpl<Torneos, Long, TorneosRepository> {
    private final TorneosRepository torneosRepository;
    private final StorageService storageService;

    public ResponseEntity<Torneos> createTorneos (CreateTorneosDTO nuevo, MultipartFile file) {
        String urlImagen = null;

        if (!file.isEmpty()) {
            String imagen = storageService.store(file);
            urlImagen = MvcUriComponentsBuilder
                    .fromMethodName(FicherosController.class, "serveFile", imagen, null)
                    .build().toUriString();
        }
        Torneos nuevoTorneo = new Torneos();
        nuevoTorneo.setUbicacion(nuevo.getUbicacion());
        nuevoTorneo.setTipo(nuevo.getTipo());
        nuevoTorneo.setCategoria(nuevo.getCategoria());
        nuevoTorneo.setModalidad(nuevo.getModalidad());
        nuevoTorneo.setSuperficie(nuevo.getSuperficie());
        nuevoTorneo.setVacantes(nuevo.getVacantes());
        nuevoTorneo.setPremios(nuevo.getPremios());
        nuevoTorneo.setFechaInicio(nuevo.getFechaInicio());
        nuevoTorneo.setFechaFin(nuevo.getFechaFin());
        nuevoTorneo.setImagen(urlImagen);
        return ResponseEntity.status(HttpStatus.CREATED).body(torneosRepository.save(nuevoTorneo));
    }

    public Page<Torneos> findbyUbicacion(String ubicacion, Pageable pageable) {
        return this.repositorio.findByUbicacion(ubicacion, pageable);
    }
    public Page<Torneos> findbyTipo(Tipo tipo, Pageable pageable) {
        return this.repositorio.findByTipo(tipo, pageable);
    }
    public Page<Torneos> findbycategoria(Categoria categoria, Pageable pageable) {
        return this.repositorio.findByCategoria(categoria, pageable);
    }
    public Page<Torneos> findbyModalidad(String modalidad, Pageable pageable) {
        return this.repositorio.findByModalidad(modalidad, pageable);
    }
    public Page<Torneos> findbySuperficie(Superficie superficie, Pageable pageable) {
        return this.repositorio.findBySuperficie(superficie, pageable);
    }
    public Page<Torneos> findbyVacantes(Long vacantes, Pageable pageable) {
        return this.repositorio.findByVacantes(vacantes, pageable);
    }
    public Page<Torneos> findbyPremios(String premios, Pageable pageable) {
        return this.repositorio.findByPremios(premios, pageable);
    }
    public Page<Torneos> findbyFechaFin(LocalDate fechafin, Pageable pageable) {
        return this.repositorio.findByFechaFin(fechafin, pageable);
    }
    public Page<Torneos> findbyFechaInicio(LocalDate fechainicio, Pageable pageable) {
        return this.repositorio.findByFechaInicio(fechainicio, pageable);
    }
}
