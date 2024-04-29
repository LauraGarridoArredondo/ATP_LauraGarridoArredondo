package org.example.atp.rest.torneos.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.atp.rest.storage.controller.FicherosController;
import org.example.atp.rest.storage.services.StorageService;
import org.example.atp.rest.torneos.dto.CreateTorneosDTO;
import org.example.atp.rest.torneos.dto.TorneosDTO;
import org.example.atp.rest.torneos.exceptions.TorneosBadRequest;
import org.example.atp.rest.torneos.exceptions.TorneosNotFoundException;
import org.example.atp.rest.torneos.mappers.TorneosDTOConverter;
import org.example.atp.rest.torneos.models.Torneos;
import org.example.atp.rest.torneos.repositories.TorneosRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/torneos")
@Tag(name = "Torneos", description = "Endpoint de Torneos de ATP")
@RequiredArgsConstructor
public class TorneosController {
    private final TorneosRepository torneosRepository;
    private final TorneosDTOConverter torneosDTOConverter;
    private final StorageService storageService;
    private final Logger log = Logger.getLogger(TorneosController.class.getName());
    /**
     * Obtenenmos todos los torneos
     * @return
     */
    @GetMapping("/torneos")
    @Operation(summary = "Obtiene todos los torneos", description = "Obtiene una lista de todos los torneos")
    @Parameters({
            @Parameter(name = "id", description = "id del tenista", required = true, example = "1"),
            @Parameter(name = "ubicacion", description = "ubicacion del tenista", required = true, example = "Madrid"),
            @Parameter(name = "tipo", description = "tipo del torneo", required = true, example = "DOBLES"),
            @Parameter(name = "categoria", description = "categoria del torneo", required = true, example = "MASTER1000"),
            @Parameter(name = "modalidad", description = "modalidad del torneo", required = true, example = "Individual"),
            @Parameter(name = "superficie", description = "superficie del torneo", required = true, example = "ARCILLA"),
            @Parameter(name = "vacantes", description = "vacantes del torneo", required = true, example = "10"),
            @Parameter(name = "premios", description = "premios del torneo", required = true, example = "10"),
            @Parameter(name = "fechaInicio", description = "fecha de inicio del torneo", required = true, example = "2000-01-01"),
            @Parameter(name = "fechaFin", description = "fecha de fin del torneo", required = true, example = "2000-01-01"),
            @Parameter(name = "imagen", description = "imagen del torneo", required = true, example = "https://via.placeholder.com/150")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Página de torneos encontrada"),
    })
    public ResponseEntity<?> findAll() {
        List<Torneos> result = torneosRepository.findAll();
        if (result.isEmpty()) {
            log.info("Torneos no encontrados");
            return ResponseEntity.notFound().build();
        } else {
            log.info("Torneos encontrados: {}");
            List<TorneosDTO> dtoList = result.stream().map(torneosDTOConverter::convertToDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        }
    }

    /**
     * Obtenemos un torneo por su id
     *
     * @param id
     * @return Null si no encuentra el torneo
     */
    @Operation(summary = "Obtiene un torneo", description = "Obtiene un torneo por su id")
    @Parameters({
            @Parameter(name = "id", description = "Identificador del torneo", example = "1", required = true)
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Torneo encontrado"),
            @ApiResponse(responseCode = "404", description = "Torneo no encontrado"),
    })
    @GetMapping("/torneos/{id}")
    public Torneos getTorneosById(@PathVariable Long id) {
        log.info("Torneos encontrados: {}");
        return torneosRepository.findById(id)
                .orElseThrow(() -> new TorneosNotFoundException(id));
    }
    /**
     * Creamos un torneo mediante un json y una imagen
     * @return el torneo con sus datos establecidos en el json
     */
    @Operation(summary = "Crea un torneo", description = "Crea un torneo por medio de un json y una imagen")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Torneo a crear", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Torneo creado"),
            @ApiResponse(responseCode = "400", description = "Torneo no válido"),
    })
    @PostMapping(value = "/torneos" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createTorneo(@RequestPart ("nuevo") CreateTorneosDTO nuevo, @RequestPart("file") MultipartFile file) {
        String urlImagen= null;
        log.info("Imagen: " + urlImagen);
        if (!file.isEmpty()) {
            String imagen= storageService.store(file);
            urlImagen = MvcUriComponentsBuilder
                    .fromMethodName(FicherosController.class, "serveFile", imagen, null)
                    .build().toUriString();
        }

        try {
            log.info("Torneo creado: {}");
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
        }catch (Exception e){
            log.info("No se pudo crear el torneo." );
            throw new TorneosBadRequest("No se pudo crear el torneo. Detalles: " + e.getMessage());
        }

    }


    /**
     * Editamos un torneos sus datos
     * @param torneos
     * @param id
     * @return el torneos editado
     */
    @Operation(summary = "Edita un torneo", description = "Edita un torneo por su id y sus datos")
    @Parameters({
            @Parameter(name = "id", description = "Identificador del tenista", example = "1", required = true)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Torneo a actualizar", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Torneo actualizado"),
            @ApiResponse(responseCode = "400", description = "Torneo no válido"),
            @ApiResponse(responseCode = "404", description = "Torneo no encontrado"),
    })
    @PutMapping("/torneos/{id}")
    public Torneos editTorneo(@RequestBody Torneos torneos, @PathVariable Long id) {
        log.info("Torneo editado: {}");
        return torneosRepository.findById(id).map(p -> {
            p.setUbicacion(torneos.getUbicacion());
            p.setTipo(torneos.getTipo());
            p.setCategoria(torneos.getCategoria());
            p.setModalidad(torneos.getModalidad());
            p.setSuperficie(torneos.getSuperficie());
            p.setVacantes(torneos.getVacantes());
            p.setPremios(torneos.getPremios());
            p.setFechaInicio(torneos.getFechaInicio());
            p.setFechaFin(torneos.getFechaFin());
            p.setImagen(torneos.getImagen());
            return torneosRepository.save(p);
        }).orElseThrow(()->new TorneosNotFoundException(id));
    }
    /**
     * Borramos un torneo por su id
     * @param id
     * @return el torneo borrado
     */
    @Operation(summary = "Elimina un torneo", description = "Elimina un torneo por su id")
    @Parameters({
            @Parameter(name = "id", description = "Identificador del Torneo", example = "1", required = true)
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Torneo borrado"),
            @ApiResponse(responseCode = "404", description = "Torneo no encontrado"),
    })
    @DeleteMapping("/torneos/{id}")
    public ResponseEntity<?> deleteTorneo(@PathVariable Long id) {
        log.info("Torneo borrado: {}");
        Torneos torneos = torneosRepository.findById(id)
                .orElseThrow(() -> new TorneosNotFoundException(id));
        torneosRepository.delete(torneos);
        return ResponseEntity.noContent().build();
    }
}
