package org.example.atp.tenistas;

import org.example.atp.rest.tenistas.dto.TenistasDTO;
import org.example.atp.rest.tenistas.mappers.TenistasDTOConverter;
import org.example.atp.rest.tenistas.models.Tenistas;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TenistasMapeadorTest {

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TenistasDTOConverter tenistasDTOConverter;

    @Test
    public void testConvertToDto() {
        // Arrange
        Tenistas tenistas = new Tenistas();
        tenistas.setId(1L);
        tenistas.setNombreCompleto("Roger Federer");
        tenistas.setPais("Suiza");

        TenistasDTO expectedDto = new TenistasDTO();
        expectedDto.setId(1L);
        expectedDto.setNombreCompleto("Roger Federer");
        expectedDto.setPais("Suiza");

        // Mockear el mapeo entre Tenistas y TenistasDTO
        when(modelMapper.map(tenistas, TenistasDTO.class)).thenReturn(expectedDto);

        // Act
        TenistasDTO actualDto = tenistasDTOConverter.convertToDto(tenistas);

        // Assert
        assertEquals(expectedDto.getId(), actualDto.getId());
        assertEquals(expectedDto.getNombreCompleto(), actualDto.getNombreCompleto());
        assertEquals(expectedDto.getPais(), actualDto.getPais());
    }
}
