package com.example.backend.mapper;

import com.example.backend.dto.UsuarioRequestDTO;
import com.example.backend.dto.UsuarioResponseDTO;
import com.example.backend.entity.Usuario;

public class UsuarioMapper {

    public static UsuarioResponseDTO toResponseDTO(
            Usuario usuario
    ) {

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getPerfil()
        );
    }
}