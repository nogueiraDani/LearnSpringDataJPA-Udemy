package com.mballem.tarefas.jpa.repository;

import com.mballem.tarefas.jpa.domain.Aluno;
import com.mballem.tarefas.jpa.dto.AlunoArmarioDto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    //a query aqui nao precisa do order by pq lá no service usamos o Sort
    @Query("select a from Aluno a " +
            "where a.nome like :nome%")
    List<Aluno> findByPrimeiroNome(@Param("nome") String nome,
                                   @Param("order") Sort sort);

    @Query("select a.nome as nome, " +
            "a.matricula as matricula, " +
            "a.anoLetivo as anoLetivo, " +
            "a.armario.numero as armario " +
            "from Aluno a " +
            "where a.matricula IN :matriculas " +
            "order by a.anoLetivo desc, a.nome asc")
    List<AlunoArmarioDto> findByMatriculas(@Param("matriculas") List<String> matriculas);

    @Query("select a from Aluno a where a.armario IS NULL")
    List<Aluno> findAlunosSemArmarios();
}
