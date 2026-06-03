package com.example.backend.service;

import com.example.backend.entity.Atendimento;
import com.example.backend.repository.AtendimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;

    public AtendimentoService(AtendimentoRepository atendimentoRepository) {
        this.atendimentoRepository = atendimentoRepository;
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

    public Atendimento salvar(Atendimento atendimento) {
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

        atendimentoExistente.setDataInicio(
                atendimentoAtualizado.getDataInicio()
        );

        atendimentoExistente.setDataFim(
                atendimentoAtualizado.getDataFim()
        );

        atendimentoExistente.setStatus(
                atendimentoAtualizado.getStatus()
        );

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
