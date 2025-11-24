package com.example.Sistema.controller;

import com.example.Sistema.dto.TecnicoDTO;
import com.example.Sistema.service.TecnicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tecnicos")
public class TecnicoController {

    private final TecnicoService tecnicoService;

    public TecnicoController(TecnicoService tecnicoService) {
        this.tecnicoService = tecnicoService;
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@RequestBody TecnicoDTO tecnicoDTO) {
        TecnicoDTO createdTecnico = tecnicoService.create(tecnicoDTO);
        return ResponseEntity.ok(createdTecnico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Long id) {
        TecnicoDTO tecnicoDTO = tecnicoService.findById(id);
        return tecnicoDTO != null ? ResponseEntity.ok(tecnicoDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll() {
        List<TecnicoDTO> tecnicos = tecnicoService.findAll();
        return ResponseEntity.ok(tecnicos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Long id, @RequestBody TecnicoDTO tecnicoDTO) {
        TecnicoDTO updatedTecnico = tecnicoService.update(id, tecnicoDTO);
        return updatedTecnico != null ? ResponseEntity.ok(updatedTecnico) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tecnicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
