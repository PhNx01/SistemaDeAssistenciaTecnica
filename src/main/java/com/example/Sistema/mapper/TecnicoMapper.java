package com.example.Sistema.mapper;

import com.example.Sistema.dto.TecnicoDTO;
import com.example.Sistema.entity.Tecnico;

public class TecnicoMapper {

    public static TecnicoDTO toDTO(Tecnico tecnico) {
        if (tecnico == null) {
            return null;
        }

        TecnicoDTO dto = new TecnicoDTO();
        dto.setId(tecnico.getId());
        dto.setNome(tecnico.getNome());
        dto.setEspecialidade(tecnico.getEspecialidade());

        return dto;
    }

    public static Tecnico toEntity(TecnicoDTO dto) {
        if (dto == null) {
            return null;
        }

        Tecnico tecnico = new Tecnico();
        tecnico.setId(dto.getId());
        tecnico.setNome(dto.getNome());
        tecnico.setEspecialidade(dto.getEspecialidade());

        return tecnico;
    }
}
