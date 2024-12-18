package com.mballem.tarefas.jpa.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ALUNOS")
public class Aluno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alunos_gen")
    @SequenceGenerator(name = "alunos_gen", sequenceName = "alunos_seq", allocationSize = 1, initialValue = 100)
    @Column(name = "id_aluno", nullable = false)
    private Long id;

    @Column(name = "nome", length = 45, nullable = false)
    private String nome;

    @Column(name = "matricula", length = 10, nullable = false, unique = true)
    private String matricula;

    @Column(name = "ano_letivo", length = 4, nullable = false)
    private Integer anoLetivo;

    @OneToOne(mappedBy = "aluno", cascade = CascadeType.ALL)
    private Armario armario;

    @OneToOne(mappedBy = "aluno")
    private InfoAluno infoAluno;

    public InfoAluno getInfoAluno() {
        return infoAluno;
    }

    public void setInfoAluno(InfoAluno infoAluno) {
        this.infoAluno = infoAluno;
    }

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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(Integer anoLetivo) {
        this.anoLetivo = anoLetivo;
    }

    @JsonManagedReference // A entidade Aluno gerencia a serialização (Evitar dependencia circular no JSON)
    public Armario getArmario() {
        return armario;
    }

    public void setArmario(Armario armario) {
        this.armario = armario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return Objects.equals(id, aluno.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                '}';
    }
}
