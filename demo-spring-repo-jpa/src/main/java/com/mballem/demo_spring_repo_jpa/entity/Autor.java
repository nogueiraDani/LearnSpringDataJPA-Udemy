package com.mballem.demo_spring_repo_jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "AUTORES")
//anotacao para a classe ser auditada
@EntityListeners(AuditingEntityListener.class)
public class Autor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autor", nullable = false)
    private Long id;

    @Column(name = "nome", length = 45, nullable = false)
    private String nome;

    @Column(name = "sobrenome", length = 45, nullable = false)
    private String sobrenome;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "id_info")
    private InfoAutor infoAutor;

    @JsonIgnore
    @OneToMany(mappedBy = "autor")
    private List<Post> posts;

    @Column(name = "criado_por")
    @CreatedBy
    private String criadoPor;

    @Column(name = "data_criacao")
    @CreatedDate
    private LocalDateTime dataCriacao;

    @Column(name = "modificado_por")
    @LastModifiedBy
    private String modificadoPor;

    @Column(name = "data_modificacao")
    @LastModifiedDate
    private LocalDateTime dataModificacao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return Objects.equals(id, autor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Autor{" + "id=" + id + '}';
    }
}
