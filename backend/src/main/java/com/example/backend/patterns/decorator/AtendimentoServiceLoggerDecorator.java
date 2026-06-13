package com.example.backend.patterns.decorator;

import com.example.backend.entity.Atendimento;
import com.example.backend.util.AtendimentoLogger;
import com.example.backend.enums.StatusAtendimento;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class AtendimentoServiceLoggerDecorator implements IAtendimentoService {

    private final IAtendimentoService delegate;
    private final AtendimentoLogger logger;

    @Override
    public List<Atendimento> listarTodos() {
        return delegate.listarTodos();
    }

    @Override
    public Atendimento buscarPorId(Long id) {
        return delegate.buscarPorId(id);
    }

    @Override
    public Atendimento salvar(Long ocorrenciaId, Long equipeId, Long recursoId, Atendimento atendimento) {

        Atendimento result = delegate.salvar(ocorrenciaId, equipeId, recursoId, atendimento);

        logger.log("ATENDIMENTO CRIADO | ID: " + result.getId() +
                " | STATUS: " + result.getStatus());

        return result;
    }

    @Override
    public Atendimento atualizar(Long id, Atendimento atendimento) {

        Atendimento antigo = delegate.buscarPorId(id);
        StatusAtendimento statusAnterior = antigo.getStatus();

        Atendimento result = delegate.atualizar(id, atendimento);

        if (statusAnterior != result.getStatus()) {

            logger.log("STATUS ALTERADO | ID: " + id +
                    " | " + statusAnterior + " -> " + result.getStatus());
        }

        if (result.getStatus() == StatusAtendimento.CONCLUIDO) {

            logger.log("ATENDIMENTO FINALIZADO | ID: " + id);
        }

        return result;
    }

    @Override
    public void deletar(Long id) {
        delegate.deletar(id);
    }
}