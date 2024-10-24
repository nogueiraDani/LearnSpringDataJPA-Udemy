package com.dani.clinica_medica.DTO;

public class MedicoDTO {
    private Long crm;
    private String nome;
    private String sobrenome;
    private String telefone;
    private String email;
    private Long especialidadeId; // ID da especialidade

    // Getters e Setters
    public Long getCrm() {
        return crm;
    }

    public void setCrm(Long crm) {
        this.crm = crm;
    }

    public Long getEspecialidadeId() {
        return especialidadeId;
    }

    public void setEspecialidadeId(Long especialidadeId) {
        this.especialidadeId = especialidadeId;
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

    @Override
    public String toString() {
        return "MedicoDTO{" + "crm=" + crm + ", nome='" + nome + '\'' + ", sobrenome='" + sobrenome + '\'' + ", telefone='" + telefone + '\'' + ", email='" + email + '\'' + ", especialidadeId=" + especialidadeId + '}';
    }
}

