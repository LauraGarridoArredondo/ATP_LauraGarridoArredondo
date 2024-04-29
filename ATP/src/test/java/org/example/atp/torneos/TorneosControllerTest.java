package org.example.atp.torneos;

import org.example.atp.rest.torneos.controllers.TorneosController;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TorneosControllerTest {

    @Mock
    private TorneosRepository torneosRepository;

    @Mock
    private TorneosDTOConverter torneosDTOConverter;

    @Mock
    private TorneosService torneosService;

    @Mock
    private PaginationLinksUtils paginationLinksUtils;

    @InjectMocks
    private TorneosController torneosController;

    private TorneosDTO dummyTorneosDTO;
    private Torneos dummyTorneos;

    @BeforeEach
    void setUp() {
        dummyTorneosDTO = new TorneosDTO();
        dummyTorneosDTO.setId(1L);
        dummyTorneosDTO.setUbicacion("Dummy Ubicacion");
        // Set other properties

        dummyTorneos = new Torneos();
        dummyTorneos.setId(1L);
        dummyTorneos.setUbicacion("Dummy Ubicacion");
        // Set other properties
    }


    @Test
    void findAll_ReturnsNotFound_WhenNoTorneosFound() {
        // Mocking
        when(torneosService.findAll(any(Pageable.class))).thenReturn(Page.empty());

        // Execution
        ResponseEntity<?> responseEntity = torneosController.findAll(Pageable.unpaged(), null);

        // Assertion
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    // Test for getTorneosById
    @Test
    void getTorneosById_ReturnsTorneos_WhenIdExists() {
        // Mocking
        when(torneosRepository.findById(any())).thenReturn(java.util.Optional.of(dummyTorneos));

        // Execution
        Torneos torneos = torneosController.getTorneosById(1L);

        // Assertion
        assertEquals(dummyTorneos, torneos);
    }

    @Test
    void getTorneosById_ThrowsTorneosNotFoundException_WhenIdNotExists() {
        // Mocking
        when(torneosRepository.findById(any())).thenReturn(java.util.Optional.empty());

        // Execution & Assertion
        assertThrows(TorneosNotFoundException.class, () -> torneosController.getTorneosById(1L));
    }

}
