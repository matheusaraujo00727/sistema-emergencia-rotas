package com.example.backend.service;

import com.example.backend.entity.Ocorrencia;
import com.example.backend.enums.StatusOcorrencia;
import com.example.backend.repository.OcorrenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OcorrenciaService {

    private final OcorrenciaRepository ocorrenciaRepository;

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

        if (ocorrencia.getTipo() == null) {
            throw new RuntimeException(
                    "Tipo da ocorrência é obrigatório."
            );
        }

        if (ocorrencia.getPrioridade() == null) {
            throw new RuntimeException(
                    "Prioridade da ocorrência é obrigatória."
            );
        }

        if (ocorrencia.getStatus() == null) {
            ocorrencia.setStatus(
                    StatusOcorrencia.ABERTA
            );
        }

        return ocorrenciaRepository.save(ocorrencia);
    }

    public Ocorrencia atualizar(Long id, Ocorrencia ocorrenciaAtualizada) {

        Ocorrencia ocorrenciaExistente = buscarPorId(id);

        if (ocorrenciaExistente.getStatus()
                == StatusOcorrencia.FINALIZADA) {

            throw new RuntimeException(
                    "Não é possível alterar uma ocorrência finalizada."
            );
        }

        if (ocorrenciaExistente.getStatus()
                == StatusOcorrencia.CANCELADA) {

            throw new RuntimeException(
                    "Não é possível alterar uma ocorrência cancelada."
            );
        }

        ocorrenciaExistente.setTitulo(
                ocorrenciaAtualizada.getTitulo()
        );

       ocorrenciaExistente.setLocalizacao(
                ocorrenciaAtualizada.getLocalizacao()
        );

        ocorrenciaExistente.setTipo(
                ocorrenciaAtualizada.getTipo()
        );

        ocorrenciaExistente.setPrioridade(
                ocorrenciaAtualizada.getPrioridade()
        );

        if (ocorrenciaAtualizada.getStatus() != null) {
            ocorrenciaExistente.setStatus(
                    ocorrenciaAtualizada.getStatus()
            );
        }

        ocorrenciaExistente.setDescricao(
                ocorrenciaAtualizada.getDescricao()
        );

        return ocorrenciaRepository.save(
                ocorrenciaExistente
        );
    }

    public void deletar(Long id) {

        Ocorrencia ocorrencia = buscarPorId(id);

        if (ocorrencia.getStatus()
                == StatusOcorrencia.EM_ATENDIMENTO) {

            throw new RuntimeException(
                    "Não é possível excluir uma ocorrência em atendimento."
            );
        }

        ocorrenciaRepository.delete(ocorrencia);
    }
}
