package com.example.Sistema.mapper;

import com.example.Sistema.dto.ServicoDTO;
import com.example.Sistema.entity.Servico;

public class ServicoMapper {

    public static ServicoDTO toDTO(Servico servico) {
        if (servico == null) {
            return null;
        }

        ServicoDTO dto = new ServicoDTO();
        dto.setId(servico.getId());
        dto.setNome(servico.getNome());
        dto.setDescricao(servico.getDescricao());
        dto.setValor(servico.getValor());

        return dto;
    }

    public static Servico toEntity(ServicoDTO dto) {
        if (dto == null) {
            return null;
        }

        Servico servico = new Servico();
        servico.setId(dto.getId());
        servico.setNome(dto.getNome());
        servico.setDescricao(dto.getDescricao());
        servico.setValor(dto.getValor());

        return servico;
    }
}
