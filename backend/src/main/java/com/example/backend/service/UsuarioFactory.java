package com.example.backend.service;

import com.example.backend.dto.UsuarioRequestDTO;
import com.example.backend.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioFactory {

    public Usuario criarUsuario(
            UsuarioRequestDTO dto
    ) {

        Usuario usuario = new Usuario();

        usuario.setNome(dto.nome());
        usuario.setCpf(dto.cpf());

        return usuario;
    }
}