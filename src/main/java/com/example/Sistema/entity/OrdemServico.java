package com.example.Sistema.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ordens_servico")
@Getter
@Setter
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aparelho_id")
    private Aparelho aparelho;

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;

    private String descricaoProblema;

    @Enumerated(EnumType.STRING)
    private StatusOrdemServico status;

    private LocalDateTime dataAbertura;

    private LocalDateTime dataConclusao;

    @OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL)
    private List<ItemServico> itensServico;
}
