package com.example.backend.controller;

import com.example.backend.dto.UsuarioRequestDTO;
import com.example.backend.dto.UsuarioResponseDTO;
import com.example.backend.entity.Usuario;
import com.example.backend.mapper.UsuarioMapper;
import com.example.backend.service.UsuarioFactory;
import com.example.backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {

        List<UsuarioResponseDTO> usuarios =
                usuarioService.listarTodos()
                        .stream()
                        .map(UsuarioMapper::toResponseDTO)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(
            @PathVariable Long id
    ) {

        Usuario usuario =
                usuarioService.buscarPorId(id);

        return ResponseEntity.ok(
                UsuarioMapper.toResponseDTO(usuario)
        );
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> salvar(
            @RequestBody UsuarioRequestDTO dto
    ) {

        Usuario salvo =
                usuarioService.salvar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        UsuarioMapper.toResponseDTO(salvo)
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody UsuarioRequestDTO dto
    ) {


        Usuario atualizado =
                usuarioService.atualizar(id, dto);

        return ResponseEntity.ok(
                UsuarioMapper.toResponseDTO(atualizado)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id
    ) {

        usuarioService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
