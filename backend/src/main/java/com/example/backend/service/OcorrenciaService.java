package com.example.backend.service;

import com.example.backend.entity.Ocorrencia;
import com.example.backend.repository.OcorrenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OcorrenciaService {

    private final OcorrenciaRepository ocorrenciaRepository;

    public OcorrenciaService(OcorrenciaRepository ocorrenciaRepository) {
        this.ocorrenciaRepository = ocorrenciaRepository;
    }

    public List<Ocorrencia> listarTodas() {
        return ocorrenciaRepository.findAll();
    }

    public Ocorrencia buscarPorId(Long id) {
        return ocorrenciaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Ocorrência não encontrada com ID: " + id
                        ));
    }

    public Ocorrencia salvar(Ocorrencia ocorrencia) {
        return ocorrenciaRepository.save(ocorrencia);
    }

    public Ocorrencia atualizar(Long id, Ocorrencia ocorrenciaAtualizada) {

        Ocorrencia ocorrenciaExistente = buscarPorId(id);

        ocorrenciaExistente.setTitulo(
                ocorrenciaAtualizada.getTitulo()
        );

        ocorrenciaExistente.setDescricao(
                ocorrenciaAtualizada.getDescricao()
        );

        ocorrenciaExistente.setTipo(
                ocorrenciaAtualizada.getTipo()
        );

        ocorrenciaExistente.setPrioridade(
                ocorrenciaAtualizada.getPrioridade()
        );

        ocorrenciaExistente.setStatus(
                ocorrenciaAtualizada.getStatus()
        );

        return ocorrenciaRepository.save(
                ocorrenciaExistente
        );
    }

    public void deletar(Long id) {

        Ocorrencia ocorrencia = buscarPorId(id);

        ocorrenciaRepository.delete(ocorrencia);
    }
}
