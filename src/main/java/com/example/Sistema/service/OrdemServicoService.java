package com.example.Sistema.service;

import com.example.Sistema.dto.OrdemServicoDTO;
import com.example.Sistema.entity.Aparelho;
import com.example.Sistema.entity.OrdemServico;
import com.example.Sistema.entity.StatusOrdemServico;
import com.example.Sistema.entity.Tecnico;
import com.example.Sistema.mapper.OrdemServicoMapper;
import com.example.Sistema.repository.AparelhoRepository;
import com.example.Sistema.repository.OrdemServicoRepository;
import com.example.Sistema.repository.TecnicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdemServicoService {

    private final OrdemServicoRepository ordemServicoRepository;
    private final AparelhoRepository aparelhoRepository;
    private final TecnicoRepository tecnicoRepository;

    public OrdemServicoService(OrdemServicoRepository ordemServicoRepository, AparelhoRepository aparelhoRepository, TecnicoRepository tecnicoRepository) {
        this.ordemServicoRepository = ordemServicoRepository;
        this.aparelhoRepository = aparelhoRepository;
        this.tecnicoRepository = tecnicoRepository;
    }

    @Transactional
    public OrdemServicoDTO create(OrdemServicoDTO ordemServicoDTO) {
        OrdemServico ordemServico = OrdemServicoMapper.toEntity(ordemServicoDTO);

        Aparelho aparelho = aparelhoRepository.findById(ordemServicoDTO.getAparelhoId())
                .orElseThrow(() -> new RuntimeException("Aparelho não encontrado com o ID: " + ordemServicoDTO.getAparelhoId()));
        ordemServico.setAparelho(aparelho);

        Tecnico tecnico = tecnicoRepository.findById(ordemServicoDTO.getTecnicoId())
                .orElseThrow(() -> new RuntimeException("Técnico não encontrado com o ID: " + ordemServicoDTO.getTecnicoId()));
        ordemServico.setTecnico(tecnico);

        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(LocalDateTime.now());

        OrdemServico savedOrdemServico = ordemServicoRepository.save(ordemServico);
        return OrdemServicoMapper.toDTO(savedOrdemServico);
    }

    @Transactional(readOnly = true)
    public OrdemServicoDTO findById(Long id) {
        return ordemServicoRepository.findById(id)
                .map(OrdemServicoMapper::toDTO)
                .orElse(null);
    }

    @Transactional(readOnly = true)
    public List<OrdemServicoDTO> findAll() {
        return ordemServicoRepository.findAll().stream()
                .map(OrdemServicoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrdemServicoDTO update(Long id, OrdemServicoDTO ordemServicoDTO) {
        return ordemServicoRepository.findById(id).map(ordemServico -> {
            // Atualiza as associações
            Aparelho aparelho = aparelhoRepository.findById(ordemServicoDTO.getAparelhoId())
                    .orElseThrow(() -> new RuntimeException("Aparelho não encontrado com o ID: " + ordemServicoDTO.getAparelhoId()));
            ordemServico.setAparelho(aparelho);

            Tecnico tecnico = tecnicoRepository.findById(ordemServicoDTO.getTecnicoId())
                    .orElseThrow(() -> new RuntimeException("Técnico não encontrado com o ID: " + ordemServicoDTO.getTecnicoId()));
            ordemServico.setTecnico(tecnico);

            // Atualiza os campos
            ordemServico.setDescricaoProblema(ordemServicoDTO.getDescricaoProblema());
            ordemServico.setStatus(ordemServicoDTO.getStatus());

            // Lógica para data de conclusão
            if (ordemServicoDTO.getStatus() == StatusOrdemServico.CONCLUIDA || ordemServicoDTO.getStatus() == StatusOrdemServico.CANCELADA) {
                ordemServico.setDataConclusao(LocalDateTime.now());
            } else {
                ordemServico.setDataConclusao(null);
            }

            OrdemServico updatedOrdemServico = ordemServicoRepository.save(ordemServico);
            return OrdemServicoMapper.toDTO(updatedOrdemServico);
        }).orElse(null);
    }

    @Transactional
    public void delete(Long id) {
        ordemServicoRepository.deleteById(id);
    }
}
