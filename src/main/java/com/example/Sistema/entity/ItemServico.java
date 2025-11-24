package com.example.Sistema.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "itens_servico")
@Getter
@Setter
public class ItemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ordem_servico_id")
    private OrdemServico ordemServico;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;

    private Integer quantidade;

    private BigDecimal valorUnitario;

    private BigDecimal valorTotal;
}
