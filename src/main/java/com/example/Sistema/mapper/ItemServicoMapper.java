package com.example.Sistema.mapper;

import com.example.Sistema.dto.ItemServicoDTO;
import com.example.Sistema.entity.ItemServico;
import com.example.Sistema.entity.Servico;

public class ItemServicoMapper {

    public static ItemServicoDTO toDTO(ItemServico itemServico) {
        if (itemServico == null) {
            return null;
        }

        ItemServicoDTO dto = new ItemServicoDTO();
        dto.setId(itemServico.getId());
        dto.setQuantidade(itemServico.getQuantidade());
        dto.setValorUnitario(itemServico.getValorUnitario());
        dto.setValorTotal(itemServico.getValorTotal());
        if (itemServico.getServico() != null) {
            dto.setServicoId(itemServico.getServico().getId());
        }

        return dto;
    }

    public static ItemServico toEntity(ItemServicoDTO dto) {
        if (dto == null) {
            return null;
        }

        ItemServico itemServico = new ItemServico();
        itemServico.setId(dto.getId());
        itemServico.setQuantidade(dto.getQuantidade());
        itemServico.setValorUnitario(dto.getValorUnitario());
        itemServico.setValorTotal(dto.getValorTotal());
        if (dto.getServicoId() != null) {
            Servico servico = new Servico();
            servico.setId(dto.getServicoId());
            itemServico.setServico(servico);
        }

        return itemServico;
    }
}
