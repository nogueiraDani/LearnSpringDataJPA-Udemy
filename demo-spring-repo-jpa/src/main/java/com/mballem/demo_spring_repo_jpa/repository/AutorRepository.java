package com.mballem.demo_spring_repo_jpa.repository;


import com.mballem.demo_spring_repo_jpa.entity.Autor;
import com.mballem.demo_spring_repo_jpa.projection.AutorInfoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("select a from Autor a where a.infoAutor.cargo like :cargo order by a.nome asc")
    List<Autor> findByCargo(@Param("cargo") String cargo);


    @Query("select a from Autor a where a.nome like :termo OR a.sobrenome like :termo")
    List<Autor> findAllByNomeOrSobrenome(@Param("termo") String termo);

    @Query("select a.nome as nome, a.sobrenome as sobrenome, a.infoAutor.cargo as cargo, a.infoAutor.bio as bio " +
            "from Autor a where a.id = :id")
    AutorInfoProjection findAutorInfoById(Long id);
}
