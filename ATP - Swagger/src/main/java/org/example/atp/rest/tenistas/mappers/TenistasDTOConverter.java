package org.example.atp.rest.tenistas.mappers;

import lombok.RequiredArgsConstructor;
import org.example.atp.rest.tenistas.dto.TenistasDTO;
import org.example.atp.rest.tenistas.models.Tenistas;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TenistasDTOConverter {
    private final ModelMapper modelMapper;

    public TenistasDTO convertToDto(Tenistas tenistas) {
        return modelMapper.map(tenistas, TenistasDTO.class);
    }

}
