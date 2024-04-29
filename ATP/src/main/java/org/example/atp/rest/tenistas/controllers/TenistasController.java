package org.example.atp.rest.tenistas.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.atp.rest.tenistas.dto.CreateTenistasDTO;
import org.example.atp.rest.tenistas.dto.TenistasDTO;
import org.example.atp.rest.tenistas.exceptions.*;
import org.example.atp.rest.tenistas.mappers.TenistasDTOConverter;
import org.example.atp.rest.tenistas.models.Mano;
import org.example.atp.rest.tenistas.models.Reves;
import org.example.atp.rest.tenistas.models.Tenistas;
import org.example.atp.rest.tenistas.repositories.TenistasRepository;
import org.example.atp.rest.tenistas.services.TenistasService;
import org.example.atp.utils.pagination.PaginationLinksUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CacheConfig(cacheNames = "tenistas")
public class TenistasController {
    private final TenistasRepository tenistasRepository;
    private final TenistasDTOConverter TenistasDTOConverter;
    private final TenistasService tenistasService;
    private final PaginationLinksUtils paginationLinksUtils;
    private final Logger log = Logger.getLogger(TenistasController.class.getName());
    /**
     * Obtenenmos todos los tenistas
     * @return la lista de tenistas
     */
   // @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/tenistas")
    public ResponseEntity<?> findAll(@PageableDefault(size = 10, page = 0 ) Pageable pageable, HttpServletRequest request) {
        Page<Tenistas> result = tenistasService.findAll(pageable);

        System.out.println(result);

        if (result.isEmpty()) {
            log.info("Tenistas no encontrados");
            return ResponseEntity.notFound().build();
        } else {
            Page<TenistasDTO> dtoList = result.map(TenistasDTOConverter::convertToDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(dtoList, uriBuilder))
                    .body(dtoList);
        }

    }

