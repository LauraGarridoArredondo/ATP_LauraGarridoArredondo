package org.example.atp.rest.tenistas.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.atp.rest.storage.controller.FicherosController;
import org.example.atp.rest.storage.services.StorageService;
import org.example.atp.rest.tenistas.dto.CreateTenistasDTO;
import org.example.atp.rest.tenistas.dto.TenistasDTO;
import org.example.atp.rest.tenistas.exceptions.TenistaNotFoundException;
import org.example.atp.rest.tenistas.exceptions.TenistasBadRequest;
import org.example.atp.rest.tenistas.mappers.TenistasDTOConverter;
import org.example.atp.rest.tenistas.models.Tenistas;
import org.example.atp.rest.tenistas.repositories.TenistasRepository;
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
@RequestMapping("/tenistas")
@Tag(name = "Tenistas", description = "Endpoint de Tenistas de ATP")
@RequiredArgsConstructor
public class TenistasController {
    private final TenistasRepository tenistasRepository;
    private final TenistasDTOConverter TenistasDTOConverter;
    private final StorageService storageService;
    private final Logger log = Logger.getLogger(TenistasController.class.getName());
    /**
     * Obtenenmos todos los tenistas
     * @return la lista de tenistas
     */
    @Operation(summary = "Obtiene todos los tenistas", description = "Obtiene una lista de tenistas")
   // @CrossOrigin(origins = "http://localhost:4200")
    @Parameters({
            @Parameter(name = "id", description = "id del tenista", required = true, example = "1"),
            @Parameter(name = "ranking", description = "ranking del tenista", required = true, example = "1"),
            @Parameter(name = "nombreCompleto", description = "nombre del tenista", required = true, example = "Antonio"),
            @Parameter(name = "pais", description = "pais del tenista", required = true, example = "España"),
            @Parameter(name = "fechaNacimiento", description = "fecha de nacimiento del tenista", required = true, example = "2000-01-01"),
            @Parameter(name = "edad", description = "edad del tenista", required = true, example = "30"),
            @Parameter(name = "altura", description = "altura del tenista", required = true, example = "180"),
            @Parameter(name = "peso", description = "peso del tenista", required = true, example = "80"),
            @Parameter(name = "debutProfesional", description = "debutProfesional del tenista", required = true, example = "2000-01-01"),
            @Parameter(name = "mano", description = "mano del tenista", required = true, example = "DIESTRO"),
            @Parameter(name = "reves", description = "reves del tenista", required = true, example = "UNAMANO"),
            @Parameter(name = "entrenador", description = "entrenador del tenista", required = true, example = "Antonio"),
            @Parameter(name = "dineroGanado", description = "dineroGanado del tenista", required = true, example = "100000"),
            @Parameter(name = "mejorRanking", description = "mejorRanking del tenista", required = true, example = "10"),
            @Parameter(name = "numVictorias", description = "numVictorias del tenista", required = true, example = "5"),
            @Parameter(name = "numDerrotas", description = "numDerrotas del tenista", required = true, example = "5"),
            @Parameter(name = "imagen", description = "imagen del tenista", required = true, example = "imagen.jpg"),
            @Parameter(name = "puntos", description = "puntos del tenista", required = true, example = "10")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Página de tenistas encontrada"),
    })
    @GetMapping("/tenistas")
    public ResponseEntity<?> findAll() {
        List<Tenistas> result = tenistasRepository.findAll();

        System.out.println(result);

        if (result.isEmpty()) {
            log.info("Tenistas no encontrados");
            return ResponseEntity.notFound().build();
        } else {
            List<TenistasDTO> dtoList = result.stream().map(TenistasDTOConverter::convertToDto)
                    .collect(Collectors.toList());
            log.info("Tenistas encontrados: {}");
            return ResponseEntity.ok(dtoList);
        }

    }

    /**
     * Obtenemos un tenista por su id
     * @param id el id del tenista
     * @return Null si no encuentra el tenista
     */

    @Operation(summary = "Obtiene el id de un tenista", description = "Obtiene un tenista mediante su id")
    @Parameters({
            @Parameter(name = "id", description = "Identificador del tenista", example = "1", required = true)
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tenista encontrado"),
            @ApiResponse(responseCode = "404", description = "Tenista no encontrado"),
    })
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
    @Operation(summary = "Crea un tenista con un json y una imagen", description = "Se crea un tenista mediante un json y una imagen")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Tenista a crear", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tenista creado"),
            @ApiResponse(responseCode = "400", description = "Tenista no válido"),
    })
    @PostMapping(value = "/tenistas", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createTenista(@RequestPart("nuevo") CreateTenistasDTO nuevo, @RequestPart("file") MultipartFile file) {

       String urlImagen= null;
        log.info("Imagen: " + urlImagen);
       if (!file.isEmpty()) {
           String imagen= storageService.store(file);
           urlImagen = MvcUriComponentsBuilder
                   .fromMethodName(FicherosController.class, "serveFile", imagen, null)
                   .build().toUriString();
       }
        try {
            log.info("Tenista creado: {}");
            Tenistas nuevoTenista = new Tenistas();
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
            log.info("Tenista creado");
            return ResponseEntity.status(HttpStatus.CREATED).body(tenistasRepository.save(nuevoTenista));
        } catch (Exception e) {
            throw new TenistasBadRequest("No se pudo crear el tenista. Detalles: " + e.getMessage());
        }
    }



    /**
     * Editamos un tenista sus datos
     * @param tenista el tenista
     * @param id el id del tenista
     * @return el tenista editado
     */
    @Operation(summary = "Edita un tenista", description = "Edita un tenista mediante su id")
    @Parameters({
            @Parameter(name = "id", description = "Identificador del tenista", example = "1", required = true)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Tenista a actualizar", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tenista actualizado"),
            @ApiResponse(responseCode = "400", description = "Tenista no válido"),
            @ApiResponse(responseCode = "404", description = "Tenista no encontrado"),
    })
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
    @Operation(summary = "Elimina un tenista", description = "Elimina un tenista por su id")
    @Parameters({
            @Parameter(name = "id", description = "Identificador del tenista", example = "1", required = true)
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tenista borrado"),
            @ApiResponse(responseCode = "404", description = "Tenista no encontrado"),
    })
    @DeleteMapping("/tenistas/{id}")
    public ResponseEntity<?> deleteTenista(@PathVariable Long id) {
        log.info("Tenista borrado: {}");
        Tenistas tenista = tenistasRepository.findById(id)
                .orElseThrow(() -> new TenistaNotFoundException(id));
        tenistasRepository.delete(tenista);
        return ResponseEntity.noContent().build();
    }
}
