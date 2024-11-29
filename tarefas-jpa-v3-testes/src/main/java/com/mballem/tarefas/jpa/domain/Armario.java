package com.mballem.tarefas.jpa.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ARMARIOS")
public class Armario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "armario_gen")
    @SequenceGenerator(name = "armario_gen", sequenceName = "armario_seq", initialValue = 15, allocationSize = 1)
    @Column(name = "id_armario", nullable = false)
    private Long id;

    @Column(name = "numero", length = 10, nullable = false)
    private int numero;

    @OneToOne(optional = false)
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @JsonBackReference // Armario é referenciada por Aluno (evitar dependencia circular no JSON)
    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Armario armario = (Armario) o;
        return Objects.equals(id, armario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Armario{" +
                "id=" + id +
                '}';
    }
}
