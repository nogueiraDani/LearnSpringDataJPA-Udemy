package com.mballem.tarefas.jpa.repository;

import com.mballem.tarefas.jpa.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AlunoRepository extends JpaRepository<Aluno, Long>,
        JpaSpecificationExecutor<Aluno> {

}
