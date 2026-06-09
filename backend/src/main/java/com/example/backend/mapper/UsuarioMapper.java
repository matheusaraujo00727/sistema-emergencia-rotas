package com.example.backend.mapper;

import com.example.backend.dto.UsuarioRequestDTO;
import com.example.backend.dto.UsuarioResponseDTO;
import com.example.backend.entity.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(
            UsuarioRequestDTO dto
    ) {

        Usuario usuario = new Usuario();

        usuario.setNome(dto.nome());
        usuario.setCpf(dto.cpf());

        return usuario;
    }

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

