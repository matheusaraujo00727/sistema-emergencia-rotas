package com.example.backend.service;

import com.example.backend.entity.Usuario;
import com.example.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

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

        usuarioExistente.setEmail(
                usuarioAtualizado.getEmail()
        );

        usuarioExistente.setSenha(
                usuarioAtualizado.getSenha()
        );

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
