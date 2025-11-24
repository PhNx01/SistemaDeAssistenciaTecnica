package com.example.Sistema.controller;

import com.example.Sistema.dto.AparelhoDTO;
import com.example.Sistema.service.AparelhoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aparelhos")
public class AparelhoController {

    private final AparelhoService aparelhoService;

    public AparelhoController(AparelhoService aparelhoService) {
        this.aparelhoService = aparelhoService;
    }

    @PostMapping
    public ResponseEntity<AparelhoDTO> create(@RequestBody AparelhoDTO aparelhoDTO) {
        try {
            AparelhoDTO createdAparelho = aparelhoService.create(aparelhoDTO);
            return ResponseEntity.ok(createdAparelho);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AparelhoDTO> findById(@PathVariable Long id) {
        AparelhoDTO aparelhoDTO = aparelhoService.findById(id);
        return aparelhoDTO != null ? ResponseEntity.ok(aparelhoDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<AparelhoDTO>> findAll() {
        List<AparelhoDTO> aparelhos = aparelhoService.findAll();
        return ResponseEntity.ok(aparelhos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AparelhoDTO> update(@PathVariable Long id, @RequestBody AparelhoDTO aparelhoDTO) {
        try {
            AparelhoDTO updatedAparelho = aparelhoService.update(id, aparelhoDTO);
            return updatedAparelho != null ? ResponseEntity.ok(updatedAparelho) : ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        aparelhoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
