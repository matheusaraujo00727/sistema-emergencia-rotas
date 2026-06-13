package com.example.backend.dto;

import com.example.backend.enums.StatusAtendimento;

import java.time.LocalDateTime;

public record AtendimentoResponseDTO(

        Long id,

        Long ocorrenciaId,
        String ocorrenciaTitulo,

        Long equipeId,
        String equipeNome,

        Long recursoId,
        String recursoNome,

        LocalDateTime dataInicio,

        LocalDateTime dataFim,

        StatusAtendimento status,

        String observacoes

) {
}