package com.example.backend.service;

import com.example.backend.entity.Recurso;
import com.example.backend.repository.RecursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecursoService {

    private final RecursoRepository repository;
    private final CriarRecursoTemplate criarRecursoTemplate;
    private final AtualizarRecursoTemplate atualizarRecursoTemplate;

    public List<Recurso> listarTodos() {
        return repository.findAll();
    }

    public Recurso buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Recurso não encontrado com ID: " + id
                ));
    }

    public Recurso salvar(Recurso recurso) {
        return criarRecursoTemplate.executar(null, recurso);
    }

    public Recurso atualizar(Long id, Recurso recurso) {
        return atualizarRecursoTemplate.executar(id, recurso);
    }

    public void deletar(Long id) {

        Recurso recurso = buscarPorId(id);

        if (recurso.getStatus().name().equals("INDISPONIVEL")) {
            throw new RuntimeException(
                    "Não é possível excluir um recurso indisponível."
            );
        }

        repository.delete(recurso);
    }
}