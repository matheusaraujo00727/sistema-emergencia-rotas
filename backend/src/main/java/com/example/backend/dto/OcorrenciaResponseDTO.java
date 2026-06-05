package com.example.backend.dto;

import com.example.backend.enums.Prioridade;
import com.example.backend.enums.StatusOcorrencia;
import com.example.backend.enums.TipoOcorrencia;

import java.time.LocalDateTime;

public record OcorrenciaResponseDTO(

        Long id,

        String titulo,

        String descricao,

        TipoOcorrencia tipo,

        Prioridade prioridade,

        StatusOcorrencia status,

        LocalDateTime dataAbertura

) {
}
