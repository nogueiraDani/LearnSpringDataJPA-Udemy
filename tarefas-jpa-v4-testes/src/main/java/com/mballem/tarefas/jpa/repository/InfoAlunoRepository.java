package com.mballem.tarefas.jpa.repository;

import com.mballem.tarefas.jpa.domain.Aluno;
import com.mballem.tarefas.jpa.domain.InfoAluno;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InfoAlunoRepository extends JpaRepository<InfoAluno, Long> {

    long countByDataNascimentoIsNotNull();

    long countByBolsistaIsTrueAndUfIgnoreCase(String uf);

    List<InfoAluno> findByAlunoNomeEndingWithIgnoreCaseAndAlunoAnoLetivoNotOrderByAlunoNomeAsc(String ultimoNome, Integer anoLetivo);

    List<InfoAluno> findByBolsistaIsTrue(Sort sort);

    InfoAluno findFirstByCidadeIgnoreCaseOrderByDataNascimentoAsc(String cidade);
}
