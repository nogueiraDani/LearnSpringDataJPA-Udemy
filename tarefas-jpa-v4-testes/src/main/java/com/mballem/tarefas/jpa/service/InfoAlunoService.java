package com.mballem.tarefas.jpa.service;

import com.mballem.tarefas.jpa.domain.Aluno;
import com.mballem.tarefas.jpa.domain.InfoAluno;
import com.mballem.tarefas.jpa.repository.InfoAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class InfoAlunoService {

    @Autowired
    private InfoAlunoRepository repository;

    // TESTE 14
    public long getTotalCidadesByUF(String uf) {
        InfoAluno infoAluno = new InfoAluno();
        infoAluno.setUf(uf);
        return this.repository.count(Example.of(infoAluno));
    }

    // TESTE 15
    public boolean isExistByCidade(String cidade) {
        InfoAluno infoAluno = new InfoAluno();
        infoAluno.setCidade(cidade);
        return this.repository.exists(Example.of(infoAluno));
    }

    // TESTE 16
    public List<InfoAluno> findByCidade(String cidade) {
        InfoAluno infoAluno = new InfoAluno();
        infoAluno.setCidade(cidade);

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase("cidade");

        return this.repository.findAll(Example.of(infoAluno, matcher));
    }

    // TESTE 17
    public InfoAluno findByNomeAluno(String nome) {
        Aluno aluno = new Aluno();
        aluno.setNome(nome);

        InfoAluno infoAluno = new InfoAluno();
        infoAluno.setAluno(aluno);

        return this.repository.findOne(Example.of(infoAluno)).orElseGet(InfoAluno::new);
    }

    // TESTE 18
    public List<InfoAluno> findByUFAndAnoLetivo(String uf, Integer anoLetivo) {
        Aluno aluno = new Aluno();
        aluno.setAnoLetivo(anoLetivo);

        InfoAluno infoAluno = new InfoAluno();
        infoAluno.setAluno(aluno);
        infoAluno.setUf(uf);

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase("uf");

        Sort sort = Sort.by(Sort.Order.asc("aluno.nome"));

        return this.repository.findAll(Example.of(infoAluno, matcher), sort);
    }

    // TESTE 20
    public long totalAlunosComDataNascimento() {
        return this.repository.countByDataNascimentoIsNotNull();
    }

    // TESTE 21
    public long totalAlunosBolsistasPorUF(String uf) {
        return this.repository.countByBolsistaIsTrueAndUfIgnoreCase(uf);
    }

    // TESTE 23
    public List<InfoAluno> findAllAlunoUltimoAndAnoLetivoExcludente(String ultimoNome, Integer anoLetivo) {
        return this.repository.findByAlunoNomeEndingWithIgnoreCaseAndAlunoAnoLetivoNotOrderByAlunoNomeAsc(ultimoNome, anoLetivo);
    }

    // TESTE 25
    public List<InfoAluno> findAllBolsistas(String field, String dir) {
        Sort sort = Sort.by(Sort.Direction.fromString(dir), field);
        return this.repository.findByBolsistaIsTrue(sort);
    }

    // TESTE 26
    public InfoAluno findByCidadeAlunoMaisVelho(String cidade) {
       return this.repository.findFirstByCidadeIgnoreCaseOrderByDataNascimentoAsc(cidade);
    }
}
