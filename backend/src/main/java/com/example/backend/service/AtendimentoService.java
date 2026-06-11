package com.example.backend.service;

import com.example.backend.entity.Atendimento;
import com.example.backend.entity.Equipe;
import com.example.backend.entity.Ocorrencia;
import com.example.backend.enums.StatusAtendimento;
import com.example.backend.enums.StatusEquipe;
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

    private void iniciarFluxoAutomatico(Long atendimentoId) {

        new Thread(() -> {

            try {

                Thread.sleep(2000);

                Atendimento atendimento =
                        atendimentoRepository.findById(atendimentoId)
                                .orElseThrow();

                atendimento.setStatus(
                        StatusAtendimento.EM_ANDAMENTO
                );

                atendimentoRepository.save(atendimento);

                Thread.sleep(7000);

                atendimento.setStatus(
                        StatusAtendimento.CONCLUIDO
                );

                atendimento.setDataFim(
                        LocalDateTime.now()
                );

                atendimentoRepository.save(atendimento);

                Ocorrencia ocorrencia =
                        atendimento.getOcorrencia();

                ocorrencia.setStatus(
                        StatusOcorrencia.FINALIZADA
                );

                ocorrenciaRepository.save(ocorrencia);

                Equipe equipe =
                        atendimento.getEquipe();

                equipe.setStatus(
                        StatusEquipe.DISPONIVEL
                );

                equipeRepository.save(equipe);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();
    }

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

        if (equipe.getStatus() != StatusEquipe.DISPONIVEL) {
            throw new RuntimeException(
                    "A equipe não está disponível para atendimento."
            );
        }

        if (ocorrencia.getStatus() != StatusOcorrencia.ABERTA) {
            throw new RuntimeException(
                    "A ocorrência não está disponível para atendimento."
            );
        }

        atendimento.setDataInicio(LocalDateTime.now());

        ocorrencia.setStatus(StatusOcorrencia.EM_ATENDIMENTO);
        equipe.setStatus(StatusEquipe.EM_ATENDIMENTO);

        atendimento.setOcorrencia(ocorrencia);
        atendimento.setEquipe(equipe);

        atendimento.setStatus(StatusAtendimento.PENDENTE);

        ocorrenciaRepository.save(ocorrencia);
        equipeRepository.save(equipe);

        Atendimento atendimentoSalvo = atendimentoRepository.save(atendimento);

        iniciarFluxoAutomatico(atendimentoSalvo.getId());

        return atendimentoSalvo;
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
