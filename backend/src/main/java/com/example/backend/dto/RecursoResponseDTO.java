package com.example.backend.dto;

import com.example.backend.enums.TipoRecurso;

public record RecursoResponseDTO(

        Long id,

        String nome,

        TipoRecurso tipo,

        Boolean disponivel,

        String placa

) {
}