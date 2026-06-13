package com.example.backend.patterns.decorator;

import com.example.backend.entity.Atendimento;
import java.util.List;

public interface IAtendimentoService {

    List<Atendimento> listarTodos();

    Atendimento buscarPorId(Long id);

    Atendimento salvar(Long ocorrenciaId, Long equipeId, Long recursoId, Atendimento atendimento);

    Atendimento atualizar(Long id, Atendimento atendimento);

    void deletar(Long id);
}