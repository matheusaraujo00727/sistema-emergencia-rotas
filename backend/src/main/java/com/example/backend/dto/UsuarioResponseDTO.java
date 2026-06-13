package com.example.backend.dto;

import com.example.backend.enums.PerfilUsuario;

public record UsuarioResponseDTO(

        Long id,
        String nome,
        String cpf,
        PerfilUsuario perfil

) {
}