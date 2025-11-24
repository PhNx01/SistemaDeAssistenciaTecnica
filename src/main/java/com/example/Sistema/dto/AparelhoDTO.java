package com.example.Sistema.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AparelhoDTO {

    private Long id;
    private String tipo;
    private String marca;
    private String modelo;
    private String numeroSerie;
    private Long clienteId;
}
