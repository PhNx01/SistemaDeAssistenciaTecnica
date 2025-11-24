package com.example.Sistema.service;

import com.example.Sistema.dto.ClienteDTO;
import com.example.Sistema.entity.Cliente;
import com.example.Sistema.mapper.ClienteMapper;
import com.example.Sistema.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public ClienteDTO create(ClienteDTO clienteDTO) {
        Cliente cliente = ClienteMapper.toEntity(clienteDTO);
        Cliente savedCliente = clienteRepository.save(cliente);
        return ClienteMapper.toDTO(savedCliente);
    }

    @Transactional(readOnly = true)
    public ClienteDTO findById(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        return clienteOptional.map(ClienteMapper::toDTO).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<ClienteDTO> findAll() {
        return clienteRepository.findAll().stream()
                .map(ClienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ClienteDTO update(Long id, ClienteDTO clienteDTO) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            cliente.setNome(clienteDTO.getNome());
            cliente.setTelefone(clienteDTO.getTelefone());
            cliente.setEmail(clienteDTO.getEmail());
            Cliente updatedCliente = clienteRepository.save(cliente);
            return ClienteMapper.toDTO(updatedCliente);
        }
        return null; // Ou lançar uma exceção
    }

    @Transactional
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
}
