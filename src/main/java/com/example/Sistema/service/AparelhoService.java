package com.example.Sistema.service;

import com.example.Sistema.dto.AparelhoDTO;
import com.example.Sistema.entity.Aparelho;
import com.example.Sistema.entity.Cliente;
import com.example.Sistema.mapper.AparelhoMapper;
import com.example.Sistema.repository.AparelhoRepository;
import com.example.Sistema.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AparelhoService {

    private final AparelhoRepository aparelhoRepository;
    private final ClienteRepository clienteRepository;

    public AparelhoService(AparelhoRepository aparelhoRepository, ClienteRepository clienteRepository) {
        this.aparelhoRepository = aparelhoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public AparelhoDTO create(AparelhoDTO aparelhoDTO) {
        Aparelho aparelho = AparelhoMapper.toEntity(aparelhoDTO);

        Cliente cliente = clienteRepository.findById(aparelhoDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + aparelhoDTO.getClienteId()));
        aparelho.setCliente(cliente);

        Aparelho savedAparelho = aparelhoRepository.save(aparelho);
        return AparelhoMapper.toDTO(savedAparelho);
    }

    @Transactional(readOnly = true)
    public AparelhoDTO findById(Long id) {
        return aparelhoRepository.findById(id)
                .map(AparelhoMapper::toDTO)
                .orElse(null);
    }

    @Transactional(readOnly = true)
    public List<AparelhoDTO> findAll() {
        return aparelhoRepository.findAll().stream()
                .map(AparelhoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public AparelhoDTO update(Long id, AparelhoDTO aparelhoDTO) {
        return aparelhoRepository.findById(id).map(aparelho -> {
            Cliente cliente = clienteRepository.findById(aparelhoDTO.getClienteId())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + aparelhoDTO.getClienteId()));
            aparelho.setCliente(cliente);
            aparelho.setTipo(aparelhoDTO.getTipo());
            aparelho.setMarca(aparelhoDTO.getMarca());
            aparelho.setModelo(aparelhoDTO.getModelo());
            aparelho.setNumeroSerie(aparelhoDTO.getNumeroSerie());

            Aparelho updatedAparelho = aparelhoRepository.save(aparelho);
            return AparelhoMapper.toDTO(updatedAparelho);
        }).orElse(null);
    }

    @Transactional
    public void delete(Long id) {
        if (!aparelhoRepository.existsById(id)) {
            // Lançar exceção ou retornar um status que indique "não encontrado"
            return;
        }
        aparelhoRepository.deleteById(id);
    }
}
