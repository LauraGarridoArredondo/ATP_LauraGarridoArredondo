package org.example.atp.rest.torneos.mappers;

import lombok.RequiredArgsConstructor;
import org.example.atp.rest.torneos.dto.TorneosDTO;
import org.example.atp.rest.torneos.models.Torneos;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TorneosDTOConverter {
    private final ModelMapper modelMapper;
    public TorneosDTO convertToDto(Torneos torneos){
        return modelMapper.map(torneos, TorneosDTO.class);
    }
}
