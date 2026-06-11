package com.example.backend.service;

import com.example.backend.entity.Usuario;
import com.example.backend.enums.PerfilUsuario;
import com.example.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Usuário não encontrado com ID: " + id
                        ));
    }

    public Usuario salvar(Usuario usuario) {
        if (
                usuario.getNome().equalsIgnoreCase("adm")
                        && usuario.getCpf().equals("000.000.000-00")
        ) {
            usuario.setPerfil(PerfilUsuario.ADMIN);
        } else {
            usuario.setPerfil(PerfilUsuario.USUARIO);
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(
            Long id,
            Usuario usuarioAtualizado
    ) {

        Usuario usuarioExistente =
                buscarPorId(id);

        usuarioExistente.setNome(
                usuarioAtualizado.getNome()
        );

        usuarioExistente.setCpf(
                usuarioAtualizado.getCpf()
        );

        if (
                usuarioAtualizado.getNome().equalsIgnoreCase("adm")
                        && usuarioAtualizado.getCpf().equals("000.000.000-00")
        ) {
            usuarioExistente.setPerfil(
                    PerfilUsuario.ADMIN
            );
        } else {
            usuarioExistente.setPerfil(
                    PerfilUsuario.USUARIO
            );
        }

        return usuarioRepository.save(
                usuarioExistente
        );
    }

    public void deletar(Long id) {

        Usuario usuario =
                buscarPorId(id);

        usuarioRepository.delete(
                usuario
        );
    }
}
