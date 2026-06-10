package com.example.backend.dto;

import com.example.backend.enums.StatusEquipe;

public record EquipeResponseDTO(

        Long id,

        String nome,

        String especialidade,

        StatusEquipe status,

        Integer quantidadeMembros

) {
}

