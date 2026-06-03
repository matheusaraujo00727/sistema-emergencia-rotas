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

    public RecursoService(RecursoRepository repository) {
        this.repository = repository;
    }

    public List<Recurso> listarTodos() {
        return repository.findAll();
    }

    public Recurso buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Recurso não encontrado com ID: " + id
                        ));
    }

    public Recurso salvar(Recurso recurso) {
        return repository.save(recurso);
    }

    public Recurso atualizar(Long id, Recurso recursoAtualizado) {

        Recurso recursoExistente = buscarPorId(id);

        recursoExistente.setNome(
                recursoAtualizado.getNome()
        );

        recursoExistente.setTipo(
                recursoAtualizado.getTipo()
        );

        recursoExistente.setDisponivel(
                recursoAtualizado.getDisponivel()
        );

        return repository.save(
                recursoExistente
        );
    }

    public void deletar(Long id) {

        Recurso recurso = buscarPorId(id);

        repository.delete(recurso);
    }
}