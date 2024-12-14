package com.mballem.demo_spring_repo_jpa.repository;

import com.mballem.demo_spring_repo_jpa.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCategoriasTituloAndAutorId(String categoria, Long autorId);

    List<Post> findByAutorNomeOrAutorSobrenome(String autorNome,
                                               String autorSobrenome);

    List<Post> findByTituloContainsOrderByAutorNomeAsc(String titulo);

    List<Post> findByDataPublicacaoIsGreaterThanEqual(LocalDate data);

    List<Post> findByDataPublicacaoIsNull();

    @Query("select p from Post p where YEAR(p.dataPublicacao) = :ano")
    Page<Post> findByAno(int ano, Pageable pageable);
}
