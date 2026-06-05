package com.example.backend.dto;

import com.example.backend.enums.StatusAtendimento;

import java.time.LocalDateTime;

public record AtendimentoResponseDTO(

        Long id,

        Long ocorrenciaId,

        Long equipeId,

        LocalDateTime dataInicio,

        LocalDateTime dataFim,

        StatusAtendimento status,

        String observacoes

) {
}
