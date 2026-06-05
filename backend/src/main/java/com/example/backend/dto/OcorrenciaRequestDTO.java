package com.example.backend.dto;

import com.example.backend.enums.Prioridade;
import com.example.backend.enums.TipoOcorrencia;

public record OcorrenciaRequestDTO(

        String titulo,

        String descricao,

        TipoOcorrencia tipo,

        Prioridade prioridade

) {
}
