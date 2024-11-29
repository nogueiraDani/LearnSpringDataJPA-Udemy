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
    private InfoAlunoRepository infoAlunoRepository;


    public long getTotalCidadesByUF(String uf) {
        //criando o modelo para o exemplo
        InfoAluno infoAluno = new InfoAluno();
        infoAluno.setUf(uf);

        //puxa a função count do JPARepository com um Example do modelo acima
        return this.infoAlunoRepository.count(Example.of(infoAluno));
    }


    public boolean isExistByCidade(String cidade) {
        InfoAluno infoAluno = new InfoAluno();
        infoAluno.setCidade(cidade);

        return this.infoAlunoRepository.exists(Example.of(infoAluno));
    }

    public List<InfoAluno> findByCidade(String cidade) {
        InfoAluno infoAluno = new InfoAluno();
        infoAluno.setCidade(cidade);

        //adicionado um matcher aqui para ignorar Maiusculo e minusculo
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase("cidade");
        return this.infoAlunoRepository.findAll(Example.of(infoAluno, exampleMatcher));
    }


    public InfoAluno findByNomeAluno(String nome) {
        Aluno aluno = new Aluno();
        aluno.setNome(nome);

        InfoAluno infoAluno = new InfoAluno();
        infoAluno.setAluno(aluno);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase("nome");

        return this.infoAlunoRepository.findOne(Example.of(infoAluno)).orElseGet(InfoAluno::new);
    }


    public List<InfoAluno> findByUFAndAnoLetivo(String uf, Integer anoLetivo) {
        Aluno aluno = new Aluno();
        aluno.setAnoLetivo(anoLetivo);

        InfoAluno infoAluno = new InfoAluno();
        infoAluno.setUf(uf);
        infoAluno.setAluno(aluno);

        ExampleMatcher exampleMatcher =
                ExampleMatcher.matching().withIgnoreCase("uf");

        Sort sort = Sort.by(Sort.Order.asc("aluno.nome"));

        return this.infoAlunoRepository.findAll(Example.of(infoAluno,
                exampleMatcher), sort);
    }
}
