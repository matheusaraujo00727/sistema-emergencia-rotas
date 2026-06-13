package com.example.backend.entity;

import com.example.backend.enums.StatusEquipe;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "equipes")
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String especialidade;

    @Enumerated(EnumType.STRING)
    private StatusEquipe status;

    private Integer quantidadeMembros;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public StatusEquipe getStatus() {
        return status;
    }

    public void setStatus(StatusEquipe status) {
        this.status = status;
    }

    public Integer getQuantidadeMembros() {
        return quantidadeMembros;
    }

    public void setQuantidadeMembros(Integer quantidadeMembros) {
        this.quantidadeMembros = quantidadeMembros;
    }
}
