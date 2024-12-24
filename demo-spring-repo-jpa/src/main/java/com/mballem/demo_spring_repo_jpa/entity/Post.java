package com.mballem.demo_spring_repo_jpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "POSTS")
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post", nullable = false)
    private Long id;

    @Column(name = "titulo", length = 65, nullable = false, unique = true)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;

    //relacionamento n para n --> atenção!
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "posts_tem_categorias",
        joinColumns = @JoinColumn(name = "id_post"),
        inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private List<Categoria> categorias;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_publicacao")
    private LocalDate dataPublicacao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id + '}';
    }
}
