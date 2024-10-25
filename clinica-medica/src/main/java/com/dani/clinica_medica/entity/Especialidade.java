package com.dani.clinica_medica.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "especialidades")
public class Especialidade implements Serializable {

    //id_especialidade
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especialidade")
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Long idEspecialidade;

    //nomeEspecialidade
    @Column(name = "nome_especialidade", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String nomeEspecialidade;


    public Long getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(Long idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }


    public String getNomeEspecialidade() {
        return nomeEspecialidade;
    }

    public void setNomeEspecialidade(String nomeEspecialidade) {
        this.nomeEspecialidade = nomeEspecialidade;
    }


    //construtores
    public Especialidade(){
    }

    public Especialidade(Long id, String nomeEspecialidade) {
        this.idEspecialidade = id;
        this.nomeEspecialidade = nomeEspecialidade;
    }

    //equals e hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Especialidade that = (Especialidade) o;
        return Objects.equals(idEspecialidade, that.idEspecialidade);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idEspecialidade);
    }

    //toString
    @Override
    public String toString() {
        return "Especialidade{" + "id=" + idEspecialidade + ", " +
                "nomeEspecialidade" +
                "='" + nomeEspecialidade + '\'' + '}';
    }
}
