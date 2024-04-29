package org.example.atp.tenistas;

import org.example.atp.rest.tenistas.dto.CreateTenistasDTO;
import org.example.atp.rest.tenistas.models.Mano;
import org.example.atp.rest.tenistas.models.Reves;
import org.example.atp.rest.tenistas.models.Tenistas;
import org.example.atp.rest.tenistas.repositories.TenistasRepository;
import org.example.atp.rest.storage.services.StorageService;
import org.example.atp.rest.tenistas.services.TenistasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class TenistasServiceTest {

    @Mock
    private TenistasRepository tenistasRepository;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private TenistasService tenistasService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        reset(tenistasRepository, storageService); // Resetear los mocks antes de cada prueba
    }


//    @Test
//    public void testCreateTenista() {
//        // Configuración de datos para el nuevo tenista
//        CreateTenistasDTO nuevo = CreateTenistasDTO.builder()
//                .Reves(Reves.DOSMANOS)
//                .Mano(Mano.DIESTRO)
//                .Pais("Espana")
//                .Altura((int) 1.75)
//                .Peso((int) 75.0)
//                .DebutProfesional(LocalDate.of(2020, 1, 1))
//                .DineroGanado((long) 100.0)
//                .fechaNacimiento(LocalDate.of(1990, 1, 1))
//                .Entrenador("Entrenador")
//                .MejorRanking(1L)
//                .numDerrotas(0)
//                .numVictorias(0)
//                .Ranking(1)
//                .NombreCompleto("Nombre Completo")
//                .Edad(30)
//                .puntos(0)
//                .build();
//
//        // Configuración del archivo adjunto
//        MultipartFile file = new MockMultipartFile("test.jpg", new byte[0]);
//
//        // Mock del método store del storageService
//        when(storageService.store(any(MultipartFile.class))).thenReturn("test.jpg");
//    }



//    @Test
//    public void testFindByRanking() {
//        Pageable pageable = Pageable.unpaged();
//        List<Tenistas> tenistasList = new ArrayList<>();
//        tenistasList.add(new Tenistas());
//        Page<Tenistas> page = new PageImpl<>(tenistasList);
//
//        when(tenistasRepository.findByRanking(anyInt(), any(Pageable.class))).thenReturn(page);
//
//        Page<Tenistas> result = tenistasService.findByRanking(1, pageable);
//
//        assertNotNull(result);
//        assertEquals(1, result.getContent().size());
//
//        verify(tenistasRepository, times(1)).findByRanking(anyInt(), any(Pageable.class));
//    }

//    @Test
//    public void testFindByPuntos() {
//        Pageable pageable = Pageable.unpaged();
//        List<Tenistas> tenistasList = new ArrayList<>();
//        tenistasList.add(new Tenistas());
//        Page<Tenistas> page = new PageImpl<>(tenistasList);
//
//        when(tenistasRepository.findByPuntos(anyInt(), any(Pageable.class))).thenReturn(page);
//
//        Page<Tenistas> result = tenistasService.findbyPuntos(1, pageable);
//
//        verify(tenistasRepository, times(1));
//    }

    // Test para findByNombre
    //@Test
   // public void testFindByNombre() {
       // Pageable pageable = Pageable.unpaged();
      //  List<Tenistas> tenistasList = new ArrayList<>();
       // tenistasList.add(new Tenistas());
       // Page<Tenistas> page = new PageImpl<>(tenistasList);

        //when(tenistasRepository.findByNombreCompletoIgnoreCase(anyString(), any(Pageable.class))).thenReturn(page);

       // Page<Tenistas> result = tenistasService.findByNombre("nombre", pageable);

       // assertNotNull(result);
      //  assertEquals(1, result.getContent().size());

       // verify(tenistasRepository, times(1)).findByNombreCompletoIgnoreCase(anyString(), any(Pageable.class));
    }

    // Test para findByPais
//    @Test
   // public void testFindByPais() {
      //  Pageable pageable = Pageable.unpaged();
      //  List<Tenistas> tenistasList = new ArrayList<>();
       // tenistasList.add(new Tenistas());
       /// Page<Tenistas> page = new PageImpl<>(tenistasList);

        //when(tenistasRepository.findByPaisIgnoreCase(anyString(), any(Pageable.class))).thenReturn(page);

