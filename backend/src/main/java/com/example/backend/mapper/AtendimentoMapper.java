package com.example.backend.mapper;

import com.example.backend.dto.AtendimentoResponseDTO;
import com.example.backend.entity.Atendimento;

public class AtendimentoMapper {

    public static AtendimentoResponseDTO toResponseDTO(
            Atendimento atendimento
    ) {

        return new AtendimentoResponseDTO(
                atendimento.getId(),
                atendimento.getOcorrencia().getId(),
                atendimento.getEquipe().getId(),
                atendimento.getDataInicio(),
                atendimento.getDataFim(),
                atendimento.getStatus(),
                atendimento.getObservacoes()
        );
    }
}
