package com.mballem.demo_spring_repo_jpa.repository;

import com.mballem.demo_spring_repo_jpa.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>,
        JpaSpecificationExecutor<Endereco> {
}
