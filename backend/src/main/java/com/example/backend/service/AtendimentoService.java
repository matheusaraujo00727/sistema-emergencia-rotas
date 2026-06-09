package com.example.backend.service;

import com.example.backend.entity.Atendimento;
import com.example.backend.entity.Equipe;
import com.example.backend.entity.Ocorrencia;
import com.example.backend.enums.StatusAtendimento;
import com.example.backend.enums.StatusOcorrencia;
import com.example.backend.repository.AtendimentoRepository;
import com.example.backend.repository.EquipeRepository;
import com.example.backend.repository.OcorrenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;
    private final OcorrenciaRepository ocorrenciaRepository;
    private final EquipeRepository equipeRepository;

    public List<Atendimento> listarTodos() {
        return atendimentoRepository.findAll();
    }

    public Atendimento buscarPorId(Long id) {
        return atendimentoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Atendimento não encontrado com ID: " + id
                        ));
    }

    public Atendimento salvar(Long ocorrenciaId, Long equipeId, Atendimento atendimento) {

        Ocorrencia ocorrencia = ocorrenciaRepository.findById(ocorrenciaId)
                .orElseThrow(() -> new RuntimeException("Ocorrência não encontrada"));

        Equipe equipe = equipeRepository.findById(equipeId)
                .orElseThrow(() -> new RuntimeException("Equipe não encontrada"));

        if (!equipe.getAtiva()) {
            throw new RuntimeException(
                    "Equipe inativa não pode atender ocorrências."
            );
        }

        if (ocorrencia.getStatus() != StatusOcorrencia.ABERTA) {
            throw new RuntimeException(
                    "A ocorrência não está disponível para atendimento."
            );
        }

        atendimento.setDataInicio(LocalDateTime.now());

        ocorrencia.setStatus(StatusOcorrencia.EM_ATENDIMENTO);

        atendimento.setOcorrencia(ocorrencia);
        atendimento.setEquipe(equipe);

        ocorrenciaRepository.save(ocorrencia);

        return atendimentoRepository.save(atendimento);
    }

    public Atendimento atualizar(
            Long id,
            Atendimento atendimentoAtualizado
    ) {

        Atendimento atendimentoExistente =
                buscarPorId(id);

        atendimentoExistente.setOcorrencia(
                atendimentoAtualizado.getOcorrencia()
        );

        atendimentoExistente.setEquipe(
                atendimentoAtualizado.getEquipe()
        );

        atendimentoExistente.setDataInicio(LocalDateTime.now());

        atendimentoExistente.setDataFim(
                atendimentoAtualizado.getDataFim()
        );

        atendimentoExistente.setStatus(
                atendimentoAtualizado.getStatus()
        );

        if (atendimentoAtualizado.getStatus() == StatusAtendimento.CONCLUIDO) {

            atendimentoExistente.setDataFim(
                    LocalDateTime.now()
            );

            Ocorrencia ocorrencia =
                    atendimentoExistente.getOcorrencia();

            ocorrencia.setStatus(
                    StatusOcorrencia.FINALIZADA
            );

            ocorrenciaRepository.save(
                    ocorrencia
            );
        }

        atendimentoExistente.setObservacoes(
                atendimentoAtualizado.getObservacoes()
        );

        return atendimentoRepository.save(
                atendimentoExistente
        );
    }

    public void deletar(Long id) {

        Atendimento atendimento =
                buscarPorId(id);

        atendimentoRepository.delete(
                atendimento
        );
    }
}
