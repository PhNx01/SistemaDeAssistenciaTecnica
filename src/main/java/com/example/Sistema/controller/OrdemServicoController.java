package com.example.Sistema.controller;

import com.example.Sistema.dto.OrdemServicoDTO;
import com.example.Sistema.service.OrdemServicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordens-servico")
public class OrdemServicoController {

    private final OrdemServicoService ordemServicoService;

    public OrdemServicoController(OrdemServicoService ordemServicoService) {
        this.ordemServicoService = ordemServicoService;
    }

    @PostMapping
    public ResponseEntity<OrdemServicoDTO> create(@RequestBody OrdemServicoDTO ordemServicoDTO) {
        try {
            OrdemServicoDTO createdOrdemServico = ordemServicoService.create(ordemServicoDTO);
            return ResponseEntity.ok(createdOrdemServico);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServicoDTO> findById(@PathVariable Long id) {
        OrdemServicoDTO ordemServicoDTO = ordemServicoService.findById(id);
        return ordemServicoDTO != null ? ResponseEntity.ok(ordemServicoDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<OrdemServicoDTO>> findAll() {
        List<OrdemServicoDTO> ordensServico = ordemServicoService.findAll();
        return ResponseEntity.ok(ordensServico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdemServicoDTO> update(@PathVariable Long id, @RequestBody OrdemServicoDTO ordemServicoDTO) {
        try {
            OrdemServicoDTO updatedOrdemServico = ordemServicoService.update(id, ordemServicoDTO);
            return updatedOrdemServico != null ? ResponseEntity.ok(updatedOrdemServico) : ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ordemServicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
