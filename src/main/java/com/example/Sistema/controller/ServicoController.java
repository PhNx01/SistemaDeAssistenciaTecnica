package com.example.Sistema.controller;

import com.example.Sistema.dto.ServicoDTO;
import com.example.Sistema.service.ServicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @PostMapping
    public ResponseEntity<ServicoDTO> create(@RequestBody ServicoDTO servicoDTO) {
        ServicoDTO createdServico = servicoService.create(servicoDTO);
        return ResponseEntity.ok(createdServico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoDTO> findById(@PathVariable Long id) {
        ServicoDTO servicoDTO = servicoService.findById(id);
        return servicoDTO != null ? ResponseEntity.ok(servicoDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<ServicoDTO>> findAll() {
        List<ServicoDTO> servicos = servicoService.findAll();
        return ResponseEntity.ok(servicos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoDTO> update(@PathVariable Long id, @RequestBody ServicoDTO servicoDTO) {
        ServicoDTO updatedServico = servicoService.update(id, servicoDTO);
        return updatedServico != null ? ResponseEntity.ok(updatedServico) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        servicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
