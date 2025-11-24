package com.example.Sistema.mapper;

import com.example.Sistema.dto.AparelhoDTO;
import com.example.Sistema.entity.Aparelho;
import com.example.Sistema.entity.Cliente;

public class AparelhoMapper {

    public static AparelhoDTO toDTO(Aparelho aparelho) {
        if (aparelho == null) {
            return null;
        }

        AparelhoDTO dto = new AparelhoDTO();
        dto.setId(aparelho.getId());
        dto.setTipo(aparelho.getTipo());
        dto.setMarca(aparelho.getMarca());
        dto.setModelo(aparelho.getModelo());
        dto.setNumeroSerie(aparelho.getNumeroSerie());
        if (aparelho.getCliente() != null) {
            dto.setClienteId(aparelho.getCliente().getId());
        }

        return dto;
    }

    public static Aparelho toEntity(AparelhoDTO dto) {
        if (dto == null) {
            return null;
        }

        Aparelho aparelho = new Aparelho();
        aparelho.setId(dto.getId());
        aparelho.setTipo(dto.getTipo());
        aparelho.setMarca(dto.getMarca());
        aparelho.setModelo(dto.getModelo());
        aparelho.setNumeroSerie(dto.getNumeroSerie());
        if (dto.getClienteId() != null) {
            Cliente cliente = new Cliente();
            cliente.setId(dto.getClienteId());
            aparelho.setCliente(cliente);
        }

        return aparelho;
    }
}
