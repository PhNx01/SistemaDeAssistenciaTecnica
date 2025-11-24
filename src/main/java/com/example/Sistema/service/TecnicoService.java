package com.example.Sistema.service;

import com.example.Sistema.dto.TecnicoDTO;
import com.example.Sistema.entity.Tecnico;
import com.example.Sistema.mapper.TecnicoMapper;
import com.example.Sistema.repository.TecnicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;

    public TecnicoService(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

    @Transactional
    public TecnicoDTO create(TecnicoDTO tecnicoDTO) {
        Tecnico tecnico = TecnicoMapper.toEntity(tecnicoDTO);
        Tecnico savedTecnico = tecnicoRepository.save(tecnico);
        return TecnicoMapper.toDTO(savedTecnico);
    }

    @Transactional(readOnly = true)
    public TecnicoDTO findById(Long id) {
        Optional<Tecnico> tecnicoOptional = tecnicoRepository.findById(id);
        return tecnicoOptional.map(TecnicoMapper::toDTO).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<TecnicoDTO> findAll() {
        return tecnicoRepository.findAll().stream()
                .map(TecnicoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public TecnicoDTO update(Long id, TecnicoDTO tecnicoDTO) {
        Optional<Tecnico> tecnicoOptional = tecnicoRepository.findById(id);
        if (tecnicoOptional.isPresent()) {
            Tecnico tecnico = tecnicoOptional.get();
            tecnico.setNome(tecnicoDTO.getNome());
            tecnico.setEspecialidade(tecnicoDTO.getEspecialidade());
            Tecnico updatedTecnico = tecnicoRepository.save(tecnico);
            return TecnicoMapper.toDTO(updatedTecnico);
        }
        return null;
    }

    @Transactional
    public void delete(Long id) {
        tecnicoRepository.deleteById(id);
    }
}
