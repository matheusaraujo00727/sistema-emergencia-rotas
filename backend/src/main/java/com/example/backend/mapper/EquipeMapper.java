package com.example.backend.mapper;

import com.example.backend.dto.EquipeRequestDTO;
import com.example.backend.dto.EquipeResponseDTO;
import com.example.backend.entity.Equipe;

public class EquipeMapper {

    public static Equipe toEntity(
            EquipeRequestDTO dto
    ) {

        Equipe equipe = new Equipe();

        equipe.setNome(dto.nome());
        equipe.setEspecialidade(dto.especialidade());
        equipe.setStatus(dto.status());
        equipe.setQuantidadeMembros(dto.quantidadeMembros());

        return equipe;
    }

    public static EquipeResponseDTO toResponseDTO(
            Equipe equipe
    ) {

        return new EquipeResponseDTO(
                equipe.getId(),
                equipe.getNome(),
                equipe.getEspecialidade(),
                equipe.getStatus(),
                equipe.getQuantidadeMembros()

        );
    }
}
