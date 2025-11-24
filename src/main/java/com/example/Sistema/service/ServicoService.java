package com.example.Sistema.service;

import com.example.Sistema.dto.ServicoDTO;
import com.example.Sistema.entity.Servico;
import com.example.Sistema.mapper.ServicoMapper;
import com.example.Sistema.repository.ServicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    @Transactional
    public ServicoDTO create(ServicoDTO servicoDTO) {
        Servico servico = ServicoMapper.toEntity(servicoDTO);
        Servico savedServico = servicoRepository.save(servico);
        return ServicoMapper.toDTO(savedServico);
    }

    @Transactional(readOnly = true)
    public ServicoDTO findById(Long id) {
        Optional<Servico> servicoOptional = servicoRepository.findById(id);
        return servicoOptional.map(ServicoMapper::toDTO).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<ServicoDTO> findAll() {
        return servicoRepository.findAll().stream()
                .map(ServicoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ServicoDTO update(Long id, ServicoDTO servicoDTO) {
        Optional<Servico> servicoOptional = servicoRepository.findById(id);
        if (servicoOptional.isPresent()) {
            Servico servico = servicoOptional.get();
            servico.setNome(servicoDTO.getNome());
            servico.setDescricao(servicoDTO.getDescricao());
            servico.setValor(servicoDTO.getValor());
            Servico updatedServico = servicoRepository.save(servico);
            return ServicoMapper.toDTO(updatedServico);
        }
        return null;
    }

    @Transactional
    public void delete(Long id) {
        servicoRepository.deleteById(id);
    }
}
