package com.mballem.demo_spring_repo_jpa.repository;

import com.mballem.demo_spring_repo_jpa.entity.InfoAutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoAutorRepository extends JpaRepository<InfoAutor, Long> {
}
