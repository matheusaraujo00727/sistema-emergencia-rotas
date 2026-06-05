package com.example.backend.controller;

import com.example.backend.dto.EquipeRequestDTO;
import com.example.backend.dto.EquipeResponseDTO;
import com.example.backend.entity.Equipe;
import com.example.backend.mapper.EquipeMapper;
import com.example.backend.service.EquipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/equipes")
@RequiredArgsConstructor
public class EquipeController {

    private final EquipeService equipeService;

    @GetMapping
    public ResponseEntity<List<EquipeResponseDTO>> listarTodas() {

        List<EquipeResponseDTO> equipes =
                equipeService.listarTodas()
                        .stream()
                        .map(EquipeMapper::toResponseDTO)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(equipes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipeResponseDTO> buscarPorId(
            @PathVariable Long id
    ) {

        Equipe equipe = equipeService.buscarPorId(id);

        return ResponseEntity.ok(
                EquipeMapper.toResponseDTO(equipe)
        );
    }

    @PostMapping
    public ResponseEntity<EquipeResponseDTO> salvar(
            @RequestBody EquipeRequestDTO dto
    ) {

        Equipe equipe = EquipeMapper.toEntity(dto);

        Equipe salva = equipeService.salvar(equipe);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        EquipeMapper.toResponseDTO(salva)
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipeResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody EquipeRequestDTO dto
    ) {

        Equipe equipe = EquipeMapper.toEntity(dto);

        Equipe atualizada =
                equipeService.atualizar(id, equipe);

        return ResponseEntity.ok(
                EquipeMapper.toResponseDTO(atualizada)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id
    ) {

        equipeService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
