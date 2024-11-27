package com.mballem.demo_spring_repo_jpa.projection;

import org.springframework.beans.factory.annotation.Value;

public interface AutorInfoProjection {

    @Value("#{target.nome + ' ' + target.sobrenome}")
    String getNomeCompleto();

    String getCargo();

    String getBio();

}
