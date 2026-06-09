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

        if (recurso.getTipo() == null) {
            throw new RuntimeException(
                    "Tipo do recurso é obrigatório."
            );
        }

        if (recurso.getDisponivel() == null) {
            recurso.setDisponivel(true);
        }

        if (repository.findByPlaca(recurso.getPlaca()).isPresent()) {
            throw new RuntimeException("Já existe um recurso com essa placa.");
        }

        return repository.save(recurso);
    }

    public Recurso atualizar(Long id, Recurso recursoAtualizado) {

        Recurso recursoExistente = buscarPorId(id);

        if (recursoAtualizado.getTipo() == null) {
            throw new RuntimeException(
                    "Tipo do recurso é obrigatório."
            );
        }

        recursoExistente.setNome(
                recursoAtualizado.getNome()
        );

        recursoExistente.setTipo(
                recursoAtualizado.getTipo()
        );

        recursoExistente.setPlaca(
                recursoAtualizado.getPlaca()
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

        if (!recurso.getDisponivel()) {
            throw new RuntimeException(
                    "Não é possível excluir um recurso indisponível."
            );
        }

        repository.delete(recurso);
    }
}