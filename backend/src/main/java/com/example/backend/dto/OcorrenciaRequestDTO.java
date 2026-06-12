package com.example.backend.dto;

import com.example.backend.enums.Prioridade;
import com.example.backend.enums.TipoOcorrencia;

public record OcorrenciaRequestDTO(

        String titulo,

        TipoOcorrencia tipo,

        Prioridade prioridade,

        String localizacao,

        String descricao

) {
}
