package org.example.atp.torneos;


import org.example.atp.rest.torneos.dto.TorneosDTO;
import org.example.atp.rest.torneos.mappers.TorneosDTOConverter;
import org.example.atp.rest.torneos.models.Torneos;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TorneosMapeadorTest {
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TorneosDTOConverter torneosDTOConverter;
    @Test
    public void testConvertToDto() {
        // Arrange
        Torneos torneos = new Torneos();
        torneos.setId(1L);
        torneos.setModalidad("ATP");
        torneos.setUbicacion("Suiza");

        TorneosDTO expectedDto = new TorneosDTO();
        expectedDto.setId(1L);
        expectedDto.setModalidad("ATP");
        expectedDto.setUbicacion("Suiza");

        // Mockear el mapeo entre Tenistas y TenistasDTO
        when(modelMapper.map(torneos, TorneosDTO.class)).thenReturn(expectedDto);

        // Act
        TorneosDTO actualDto = torneosDTOConverter.convertToDto(torneos);

        // Assert
        assertEquals(expectedDto.getId(), actualDto.getId());
        assertEquals(expectedDto.getModalidad(), actualDto.getModalidad());
        assertEquals(expectedDto.getUbicacion(), actualDto.getUbicacion());
    }
}
