package com.example.backend.mapper;

import com.example.backend.dto.RecursoRequestDTO;
import com.example.backend.dto.RecursoResponseDTO;
import com.example.backend.entity.Recurso;

public class RecursoMapper {

    public static Recurso toEntity(
            RecursoRequestDTO dto
    ) {

        Recurso recurso = new Recurso();

        recurso.setNome(dto.nome());
        recurso.setTipo(dto.tipo());
        recurso.setStatus(dto.status());
        recurso.setPlaca(dto.placa());

        return recurso;
    }

    public static RecursoResponseDTO toResponseDTO(
            Recurso recurso
    ) {

        return new RecursoResponseDTO(
                recurso.getId(),
                recurso.getNome(),
                recurso.getTipo(),
                recurso.getStatus(),
                recurso.getPlaca()
        );
    }
}
