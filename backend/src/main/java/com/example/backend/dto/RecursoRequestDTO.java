package com.example.backend.dto;

import com.example.backend.enums.TipoRecurso;

public record RecursoRequestDTO(

        String nome,

        TipoRecurso tipo,

        Boolean disponivel

) {
}
