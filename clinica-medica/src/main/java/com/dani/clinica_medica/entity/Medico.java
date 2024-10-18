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

    public Long getCrm() {
        return crm;
    }

    public void setCrm(Long crm) {
        this.crm = crm;
    }


    //especialidade como FK
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "id_especialidade", nullable = false)
    private Especialidade especialidade;

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }


    //nome
    @Column(name = "nome", length = 50, nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    //sobrenome
    @Column(name = "sobrenome", length = 100, nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String sobrenome;

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }


    //telefone
    @Column(name = "telefone", length = 16)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String telefone;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    //email
    @Column(name = "email")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    //construtores
    public Medico() {
    }

    public Medico(Long crm, Especialidade especialidade, String nome, String sobrenome, String telefone, String email) {
        this.crm = crm;
        this.especialidade = especialidade;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.email = email;
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
        return "Medico{" + "crm=" + crm + ", especialidade=" + especialidade + ", nome='" + nome + '\'' + ", sobrenome='" + sobrenome + '\'' + ", telefone='" + telefone + '\'' + ", email='" + email + '\'' + '}';
    }
}
