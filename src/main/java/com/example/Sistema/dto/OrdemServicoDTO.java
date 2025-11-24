package com.example.Sistema.dto;

import com.example.Sistema.entity.StatusOrdemServico;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrdemServicoDTO {

    private Long id;
    private Long aparelhoId;
    private Long tecnicoId;
    private String descricaoProblema;
    private StatusOrdemServico status;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataConclusao;
    private List<ItemServicoDTO> itensServico;
}
