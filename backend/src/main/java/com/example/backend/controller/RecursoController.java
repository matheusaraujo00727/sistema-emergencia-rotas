package com.example.backend.controller;

import com.example.backend.dto.RecursoRequestDTO;
import com.example.backend.dto.RecursoResponseDTO;
import com.example.backend.entity.Recurso;
import com.example.backend.mapper.RecursoMapper;
import com.example.backend.service.RecursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recursos")
@RequiredArgsConstructor
public class RecursoController {

    private final RecursoService recursoService;

    @GetMapping
    public ResponseEntity<List<RecursoResponseDTO>> listarTodos() {

        List<RecursoResponseDTO> recursos =
                recursoService.listarTodos()
                        .stream()
                        .map(RecursoMapper::toResponseDTO)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(recursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecursoResponseDTO> buscarPorId(
            @PathVariable Long id
    ) {

        Recurso recurso =
                recursoService.buscarPorId(id);

        return ResponseEntity.ok(
                RecursoMapper.toResponseDTO(recurso)
        );
    }

    @PostMapping
    public ResponseEntity<RecursoResponseDTO> salvar(
            @RequestBody RecursoRequestDTO dto
    ) {

        Recurso recurso =
                RecursoMapper.toEntity(dto);

        Recurso salvo =
                recursoService.salvar(recurso);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        RecursoMapper.toResponseDTO(salvo)
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecursoResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody RecursoRequestDTO dto
    ) {

        Recurso recurso =
                RecursoMapper.toEntity(dto);

        Recurso atualizado =
                recursoService.atualizar(id, recurso);

        return ResponseEntity.ok(
                RecursoMapper.toResponseDTO(atualizado)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id
    ) {

        recursoService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
