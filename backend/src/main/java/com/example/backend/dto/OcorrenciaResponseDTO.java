package com.example.backend.dto;

import com.example.backend.enums.Prioridade;
import com.example.backend.enums.StatusOcorrencia;
import com.example.backend.enums.TipoOcorrencia;

import java.time.LocalDateTime;

public record OcorrenciaResponseDTO(

        Long id,

        String titulo,

        TipoOcorrencia tipo,

        Prioridade prioridade,

        StatusOcorrencia status,

        String localizacao,

        String descricao,

        LocalDateTime dataAbertura

) {
}
