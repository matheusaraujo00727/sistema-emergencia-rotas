package com.example.backend.controller;

import com.example.backend.dto.OcorrenciaRequestDTO;
import com.example.backend.dto.OcorrenciaResponseDTO;
import com.example.backend.entity.Ocorrencia;
import com.example.backend.mapper.OcorrenciaMapper;
import com.example.backend.service.OcorrenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ocorrencias")
@RequiredArgsConstructor
public class OcorrenciaController {

    private final OcorrenciaService ocorrenciaService;

    @GetMapping
    public ResponseEntity<List<OcorrenciaResponseDTO>> listarTodas() {

        List<OcorrenciaResponseDTO> ocorrencias =
                ocorrenciaService.listarTodas()
                        .stream()
                        .map(OcorrenciaMapper::toResponseDTO)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(ocorrencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OcorrenciaResponseDTO> buscarPorId(
            @PathVariable Long id
    ) {

        Ocorrencia ocorrencia =
                ocorrenciaService.buscarPorId(id);

        return ResponseEntity.ok(
                OcorrenciaMapper.toResponseDTO(ocorrencia)
        );
    }

    @PostMapping
    public ResponseEntity<OcorrenciaResponseDTO> salvar(
            @RequestBody OcorrenciaRequestDTO dto
    ) {

        Ocorrencia ocorrencia =
                OcorrenciaMapper.toEntity(dto);

        Ocorrencia salva =
                ocorrenciaService.salvar(ocorrencia);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        OcorrenciaMapper.toResponseDTO(salva)
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<OcorrenciaResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody OcorrenciaRequestDTO dto
    ) {

        Ocorrencia ocorrencia =
                OcorrenciaMapper.toEntity(dto);

        Ocorrencia atualizada =
                ocorrenciaService.atualizar(
                        id,
                        ocorrencia
                );

        return ResponseEntity.ok(
                OcorrenciaMapper.toResponseDTO(
                        atualizada
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id
    ) {

        ocorrenciaService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
