package com.example.backend.dto;

import com.example.backend.enums.StatusRecurso;
import com.example.backend.enums.TipoRecurso;

public record RecursoResponseDTO(

        Long id,

        String nome,

        TipoRecurso tipo,

        StatusRecurso status,

        String placa

) {
}