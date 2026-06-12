package com.example.backend.service;

import com.example.backend.entity.Equipe;
import com.example.backend.repository.EquipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipeService {

    private final EquipeRepository equipeRepository;

    public List<Equipe> listarTodas() {
        return equipeRepository.findAll();
    }

    public Equipe buscarPorId(Long id) {
        return equipeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Equipe não encontrada com ID: " + id
                        ));
    }

    public Equipe salvar(Equipe equipe) {
        if (equipe.getQuantidadeMembros() == null
                || equipe.getQuantidadeMembros() <= 0) {

            throw new RuntimeException(
                    "A equipe deve possuir pelo menos 1 membro."
            );
        }

        return equipeRepository.save(equipe);
    }

    public Equipe atualizar(Long id, Equipe equipeAtualizada) {

        if (equipeAtualizada.getQuantidadeMembros() == null
                || equipeAtualizada.getQuantidadeMembros() <= 0) {

            throw new RuntimeException(
                    "A equipe deve possuir pelo menos 1 membro."
            );
        }

        Equipe equipeExistente = buscarPorId(id);

        equipeExistente.setNome(
                equipeAtualizada.getNome()
        );

        equipeExistente.setEspecialidade(
                equipeAtualizada.getEspecialidade()
        );

        equipeExistente.setStatus(
                equipeAtualizada.getStatus()
        );

        equipeExistente.setQuantidadeMembros(
                equipeAtualizada.getQuantidadeMembros()
        );

        return equipeRepository.save(
                equipeExistente
        );
    }

    public void deletar(Long id) {

        Equipe equipe = buscarPorId(id);

        equipeRepository.delete(equipe);
    }
}
