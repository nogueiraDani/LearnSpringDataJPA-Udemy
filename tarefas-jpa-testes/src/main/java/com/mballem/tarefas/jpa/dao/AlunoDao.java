package com.mballem.tarefas.jpa.dao;

import com.mballem.tarefas.jpa.domain.Aluno;
import com.mballem.tarefas.jpa.dto.AlunoArmarioDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AlunoDao {

    @PersistenceContext
    private EntityManager manager;


    @Transactional(readOnly = true)
    public Aluno findByNomeCompleto(String nome) {
        String query = """
                select a
                from Aluno a
                where a.nome like : nome
                """;
        return this.manager.createQuery(query, Aluno.class).setParameter("nome", nome).getSingleResult();
    }

    @Transactional(readOnly = true)
    public Aluno findByMatricula(String matricula) {
        String query = """
                select a
                from Aluno a
                where a.matricula like : matricula
                """;
        return this.manager.createQuery(query, Aluno.class).setParameter("matricula", matricula).getSingleResult();
    }

    @Transactional(readOnly = true)
    public Long findByAnoLetivo(int inicio, int fim) {
        String query = """
                select count(1)
                from Aluno a
                where a.anoLetivo between :inicio and :fim
                """;
        return this.manager.createQuery(query, Long.class).setParameter("inicio", inicio).setParameter("fim", fim).getSingleResult();
    }

    @Transactional(readOnly = true)
    public List<Aluno> findByNomeParcial(String nome) {
        String query = """
                select a
                from Aluno a
                where a.nome like :nome
                """;
        return this.manager.createQuery(query, Aluno.class).setParameter("nome", "%" + nome + "%").getResultList();
    }

    @Transactional(readOnly = true)
    public Aluno findByNumeroArmario(Integer numero) {
        String query = """
                select a
                from Aluno a
                where a.armario.numero = :numero
                """;
        return this.manager.createQuery(query, Aluno.class).setParameter("numero", numero).getSingleResult();
    }

    @Transactional(readOnly = true)
    public List<Long> findByNumerosDeArmarios(List<Integer> numeros) {
        String query = """
                select a.id
                from Aluno a
                where a.armario.numero IN(:numeros)
                """;
        return this.manager.createQuery(query, Long.class).setParameter("numeros", numeros).getResultList();
    }

    @Transactional
    public Aluno updateNomeById(String matricula, String nome) {
        Aluno aluno = findByMatricula(matricula);
        aluno.setNome(nome);
        return aluno;
    }

    @Transactional(readOnly = true)
    public List<AlunoArmarioDto> findAlunosAndArmariosByNomeAndAnoLetivo(String nome, int anoLetivo) {
        String query = """
                select new com.mballem.tarefas.jpa.dto.AlunoArmarioDto(a.aluno.nome, a.numero)
                from Armario a
                where a.aluno.nome like :nome and a.aluno.anoLetivo = :anoLetivo
                order by a.aluno.nome asc
                """;
        return this.manager
                .createQuery(query, AlunoArmarioDto.class)
                .setParameter("nome", "%" + nome + "%")
                .setParameter("anoLetivo", anoLetivo)
                .getResultList();
    }

}
