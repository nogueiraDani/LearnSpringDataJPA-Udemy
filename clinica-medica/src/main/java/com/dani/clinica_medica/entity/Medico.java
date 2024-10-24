package com.dani.clinica_medica.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "medicos")
public class Medico implements Serializable {

    //CRM como Pk
    @Id
    @Column(name = "crm", nullable = false)
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Long crm;

    //especialidade como FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_especialidade", nullable = false)
    private Especialidade especialidade;

    //nome
    @Column(name = "nome", length = 50, nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String nome;

    //sobrenome
    @Column(name = "sobrenome", length = 100, nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String sobrenome;

    //telefone
    @Column(name = "telefone", length = 16)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String telefone;

    //email
    @Column(name = "email")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String email;


    //getters e setters
    public Long getCrm() {
        return crm;
    }

    public void setCrm(Long crm) {
        this.crm = crm;
    }


    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }


    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    //construtores
    public Medico() {
    }

    public Medico(Long crm, String nome, String sobrenome, String telefone, String email, Especialidade especialidade) {
        this.crm = crm;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.email = email;
        this.especialidade = especialidade;
    }


    //equals e hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medico medico = (Medico) o;
        return Objects.equals(crm, medico.crm);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(crm);
    }


    //toString
    @Override
    public String toString() {
        return "Medico{" + "crm=" + crm + ", nome='" + nome + '\'' + ", sobrenome='" + sobrenome + '\'' + ", telefone='" + telefone + '\'' + ", email='" + email + ", especialidade=" + especialidade + '\'' + '}';
    }
}
