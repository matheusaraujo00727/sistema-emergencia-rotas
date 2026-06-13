package com.example.backend.mapper;

import com.example.backend.dto.OcorrenciaRequestDTO;
import com.example.backend.dto.OcorrenciaResponseDTO;
import com.example.backend.entity.Ocorrencia;

public class OcorrenciaMapper {

    public static Ocorrencia toEntity(
            OcorrenciaRequestDTO dto
    ) {

        Ocorrencia ocorrencia = new Ocorrencia();

        ocorrencia.setTitulo(dto.titulo());

        ocorrencia.setTipo(dto.tipo());
        ocorrencia.setPrioridade(dto.prioridade());
        ocorrencia.setLocalizacao(dto.localizacao());
        ocorrencia.setDescricao(dto.descricao());

        return ocorrencia;
    }

    public static OcorrenciaResponseDTO toResponseDTO(
            Ocorrencia ocorrencia
    ) {

        return new OcorrenciaResponseDTO(
                ocorrencia.getId(),
                ocorrencia.getTitulo(),
                ocorrencia.getTipo(),
                ocorrencia.getPrioridade(),
                ocorrencia.getStatus(),
                ocorrencia.getLocalizacao(),
                ocorrencia.getDescricao(),
                ocorrencia.getDataAbertura()
        );
    }
}
