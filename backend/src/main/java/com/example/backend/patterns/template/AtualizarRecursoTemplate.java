package com.example.backend.patterns.template;

import com.example.backend.entity.Recurso;
import com.example.backend.repository.RecursoRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizarRecursoTemplate extends RecursoTemplate {

    private final RecursoRepository repository;

    public AtualizarRecursoTemplate(RecursoRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    protected void validarPlacaObrigatoria(Recurso recurso) {

        if (recurso.getTipo() == null) {
            throw new RuntimeException("Tipo do recurso é obrigatório.");
        }
    }

    @Override
    protected void validarPlacaDuplicada(Long id, Recurso recurso) {

        if (recurso.getPlaca() != null && !recurso.getPlaca().isBlank()) {

            repository.findByPlaca(recurso.getPlaca())
                    .ifPresent(r -> {

                        if (!r.getId().equals(id)) {
                            throw new RuntimeException(
                                    "Já existe um recurso com essa placa."
                            );
                        }
                    });
        }
    }

    @Override
    protected Recurso persistir(Long id, Recurso recurso) {

        Recurso existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Recurso não encontrado com ID: " + id
                ));

        existente.setNome(recurso.getNome());
        existente.setTipo(recurso.getTipo());
        existente.setStatus(recurso.getStatus());
        existente.setPlaca(recurso.getPlaca());

        return repository.save(existente);
    }
}