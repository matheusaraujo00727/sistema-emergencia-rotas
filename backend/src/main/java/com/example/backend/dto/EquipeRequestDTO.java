package com.example.backend.dto;

import com.example.backend.enums.StatusEquipe;

public record EquipeRequestDTO(

        String nome,

        String especialidade,

        StatusEquipe status,

        Integer quantidadeMembros

) {
}