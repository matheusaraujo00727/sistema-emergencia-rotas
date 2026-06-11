package com.example.backend.dto;

import com.example.backend.enums.StatusAtendimento;

import java.time.LocalDateTime;

public record AtendimentoRequestDTO(

        Long ocorrenciaId,

        Long equipeId,

        Long recursoId,

        LocalDateTime dataInicio,

        LocalDateTime dataFim,

        StatusAtendimento status,

        String observacoes

) {
}