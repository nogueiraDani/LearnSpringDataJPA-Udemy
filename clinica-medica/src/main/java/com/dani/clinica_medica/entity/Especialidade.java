package com.dani.clinica_medica.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "especialidades")
public class Especialidade implements Serializable {

    //id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especialidade")
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Long id_especialidade;

    public Long getId_especialidade() {
        return id_especialidade;
    }

    public void setId_especialidade(Long id_especialidade) {
        this.id_especialidade = id_especialidade;
    }


    //descricao
    @Column(name = "descricao", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    //construtores
    public Especialidade(){
    }

    public Especialidade(Long id, String descricao) {
        this.id_especialidade = id;
        this.descricao = descricao;
    }

    //equals e hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Especialidade that = (Especialidade) o;
        return Objects.equals(id_especialidade, that.id_especialidade);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id_especialidade);
    }

    //toString
    @Override
    public String toString() {
        return "Especialidade{" + "id=" + id_especialidade + ", descricao='" + descricao + '\'' + '}';
    }
}