    /**
     * Obtenemos un tenista por su id
     * @param id el id del tenista
     * @return Null si no encuentra el tenista
     */
    @Cacheable(key = "#id")
    @GetMapping("/tenistas/{id}")
    public Tenistas getTenistaById(@PathVariable Long id) {
        log.info("Tenistas encontrados: {}");
        return tenistasRepository.findById(id)
                .orElseThrow(() -> new TenistaNotFoundException(id));
    }
    /**
     * Creamos un tenista con un json y una imagen
     * @return el tenista con sus datos establecidos en el json
     */
    @CachePut(key = "#nuevo.id")
    @PostMapping(value = "/tenistas", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Tenistas> nuevoTenista(@RequestPart("nuevo") CreateTenistasDTO nuevo, @RequestPart("file") MultipartFile file) {
        log.info("Tenista creado: {}");
        return ResponseEntity.status(HttpStatus.CREATED).body(tenistasService.createTenista(nuevo, file));
    }

    /**
     * Editamos un tenista sus datos
     * @param tenista el tenista
     * @param id el id del tenista
     * @return el tenista editado
     */
    @PutMapping("/tenistas/{id}")
    public Tenistas editTenista(@RequestBody Tenistas tenista, @PathVariable Long id) {
        log.info("Tenista editado: {}");
        return tenistasRepository.findById(id).map(p -> {
            p.setAltura(tenista.getAltura());
            p.setEdad(tenista.getEdad());
            p.setPeso(tenista.getPeso());
            p.setEntrenador(tenista.getEntrenador());
            p.setDebutProfesional(tenista.getDebutProfesional());
            p.setDineroGanado(tenista.getDineroGanado());
            p.setFechaNacimiento(tenista.getFechaNacimiento());
            p.setMano(tenista.getMano());
            p.setImagen(tenista.getImagen());
            p.setMejorRanking(tenista.getMejorRanking());
            p.setNombreCompleto(tenista.getNombreCompleto());
            p.setNumDerrotas(tenista.getNumDerrotas());
            p.setNumVictorias(tenista.getNumVictorias());
            p.setPais(tenista.getPais());
            p.setReves(tenista.getReves());
            p.setPuntos(tenista.getPuntos());
            log.info("Tenista editado");
            return tenistasRepository.save(p);
        }).orElseThrow(()->new TenistaNotFoundException(id));
    }
    /**
     * Borramos un tenista por su id
     * @param id el id del tenista
     * @return el tenista borrado
     */
    @CacheEvict(key = "#id")
    @DeleteMapping("/tenistas/{id}")
    public ResponseEntity<Void> deleteTenista(@PathVariable Long id) {
        log.info("Tenista borrado: {}");
        Tenistas tenista = tenistasRepository.findById(id)
                .orElseThrow(() -> new TenistaNotFoundException(id));
        tenistasRepository.delete(tenista);
        return ResponseEntity.noContent().build();
    }

    /**
     * Busca un tenista por su ranking
     * @param ranking el ranking del tenista a obtener
     * @return 404 si no se encuentran resultados. 200 y el conjunto de tenistas si se encuentra
     */
    @GetMapping(value = "/tenistas/", params = "ranking")
    public ResponseEntity<?> findbyRanking(
            @RequestParam("ranking") int ranking,
            Pageable pageable, HttpServletRequest request) {

        Page<Tenistas> result = tenistasService.findByRanking(ranking, pageable);

        if (result.isEmpty()) {
            throw new TenistasRankingNotFound(ranking);
        } else {
            Page<TenistasDTO> dtoList = result.map(TenistasDTOConverter::convertToDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(dtoList, uriBuilder))
                    .body(dtoList);
        }
    }

    /**
     * Busca un tenista por sus puntos
     * @param puntos el ranking del tenista a obtener
     * @return 404 si no se encuentran resultados. 200 y el conjunto de tenistas si se encuentra
     */
    @GetMapping(value = "/tenistas/", params = "puntos")
    public ResponseEntity<?> findByPuntos(
            @RequestParam("puntos") int puntos,
            Pageable pageable, HttpServletRequest request) {

        Page<Tenistas> result = tenistasService.findbyPuntos(puntos, pageable);

        if (result.isEmpty()) {
            throw new TenistasPuntosNotFound(puntos);
        } else {
            Page<TenistasDTO> dtoList = result.map(TenistasDTOConverter::convertToDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(dtoList, uriBuilder))
                    .body(dtoList);
        }
    }

    /**
     *  Busca un tenista por su nombre
     * @param txt el nombre del tenista a obtener
     * @return 404 si no se encuentran resultados. 200 y el conjunto de tenistas si se encuentra
     */
    @GetMapping(value = "/tenistas/", params = "nombreCompleto")
    public ResponseEntity<?> findByNombreCompletoIgnoreCase(
            @RequestParam("nombreCompleto") String txt,
            Pageable pageable, HttpServletRequest request) {

        Page<Tenistas> result = tenistasService.findByNombre(txt, pageable);

        if (result.isEmpty()) {
            throw new TenistasNotFoundByNombre(txt);
        } else {

            Page<TenistasDTO> dtoList = result.map(TenistasDTOConverter::convertToDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(dtoList, uriBuilder))
                    .body(dtoList);

        }
    }
    /**
     * Busca un tenista por su pais
     * @param txt el nombre del tenista a obtener
     * @return 404 si no se encuentran resultados. 200 y el conjunto de tenistas si se encuentra
     */
    @GetMapping(value = "/tenistas/", params = "pais")
    public ResponseEntity<?> findByPaisIgnoreCase(
            @RequestParam("pais") String txt,
            Pageable pageable, HttpServletRequest request) {

        Page<Tenistas> result = tenistasService.findByPais(txt, pageable);

        if (result.isEmpty()) {
            throw new TenistasNotFoundByPais(txt);
        } else {
            Page<TenistasDTO> dtoList = result.map(TenistasDTOConverter::convertToDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(dtoList, uriBuilder))
                    .body(dtoList);

        }
    }

    /**
     * Busca un tenista por su altura
     * @param altura la altura del tenista a obtener
     * @return 404 si no se encuentran resultados. 200 y el conjunto de tenistas si se encuentra
     */
    @GetMapping(value = "/tenistas/", params = "altura")
    public ResponseEntity<?> findByAltura(
            @RequestParam("altura") int altura,
            Pageable pageable, HttpServletRequest request) {

        Page<Tenistas> result = tenistasService.findByAltura(altura, pageable);

        if (result.isEmpty()) {
            throw new TenistasNotFoundByAltura(altura);
        } else {
            Page<TenistasDTO> dtoList = result.map(TenistasDTOConverter::convertToDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(dtoList, uriBuilder))
                    .body(dtoList);

        }
    }

    /**
     * Busca un tenista por su peso
     * @param peso el peso del tenista a obtener
     * @return 404 si no se encuentran resultados. 200 y el conjunto de tenistas si se encuentra
     */
    @GetMapping(value = "/tenistas/", params = "peso")
    public ResponseEntity<?> findByPeso(
            @RequestParam("peso") int peso,
            Pageable pageable, HttpServletRequest request) {

        Page<Tenistas> result = tenistasService.findByPeso(peso, pageable);

        if (result.isEmpty()) {
            throw new TenistasNotFoundByPeso(peso);
        } else {
            Page<TenistasDTO> dtoList = result.map(TenistasDTOConverter::convertToDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(dtoList, uriBuilder))
                    .body(dtoList);

        }
    }

    /**
     * Busca un tenista por su edad
     * @param edad la edad del tenista a obtener
     * @return 404 si no se encuentran resultados. 200 y el conjunto de tenistas si se encuentra
     */
    @GetMapping(value = "/tenistas/", params = "edad")
    public ResponseEntity<?> findByEdad(
            @RequestParam("edad") int edad,
            Pageable pageable, HttpServletRequest request) {

        Page<Tenistas> result = tenistasService.findByEdad(edad, pageable);

        if (result.isEmpty()) {
            throw new TenistasNotFoundByEdad(edad);
        } else {
            Page<TenistasDTO> dtoList = result.map(TenistasDTOConverter::convertToDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(dtoList, uriBuilder))
                    .body(dtoList);

        }
    }

    /**
     * Busca un tenista por su fecha de nacimiento
     * @param fechaNacimiento la fecha de nacimiento del tenista a obtener
     * @return 404 si no se encuentran resultados. 200 y el conjunto de tenistas si se encuentra
     */
    @GetMapping(value = "/tenistas/", params = "fechaNacimiento")
    public ResponseEntity<?> findByFechaNacimiento(
            @RequestParam("fechaNacimiento") LocalDate fechaNacimiento,
            Pageable pageable, HttpServletRequest request) {

        Page<Tenistas> result = tenistasService.findByFechaNacimiento(fechaNacimiento, pageable);

        if (result.isEmpty()) {
            throw new TenistasNotFoundByFechaNacimiento(fechaNacimiento);
        } else {
            Page<TenistasDTO> dtoList = result.map(TenistasDTOConverter::convertToDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(dtoList, uriBuilder))
                    .body(dtoList);

        }
    }

    /**
     * Busca un tenista por su debut Profesional
     * @param debutProfesional la fecha de debut Profesional del tenista a obtener
     * @return 404 si no se encuentran resultados. 200 y el conjunto de tenistas si se encuentra
     */
    @GetMapping(value = "/tenistas/", params = "debutProfesional")
    public ResponseEntity<?> findByDebutProfesional(
            @RequestParam("debutProfesional") LocalDate debutProfesional,
            Pageable pageable, HttpServletRequest request) {

        Page<Tenistas> result = tenistasService.findByDebutProfesional(debutProfesional, pageable);

        if (result.isEmpty()) {
            throw new TenistasNotFoundByDebutProfesional(debutProfesional);
        } else {
            Page<TenistasDTO> dtoList = result.map(TenistasDTOConverter::convertToDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(dtoList, uriBuilder))
                    .body(dtoList);

        }
    }

    /**
     * Busca un tenista por su mano dominante
     * @param mano la mano dominante del tenista a obtener
     * @return 404 si no se encuentran resultados. 200 y el conjunto de tenistas si se encuentra
     */
    @GetMapping(value = "/tenistas/", params = "mano")
    public ResponseEntity<?> findByMano(
            @RequestParam("mano") Mano mano,
            Pageable pageable, HttpServletRequest request) {

        Page<Tenistas> result = tenistasService.findByMano(mano, pageable);

        if (result.isEmpty()) {
            throw new TenistasNotFoundMano(mano);
        } else {
            Page<TenistasDTO> dtoList = result.map(TenistasDTOConverter::convertToDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(dtoList, uriBuilder))
                    .body(dtoList);

        }
    }
    /**
     * Busca un tenista por su reves
     * @param reves la reves del tenista a obtener
     * @return 404 si no se encuentran resultados. 200 y el conjunto de tenistas si se encuentra
     */
    @GetMapping(value = "/tenistas/", params = "reves")
    public ResponseEntity<?> findByMano(
            @RequestParam("reves") Reves reves,
            Pageable pageable, HttpServletRequest request) {

        Page<Tenistas> result = tenistasService.findByReves(reves, pageable);

        if (result.isEmpty()) {
            throw new TenistasNotFoundByReves(reves);
        } else {
            Page<TenistasDTO> dtoList = result.map(TenistasDTOConverter::convertToDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(dtoList, uriBuilder))
                    .body(dtoList);

        }
    }
    /**
     * Busca un tenista por su Entrenador
     * @param entrenador el entrenador del tenista a obtener
     * @return 404 si no se encuentran resultados. 200 y el conjunto de tenistas si se encuentra
     */
    @GetMapping(value = "/tenistas/", params = "entrenador")
    public ResponseEntity<?> findByEntrenador(
            @RequestParam("entrenador") String entrenador,
            Pageable pageable, HttpServletRequest request) {

        Page<Tenistas> result = tenistasService.findByEntrenador(entrenador, pageable);

        if (result.isEmpty()) {
            throw new TenistasNotFoundByEntrenador(entrenador);
        } else {
            Page<TenistasDTO> dtoList = result.map(TenistasDTOConverter::convertToDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(dtoList, uriBuilder))
                    .body(dtoList);

        }
    }

    /**
     * Busca un tenista por su dinero ganado
     * @param dineroGanado el dinero ganado del tenista a obtener
     * @return 404 si no se encuentran resultados. 200 y el conjunto de tenistas si se encuentra
     */
    @GetMapping(value = "/tenistas/", params = "dineroGanado")
    public ResponseEntity<?> findByDineroGanado(
            @RequestParam("dineroGanado") long dineroGanado,
            Pageable pageable, HttpServletRequest request) {

        Page<Tenistas> result = tenistasService.findByDineroGanado(dineroGanado, pageable);

        if (result.isEmpty()) {
            throw new TenistasNotFoundByDineroGanado(dineroGanado);
        } else {
            Page<TenistasDTO> dtoList = result.map(TenistasDTOConverter::convertToDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(dtoList, uriBuilder))
                    .body(dtoList);

        }
    }
    /**
     * Busca un tenista por su mejor ranking
     * @param mejorRanking el mejor ranking del tenista a obtener
     * @return 404 si no se encuentran resultados. 200 y el conjunto de tenistas si se encuentra
     */
    @GetMapping(value = "/tenistas/", params = "mejorRanking")
    public ResponseEntity<?> findByMejorRanking(
            @RequestParam("mejorRanking") long mejorRanking,
            Pageable pageable, HttpServletRequest request) {

        Page<Tenistas> result = tenistasService.findByMejorRanking(mejorRanking, pageable);

        if (result.isEmpty()) {
            throw new TenistasNotFoundBymejorRanking(mejorRanking);
        } else {
            Page<TenistasDTO> dtoList = result.map(TenistasDTOConverter::convertToDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(dtoList, uriBuilder))
                    .body(dtoList);

        }
    }

    /**
     * Busca un tenista por su numVictorias
     * @param numVictorias el numVictorias del tenista a obtener
     * @return 404 si no se encuentran resultados. 200 y el conjunto de tenistas si se encuentra
     */
    @GetMapping(value = "/tenistas/", params = "numVictorias")
    public ResponseEntity<?> findBynumVictorias(
            @RequestParam("numVictorias") int numVictorias,
            Pageable pageable, HttpServletRequest request) {

        Page<Tenistas> result = tenistasService.findByNumVictorias(numVictorias, pageable);

        if (result.isEmpty()) {
            throw new TenistasNotFoundBynumVictorias(numVictorias);
        } else {
            Page<TenistasDTO> dtoList = result.map(TenistasDTOConverter::convertToDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(dtoList, uriBuilder))
                    .body(dtoList);

        }
    }
    /**
     * Busca un tenista por su numDerrotas
     * @param numDerrotas el numDerrotas del tenista a obtener
     * @return 404 si no se encuentran resultados. 200 y el conjunto de tenistas si se encuentra
     */
    @GetMapping(value = "/tenistas/", params = "numDerrotas")
    public ResponseEntity<?> findBynumDerrotas(
            @RequestParam("numDerrotas") int numDerrotas,
            Pageable pageable, HttpServletRequest request) {

        Page<Tenistas> result = tenistasService.findByNumDerrotas(numDerrotas, pageable);

        if (result.isEmpty()) {
            throw new TenistasNotFoundBynumDerrotas(numDerrotas);
        } else {
            Page<TenistasDTO> dtoList = result.map(TenistasDTOConverter::convertToDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(dtoList, uriBuilder))
                    .body(dtoList);

        }
    }

    /**
     * Obtiene el primer tenista con más puntos en la lista DTO de tenistas.
     * @return ResponseEntity con el tenista con más puntos
     */
    @GetMapping("/tenistas/primero-con-mas-puntos")
    public ResponseEntity<?> getTenistaConMasPuntos() {
        Page<Tenistas> result = tenistasService.findAll(Pageable.unpaged());
        List<TenistasDTO> dtoList = result.getContent().stream()
                .map(TenistasDTOConverter::convertToDto)
                .collect(Collectors.toList());

        if (dtoList.isEmpty()) {
            log.info("No se encontraron tenistas");
            return ResponseEntity.notFound().build();
        }

        TenistasDTO tenistaConMasPuntos = dtoList.stream()
                .max(Comparator.comparingInt(TenistasDTO::getPuntos))
                .orElseThrow(() -> new IllegalStateException("No se pudo encontrar el tenista con más puntos"));

        return ResponseEntity.ok().body(tenistaConMasPuntos);
    }
}
