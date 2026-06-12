package com.example.backend.service;

import com.example.backend.entity.Recurso;
import com.example.backend.enums.TipoRecurso;
import com.example.backend.repository.RecursoRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarRecursoTemplate extends RecursoTemplate {

    private final RecursoRepository repository;

    public CriarRecursoTemplate(RecursoRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    protected void validarPlacaObrigatoria(Recurso recurso) {

        if ((recurso.getTipo() == TipoRecurso.AMBULANCIA
                || recurso.getTipo() == TipoRecurso.VIATURA)
                && (recurso.getPlaca() == null || recurso.getPlaca().isBlank())) {

            throw new RuntimeException(
                    "Este tipo de recurso exige placa."
            );
        }
    }

    @Override
    protected void validarPlacaDuplicada(Long id, Recurso recurso) {

        if (recurso.getPlaca() != null && !recurso.getPlaca().isBlank()) {

            repository.findByPlaca(recurso.getPlaca())
                    .ifPresent(r -> {
                        throw new RuntimeException(
                                "Já existe um recurso com essa placa."
                        );
                    });
        }
    }

    @Override
    protected Recurso persistir(Long id, Recurso recurso) {
        return repository.save(recurso);
    }
}