package com.example.backend.entity;

import com.example.backend.enums.StatusRecurso;
import com.example.backend.enums.TipoRecurso;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "recursos")
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoRecurso tipo;

    @Enumerated(EnumType.STRING)
    private StatusRecurso status;

    @Pattern(
            regexp = "^$|^[A-Z]{3}[0-9][A-Z][0-9]{2}$",
            message = "Placa deve estar no formato Mercosul (AAA1A11)"
    )
    @Column(unique = true, nullable = true)
    private String placa;

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

    public TipoRecurso getTipo() {
        return tipo;
    }

    public void setTipo(TipoRecurso tipo) {
        this.tipo = tipo;
    }

    public StatusRecurso getStatus() {
        return status;
    }

    public void setStatus(StatusRecurso status) {
        this.status = status;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}