package com.example.Sistema.mapper;

import com.example.Sistema.dto.OrdemServicoDTO;
import com.example.Sistema.entity.Aparelho;
import com.example.Sistema.entity.OrdemServico;
import com.example.Sistema.entity.Tecnico;

import java.util.stream.Collectors;

public class OrdemServicoMapper {

    public static OrdemServicoDTO toDTO(OrdemServico ordemServico) {
        if (ordemServico == null) {
            return null;
        }

        OrdemServicoDTO dto = new OrdemServicoDTO();
        dto.setId(ordemServico.getId());
        dto.setDescricaoProblema(ordemServico.getDescricaoProblema());
        dto.setStatus(ordemServico.getStatus());
        dto.setDataAbertura(ordemServico.getDataAbertura());
        dto.setDataConclusao(ordemServico.getDataConclusao());

        if (ordemServico.getAparelho() != null) {
            dto.setAparelhoId(ordemServico.getAparelho().getId());
        }

        if (ordemServico.getTecnico() != null) {
            dto.setTecnicoId(ordemServico.getTecnico().getId());
        }

        if (ordemServico.getItensServico() != null) {
            dto.setItensServico(ordemServico.getItensServico().stream()
                    .map(ItemServicoMapper::toDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public static OrdemServico toEntity(OrdemServicoDTO dto) {
        if (dto == null) {
            return null;
        }

        OrdemServico ordemServico = new OrdemServico();
        ordemServico.setId(dto.getId());
        ordemServico.setDescricaoProblema(dto.getDescricaoProblema());
        ordemServico.setStatus(dto.getStatus());
        ordemServico.setDataAbertura(dto.getDataAbertura());
        ordemServico.setDataConclusao(dto.getDataConclusao());

        if (dto.getAparelhoId() != null) {
            Aparelho aparelho = new Aparelho();
            aparelho.setId(dto.getAparelhoId());
            ordemServico.setAparelho(aparelho);
        }

        if (dto.getTecnicoId() != null) {
            Tecnico tecnico = new Tecnico();
            tecnico.setId(dto.getTecnicoId());
            ordemServico.setTecnico(tecnico);
        }

        if (dto.getItensServico() != null) {
            ordemServico.setItensServico(dto.getItensServico().stream()
                    .map(ItemServicoMapper::toEntity)
                    .collect(Collectors.toList()));
        }

        return ordemServico;
    }
}
