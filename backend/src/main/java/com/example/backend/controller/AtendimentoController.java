package com.example.backend.controller;

import com.example.backend.dto.AtendimentoRequestDTO;
import com.example.backend.dto.AtendimentoResponseDTO;
import com.example.backend.entity.Atendimento;
import com.example.backend.mapper.AtendimentoMapper;
import com.example.backend.service.AtendimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/atendimentos")
@RequiredArgsConstructor
public class AtendimentoController {

    private final AtendimentoService atendimentoService;

    @GetMapping
    public ResponseEntity<List<AtendimentoResponseDTO>> listarTodos() {

        List<AtendimentoResponseDTO> atendimentos =
                atendimentoService.listarTodos()
                        .stream()
                        .map(AtendimentoMapper::toResponseDTO)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(atendimentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtendimentoResponseDTO> buscarPorId(
            @PathVariable Long id
    ) {

        Atendimento atendimento =
                atendimentoService.buscarPorId(id);

        return ResponseEntity.ok(
                AtendimentoMapper.toResponseDTO(atendimento)
        );
    }

    @PostMapping
    public ResponseEntity<AtendimentoResponseDTO> salvar(@RequestBody AtendimentoRequestDTO dto) {

        Atendimento atendimento = new Atendimento();

        atendimento.setDataInicio(LocalDateTime.now());

        atendimento.setDataFim(null);

        atendimento.setStatus(dto.status());

        atendimento.setObservacoes(dto.observacoes());

        Atendimento salvo = atendimentoService.salvar(
                        dto.ocorrenciaId(),
                        dto.equipeId(),
                        atendimento
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(AtendimentoMapper.toResponseDTO(salvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtendimentoResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody AtendimentoRequestDTO dto
    ) {
        Atendimento atendimento = new Atendimento();

        atendimento.setDataInicio(dto.dataInicio());
        atendimento.setDataFim(dto.dataFim());
        atendimento.setStatus(dto.status());
        atendimento.setObservacoes(dto.observacoes());

        Atendimento atualizado = atendimentoService.atualizar(id, atendimento);

        return ResponseEntity.ok(
                AtendimentoMapper.toResponseDTO(atualizado)
        );
    }


}
