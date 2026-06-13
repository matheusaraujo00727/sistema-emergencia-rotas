package com.example.backend.patterns.template;

import com.example.backend.entity.Recurso;
import com.example.backend.enums.StatusRecurso;
import com.example.backend.repository.RecursoRepository;

public abstract class RecursoTemplate {

    protected final RecursoRepository repository;

    protected RecursoTemplate(RecursoRepository repository) {
        this.repository = repository;
    }

    public final Recurso executar(Long id, Recurso recurso) {

        validarTipo(recurso);

        definirStatusPadrao(recurso);

        normalizarPlaca(recurso);

        validarPlacaObrigatoria(recurso);

        validarPlacaDuplicada(id, recurso);

        return persistir(id, recurso);
    }

    private void validarTipo(Recurso recurso) {

        if (recurso.getTipo() == null) {
            throw new RuntimeException(
                    "Tipo do recurso é obrigatório."
            );
        }
    }

    private void definirStatusPadrao(Recurso recurso) {

        if (recurso.getStatus() == null) {
            recurso.setStatus(StatusRecurso.DISPONIVEL);
        }
    }

    private void normalizarPlaca(Recurso recurso) {

        if (recurso.getPlaca() != null && recurso.getPlaca().isBlank()) {
            recurso.setPlaca(null);
        }
    }

    protected abstract void validarPlacaObrigatoria(Recurso recurso);

    protected abstract void validarPlacaDuplicada(Long id, Recurso recurso);

    protected abstract Recurso persistir(Long id, Recurso recurso);
}