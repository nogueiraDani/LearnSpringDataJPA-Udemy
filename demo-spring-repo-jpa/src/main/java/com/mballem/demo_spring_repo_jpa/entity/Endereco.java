package com.mballem.demo_spring_repo_jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString(of = "id")
@Entity
@Table(name = "ENDERECOS")
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco", nullable = false)
    private Long id;

    @Column(name = "uf", length = 2)
    private String uf;

    @Column(name = "cidade", length = 55)
    private String cidade;

    @Column(name = "bairro", length = 55)
    private String bairro;

    @Column(name = "logradouro", length = 120)
    private String logradouro;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "complemento", length = 150)
    private String complemento;

    @Column(name = "cep", length = 9)
    private String cep;

    @OneToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(id, endereco.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
