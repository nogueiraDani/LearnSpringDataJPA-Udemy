package com.mballem.tarefas.jpa.repository;

import com.mballem.tarefas.jpa.domain.Aluno;
import com.mballem.tarefas.jpa.dto.AlunoArmarioDto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    @Query("select a from Aluno a where a.nome like :nome%")
    List<Aluno> findByPrimeiroNome(String nome, Sort order);

    @Query("select a.nome as nome, a.matricula as matricula, a.anoLetivo as anoLetivo, a.armario.numero as armario " +
            "from Aluno a " +
            "where a.matricula IN :matriculas " +
            "order by a.anoLetivo desc, a.nome asc")
    List<AlunoArmarioDto> findByMatriculas(List<String> matriculas);

    @Query("select a from Aluno a where a.armario is null")
    List<Aluno> findAlunosSemArmarios();

    List<Aluno> findByAnoLetivoIsBetweenOrderByAnoLetivoDescNomeAsc(Integer inicio, Integer fim);

    List<Aluno> findByNomeStartsWithIgnoreCaseAndNomeEndingWithIgnoreCase(String nome, String sobrenome);

    List<Aluno> findFirst3ByOrderByAnoLetivoDescIdAsc();

    void deleteByMatricula(String matricula);
}
