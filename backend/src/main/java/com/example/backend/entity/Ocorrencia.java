package com.example.backend.entity;

import com.example.backend.enums.Prioridade;
import com.example.backend.enums.StatusOcorrencia;
import com.example.backend.enums.TipoOcorrencia;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "ocorrencias")
public class Ocorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 5, max = 100)
    private String titulo;

    @NotBlank
    @Size(min = 10, max = 500)
    private String descricao;

    @NotBlank
    @Size(min = 3, max = 500)
    private String localizacao;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoOcorrencia tipo;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Prioridade prioridade;

    @Enumerated(EnumType.STRING)
    private StatusOcorrencia status;

    private LocalDateTime dataAbertura;

    @PrePersist
    public void prePersist() {
        this.dataAbertura = LocalDateTime.now();

        if (this.status == null) {
            this.status = StatusOcorrencia.ABERTA;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank @Size(min = 5, max = 100) String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotBlank @Size(min = 5, max = 100) String titulo) {
        this.titulo = titulo;
    }

    public @NotBlank @Size(min = 10, max = 500) String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank @Size(min = 10, max = 500) String descricao) {
        this.descricao = descricao;
    }

    public @NotNull TipoOcorrencia getTipo() {
        return tipo;
    }

    public void setTipo(@NotNull TipoOcorrencia tipo) {
        this.tipo = tipo;
    }

    public @NotNull Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(@NotNull Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public @NotBlank @Size(min = 3, max = 500) String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(@NotBlank @Size(min = 3, max = 500) String localizacao) {
        this.localizacao = localizacao;
    }

    public StatusOcorrencia getStatus() {
        return status;
    }

    public void setStatus(StatusOcorrencia status) {
        this.status = status;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }
}