//        Page<Tenistas> result = tenistasService.findByPais("pais", pageable);
//
//        assertNotNull(result);
//        assertEquals(1, result.getContent().size());
//
//        verify(tenistasRepository, times(1)).findByPaisIgnoreCase(anyString(), any(Pageable.class));
//    }
//
//    // Test para findByAltura
//    @Test
//    public void testFindByAltura() {
//        Pageable pageable = Pageable.unpaged();
//        List<Tenistas> tenistasList = new ArrayList<>();
//        tenistasList.add(new Tenistas());
//        Page<Tenistas> page = new PageImpl<>(tenistasList);
//
//        when(tenistasRepository.findByAltura(anyInt(), any(Pageable.class))).thenReturn(page);
//
//        Page<Tenistas> result = tenistasService.findByAltura(180, pageable);
//
//        assertNotNull(result);
//        assertEquals(1, result.getContent().size());
//
//        verify(tenistasRepository, times(1)).findByAltura(anyInt(), any(Pageable.class));
//    }
//
//    // Test para findByPeso
//    @Test
//    public void testFindByPeso() {
//        Pageable pageable = Pageable.unpaged();
//        List<Tenistas> tenistasList = new ArrayList<>();
//        tenistasList.add(new Tenistas());
//        Page<Tenistas> page = new PageImpl<>(tenistasList);
//
//        when(tenistasRepository.findByPeso(anyInt(), any(Pageable.class))).thenReturn(page);
//
//        Page<Tenistas> result = tenistasService.findByPeso(75, pageable);
//
//        assertNotNull(result);
//        assertEquals(1, result.getContent().size());
//
//        verify(tenistasRepository, times(1)).findByPeso(anyInt(), any(Pageable.class));
//    }
//
//    // Test para findByEdad
//    @Test
//    public void testFindByEdad() {
//        Pageable pageable = Pageable.unpaged();
//        List<Tenistas> tenistasList = new ArrayList<>();
//        tenistasList.add(new Tenistas());
//        Page<Tenistas> page = new PageImpl<>(tenistasList);
//
//        when(tenistasRepository.findByEdad(anyInt(), any(Pageable.class))).thenReturn(page);
//
//        Page<Tenistas> result = tenistasService.findByEdad(30, pageable);
//
//        assertNotNull(result);
//        assertEquals(1, result.getContent().size());
//
//        verify(tenistasRepository, times(1)).findByEdad(anyInt(), any(Pageable.class));
//    }
//
//    // Test para findByFechaNacimiento
//    @Test
//    public void testFindByFechaNacimiento() {
//        Pageable pageable = Pageable.unpaged();
//        List<Tenistas> tenistasList = new ArrayList<>();
//        tenistasList.add(new Tenistas());
//        Page<Tenistas> page = new PageImpl<>(tenistasList);
//
//        LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
//        when(tenistasRepository.findByFechaNacimiento(any(LocalDate.class), any(Pageable.class))).thenReturn(page);
//
//        Page<Tenistas> result = tenistasService.findByFechaNacimiento(fechaNacimiento, pageable);
//
//        assertNotNull(result);
//        assertEquals(1, result.getContent().size());
//
//        verify(tenistasRepository, times(1)).findByFechaNacimiento(any(LocalDate.class), any(Pageable.class));
//    }
//
//    // Test para findByDebutProfesional
//    @Test
//    public void testFindByDebutProfesional() {
//        Pageable pageable = Pageable.unpaged();
//        List<Tenistas> tenistasList = new ArrayList<>();
//        tenistasList.add(new Tenistas());
//        Page<Tenistas> page = new PageImpl<>(tenistasList);
//
//        LocalDate debutProfesional = LocalDate.of(2020, 1, 1);
//        when(tenistasRepository.findByDebutProfesional(any(LocalDate.class), any(Pageable.class))).thenReturn(page);
//
//        Page<Tenistas> result = tenistasService.findByDebutProfesional(debutProfesional, pageable);
//
//        assertNotNull(result);
//        assertEquals(1, result.getContent().size());
//
//        verify(tenistasRepository, times(1)).findByDebutProfesional(any(LocalDate.class), any(Pageable.class));
//    }
//
//    // Test para findByMano
//    @Test
//    public void testFindByMano() {
//        Pageable pageable = Pageable.unpaged();
//        List<Tenistas> tenistasList = new ArrayList<>();
//        tenistasList.add(new Tenistas());
//        Page<Tenistas> page = new PageImpl<>(tenistasList);
//
//        when(tenistasRepository.findByMano(any(Mano.class), any(Pageable.class))).thenReturn(page);
//
//        Page<Tenistas> result = tenistasService.findByMano(Mano.DIESTRO, pageable);
//
//        assertNotNull(result);
//        assertEquals(1, result.getContent().size());
//
//        verify(tenistasRepository, times(1)).findByMano(any(Mano.class), any(Pageable.class));
//    }
//
//    // Test para findByReves
//    @Test
//    public void testFindByReves() {
//        Pageable pageable = Pageable.unpaged();
//        List<Tenistas> tenistasList = new ArrayList<>();
//        tenistasList.add(new Tenistas());
//        Page<Tenistas> page = new PageImpl<>(tenistasList);
//
//        when(tenistasRepository.findByReves(any(Reves.class), any(Pageable.class))).thenReturn(page);
//
//        Page<Tenistas> result = tenistasService.findByReves(Reves.DOSMANOS, pageable);
//
//        assertNotNull(result);
//        assertEquals(1, result.getContent().size());
//
//        verify(tenistasRepository, times(1)).findByReves(any(Reves.class), any(Pageable.class));
//    }
//
//    // Test para findByEntrenador
//    @Test
//    public void testFindByEntrenador() {
//        Pageable pageable = Pageable.unpaged();
//        List<Tenistas> tenistasList = new ArrayList<>();
//        tenistasList.add(new Tenistas());
//        Page<Tenistas> page = new PageImpl<>(tenistasList);
//
//        when(tenistasRepository.findByEntrenador(anyString(), any(Pageable.class))).thenReturn(page);
//
//        Page<Tenistas> result = tenistasService.findByEntrenador("entrenador", pageable);
//
//        assertNotNull(result);
//        assertEquals(1, result.getContent().size());
//
//        verify(tenistasRepository, times(1)).findByEntrenador(anyString(), any(Pageable.class));
//    }
//
//    // Test para findByDineroGanado
//    @Test
//    public void testFindByDineroGanado() {
//        Pageable pageable = Pageable.unpaged();
//        List<Tenistas> tenistasList = new ArrayList<>();
//        tenistasList.add(new Tenistas());
//        Page<Tenistas> page = new PageImpl<>(tenistasList);
//
//        when(tenistasRepository.findByDineroGanado(anyLong(), any(Pageable.class))).thenReturn(page);
//
//        Page<Tenistas> result = tenistasService.findByDineroGanado(100L, pageable);
//
//        assertNotNull(result);
//        assertEquals(1, result.getContent().size());
//
//        verify(tenistasRepository, times(1)).findByDineroGanado(anyLong(), any(Pageable.class));
//    }
//
//    // Test para findByMejorRanking
//    @Test
//    public void testFindByMejorRanking() {
//        Pageable pageable = Pageable.unpaged();
//        List<Tenistas> tenistasList = new ArrayList<>();
//        tenistasList.add(new Tenistas());
//        Page<Tenistas> page = new PageImpl<>(tenistasList);
//
//        when(tenistasRepository.findByMejorRanking(anyLong(), any(Pageable.class))).thenReturn(page);
//
//        Page<Tenistas> result = tenistasService.findByMejorRanking(1L, pageable);
//
//        assertNotNull(result);
//        assertEquals(1, result.getContent().size());
//
//        verify(tenistasRepository, times(1)).findByMejorRanking(anyLong(), any(Pageable.class));
//    }
//
//    // Test para findByNumVictorias
//    @Test
//    public void testFindByNumVictorias() {
//        Pageable pageable = Pageable.unpaged();
//        List<Tenistas> tenistasList = new ArrayList<>();
//        tenistasList.add(new Tenistas());
//        Page<Tenistas> page = new PageImpl<>(tenistasList);
//
//        when(tenistasRepository.findByNumVictorias(anyInt(), any(Pageable.class))).thenReturn(page);
//
//        Page<Tenistas> result = tenistasService.findByNumVictorias(10, pageable);
//
//        assertNotNull(result);
//        assertEquals(1, result.getContent().size());
//
//        verify(tenistasRepository, times(1)).findByNumVictorias(anyInt(), any(Pageable.class));
//    }
//
//    // Test para findByNumDerrotas
//    @Test
//    public void testFindByNumDerrotas() {
//        Pageable pageable = Pageable.unpaged();
//        List<Tenistas> tenistasList = new ArrayList<>();
//        tenistasList.add(new Tenistas());
//        Page<Tenistas> page = new PageImpl<>(tenistasList);
//
//        when(tenistasRepository.findByNumDerrotas(anyInt(), any(Pageable.class))).thenReturn(page);
//
//        Page<Tenistas> result = tenistasService.findByNumDerrotas(5, pageable);
//
//        assertNotNull(result);
//        assertEquals(1, result.getContent().size());
//
//        verify(tenistasRepository, times(1)).findByNumDerrotas(anyInt(), any(Pageable.class));
//    }

//}