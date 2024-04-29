package org.example.atp.tenistas;

import org.example.atp.rest.tenistas.controllers.TenistasController;
import org.example.atp.rest.tenistas.exceptions.*;
import org.example.atp.rest.tenistas.mappers.TenistasDTOConverter;
import org.example.atp.rest.tenistas.models.Tenistas;
import org.example.atp.rest.tenistas.repositories.TenistasRepository;
import org.example.atp.rest.tenistas.services.TenistasService;
import org.example.atp.utils.pagination.PaginationLinksUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class TenistasControllerTest {

    @Mock
    private TenistasRepository tenistasRepository;

    @Mock
    private TenistasDTOConverter tenistasDTOConverter;

    @Mock
    private TenistasService tenistasService;

    @Mock
    private PaginationLinksUtils paginationLinksUtils;

    @InjectMocks
    private TenistasController tenistasController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testGetTenistaById() {
        Tenistas tenista = new Tenistas();
        Long id = 1L;
        when(tenistasRepository.findById(id)).thenReturn(Optional.of(tenista));

        Tenistas response = tenistasController.getTenistaById(id);

        assertEquals(tenista, response);
    }

    @Test
    public void testGetTenistaById_NotFound() {
        Long id = 1L;
        when(tenistasRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(TenistaNotFoundException.class, () -> {
            tenistasController.getTenistaById(id);
        });
    }



    @Test
    public void testEditTenista_NotFound() {
        Long id = 1L;
        when(tenistasRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(TenistaNotFoundException.class, () -> {
            tenistasController.editTenista(new Tenistas(), id);
        });
    }

    @Test
    public void testDeleteTenista() {
        Long id = 1L;
        Tenistas tenista = new Tenistas();
        when(tenistasRepository.findById(id)).thenReturn(Optional.of(tenista));

        ResponseEntity<Void> responseEntity = tenistasController.deleteTenista(id);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteTenista_NotFound() {
        Long id = 1L;
        when(tenistasRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(TenistaNotFoundException.class, () -> {
            tenistasController.deleteTenista(id);
        });
    }
}
