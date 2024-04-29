package org.example.atp.rest.torneos.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.atp.rest.torneos.dto.CreateTorneosDTO;
import org.example.atp.rest.torneos.dto.TorneosDTO;
import org.example.atp.rest.torneos.exceptions.*;
import org.example.atp.rest.torneos.mappers.TorneosDTOConverter;
import org.example.atp.rest.torneos.models.Categoria;
import org.example.atp.rest.torneos.models.Superficie;
import org.example.atp.rest.torneos.models.Tipo;
import org.example.atp.rest.torneos.models.Torneos;
import org.example.atp.rest.torneos.repositories.TorneosRepository;
import org.example.atp.rest.torneos.services.TorneosService;
import org.example.atp.utils.pagination.PaginationLinksUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
@CacheConfig(cacheNames = "torneos")
public class TorneosController {
    private final TorneosRepository torneosRepository;
    private final TorneosDTOConverter torneosDTOConverter;
    private final TorneosService torneosService;
    private final PaginationLinksUtils paginationLinksUtils;
    private final Logger log = Logger.getLogger(TorneosController.class.getName());
    /**
     * Obtenemos todos los torneos
     * @return
     */
    @GetMapping("/torneos")
    public ResponseEntity<?> findAll(@PageableDefault(size = 10, page = 0 ) Pageable pageable, HttpServletRequest request) {
        Page<Torneos> result = torneosService.findAll(pageable);

        System.out.println(result);

        if (result.isEmpty()) {
            log.info("Torneos no encontrados");
            return ResponseEntity.notFound().build();
        } else {
            Page<TorneosDTO> dtoList = result.map(torneosDTOConverter::convertToDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(dtoList, uriBuilder))
                    .body(dtoList);
        }

    }
    /**
     * Obtenemos un torneo por su id
     *
     * @param id
     * @return Null si no encuentra el torneo
     */
    @Cacheable(key = "#id")
    @GetMapping("/torneos/{id}")
    public Torneos getTorneosById(@PathVariable Long id) {
        log.info("Torneos encontrados: {}");
        return torneosRepository.findById(id)
                .orElseThrow(() -> new TorneosNotFoundException(id));
    }
    /**
     * Creamos un torneo mediante un json y una imagen
     *
     * @return el torneo con sus datos establecidos en el json
     */
    @CachePut(key = "#nuevo.id")
    @PostMapping(value = "/torneos", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseEntity<Torneos>> nuevoTorneo(@RequestPart("nuevo") CreateTorneosDTO nuevo, @RequestPart("file") MultipartFile file) {
        log.info("Tenista creado: {}");
        return ResponseEntity.status(HttpStatus.CREATED).body(torneosService.createTorneos(nuevo, file));
    }


    /**
     * Editamos un torneos sus datos
     * @param torneos
     * @param id
     * @return el torneos editado
     */
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
    @CacheEvict(key = "#id")
    @DeleteMapping("/torneos/{id}")
    public ResponseEntity<Void> deleteTorneo(@PathVariable Long id) {
        log.info("Torneo borrado: {}");
        Torneos torneos = torneosRepository.findById(id)
                .orElseThrow(() -> new TorneosNotFoundException(id));
        torneosRepository.delete(torneos);
        return ResponseEntity.noContent().build();
    }

    /**
     * Buscamos torneos por ubicacion
     * @param ubicacion ubicacion del torneo a buscar
     * @return la lista de torneos
     */
    @GetMapping(value = "/torneos/", params = "ubicacion")
    public ResponseEntity<?> findByUbicacion(
            @RequestParam("ubicacion") String ubicacion,
            Pageable pageable, HttpServletRequest request) {

        Page<Torneos> result = torneosService.findbyUbicacion(ubicacion, pageable);

        if (result.isEmpty()) {
            throw new TorneosNotFoundUbicacion(ubicacion);
        } else {
            Page<TorneosDTO> dtoList = result.map(torneosDTOConverter::convertToDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(dtoList, uriBuilder))
                    .body(dtoList);

        }
    }
    /**
     * Buscamos torneos por tipo
     * @param tipo tipo del torneo a buscar
     * @return la lista de torneos
     */
    @GetMapping(value = "/torneos/", params = "tipo")
    public ResponseEntity<?> findBytipo(
            @RequestParam("tipo") Tipo tipo,
            Pageable pageable, HttpServletRequest request) {

        Page<Torneos> result = torneosService.findbyTipo(tipo, pageable);

        if (result.isEmpty()) {
            throw new TorneosNotFoundTipo(tipo);
        } else {
            Page<TorneosDTO> dtoList = result.map(torneosDTOConverter::convertToDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(dtoList, uriBuilder))
                    .body(dtoList);

        }
    }

    /**
     * Buscamos torneos por categoria
     * @param categoria categoria del torneo a buscar
     * @return la lista de torneos
     */
    @GetMapping(value = "/torneos/", params = "categoria")
    public ResponseEntity<?> findByCategoria(
            @RequestParam("categoria") Categoria categoria,
            Pageable pageable, HttpServletRequest request) {

        Page<Torneos> result = torneosService.findbycategoria(categoria, pageable);

        if (result.isEmpty()) {
            throw new TorneosNotFoundCategoria(categoria);
        } else {
            Page<TorneosDTO> dtoList = result.map(torneosDTOConverter::convertToDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(dtoList, uriBuilder))
                    .body(dtoList);

        }
    }

    /**
     * Buscamos torneos por modalidad
     * @param modalidad modalidad del torneo a buscar
     * @return la lista de torneos
     */
    @GetMapping(value = "/torneos/", params = "modalidad")
    public ResponseEntity<?> findByModalidad(
            @RequestParam("modalidad") String modalidad,
            Pageable pageable, HttpServletRequest request) {

        Page<Torneos> result = torneosService.findbyModalidad(modalidad, pageable);

        if (result.isEmpty()) {
            throw new TorneosNotFoundModalidad(modalidad);
        } else {
            Page<TorneosDTO> dtoList = result.map(torneosDTOConverter::convertToDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(dtoList, uriBuilder))
                    .body(dtoList);

        }
    }

    /**
     * Buscamos torneos por superficie
     * @param superficie superficie del torneo a buscar
     * @return la lista de torneos
     */
    @GetMapping(value = "/torneos/", params = "superficie")
    public ResponseEntity<?> findBySuperficie(
            @RequestParam("superficie") Superficie superficie,
            Pageable pageable, HttpServletRequest request) {

        Page<Torneos> result = torneosService.findbySuperficie(superficie, pageable);

        if (result.isEmpty()) {
            throw new TorneosNotFoundSuperficie(superficie);
        } else {
            Page<TorneosDTO> dtoList = result.map(torneosDTOConverter::convertToDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(dtoList, uriBuilder))
                    .body(dtoList);

        }
    }
    /**
     * Buscamos torneos por vacantes
     * @param vacantes vacantes del torneo a buscar
     * @return la lista de torneos
     */
    @GetMapping(value = "/torneos/", params = "vacantes")
    public ResponseEntity<?> findByVacantes(
            @RequestParam("vacantes") Long vacantes,
            Pageable pageable, HttpServletRequest request) {

        Page<Torneos> result = torneosService.findbyVacantes(vacantes, pageable);

        if (result.isEmpty()) {
            throw new TorneosNotFoundVacantes(vacantes);
        } else {
            Page<TorneosDTO> dtoList = result.map(torneosDTOConverter::convertToDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(dtoList, uriBuilder))
                    .body(dtoList);

        }
    }
    /**
     * Buscamos torneos por premios
     * @param premios premios del torneo a buscar
     * @return la lista de torneos
     */
    @GetMapping(value = "/torneos/", params = "premios")
    public ResponseEntity<?> findByPremios(
            @RequestParam("premios") String premios,
            Pageable pageable, HttpServletRequest request) {

        Page<Torneos> result = torneosService.findbyPremios(premios, pageable);

        if (result.isEmpty()) {
            throw new TorneosNotFoundPremios(premios);
        } else {
            Page<TorneosDTO> dtoList = result.map(torneosDTOConverter::convertToDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(dtoList, uriBuilder))
                    .body(dtoList);

        }
    }
    /**
     * Buscamos torneos por su fecha de inicio
     * @param fechainicio fechainicio del torneo a buscar
     * @return la lista de torneos
     */
    @GetMapping(value = "/torneos/", params = "fechainicio")
    public ResponseEntity<?> findByFechaInicio(
            @RequestParam("fechainicio") LocalDate fechainicio,
            Pageable pageable, HttpServletRequest request) {

        Page<Torneos> result = torneosService.findbyFechaInicio(fechainicio, pageable);

        if (result.isEmpty()) {
            throw new TorneosNotFoundFechaInicio(fechainicio);
        } else {
            Page<TorneosDTO> dtoList = result.map(torneosDTOConverter::convertToDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(dtoList, uriBuilder))
                    .body(dtoList);

        }
    }
    /**
     * Buscamos torneos por su fecha de finalizacion
     * @param fechafin fechafin del torneo a buscar
     * @return la lista de torneos
     */
    @GetMapping(value = "/torneos/", params = "fechafin")
    public ResponseEntity<?> findByFechaFin(
            @RequestParam("fechafin") LocalDate fechafin,
            Pageable pageable, HttpServletRequest request) {

        Page<Torneos> result = torneosService.findbyFechaFin(fechafin, pageable);

        if (result.isEmpty()) {
            throw new TorneosNotFoundFechaFin(fechafin);
        } else {
            Page<TorneosDTO> dtoList = result.map(torneosDTOConverter::convertToDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(dtoList, uriBuilder))
                    .body(dtoList);

        }
    }
}
