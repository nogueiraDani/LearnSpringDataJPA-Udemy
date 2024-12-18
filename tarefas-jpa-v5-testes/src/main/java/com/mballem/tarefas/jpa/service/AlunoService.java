package com.mballem.tarefas.jpa.service;

import com.mballem.tarefas.jpa.domain.Aluno;
import com.mballem.tarefas.jpa.repository.AlunoRepository;
import com.mballem.tarefas.jpa.specification.AlunoSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    /**
     * TESTE 30
     */
    @Transactional(readOnly = true)
    public boolean hasAlunoByMatriculaAndAnoLetivo(String matricula,
                                                   int ano) {

        Specification<Aluno> alunoSpecification = Specification.where(
                AlunoSpecifications.likeMatricula(matricula)
                                   .and(AlunoSpecifications.equalsAnoLetivo(
                                           ano)));
        return this.alunoRepository.exists(alunoSpecification);
    }

    /**
     * TESTE 31
     */
    @Transactional(readOnly = true)
    public Aluno findAlunoBolsistaByMatriculaAndUf(String matricula,
                                                   String uf) {

        Specification<Aluno> alunoSpecification = Specification.allOf(
                AlunoSpecifications.likeMatricula(matricula),
                AlunoSpecifications.likeUf(uf),
                AlunoSpecifications.isBolsista());

        return this.alunoRepository.findOne(alunoSpecification)
                                   .orElseGet(Aluno::new);
    }

    /**
     * TESTE 32
     */
    @Transactional(readOnly = true)
    public List<Aluno> findAlunoByAnoLetivoAndDataDeNascimento(int anoLetivo,
                                                               LocalDate inicio,
                                                               LocalDate fim) {
        Specification<Aluno> alunoSpecification = Specification.anyOf(
                AlunoSpecifications.equalsAnoLetivo(anoLetivo),
                AlunoSpecifications.betweenDataNascimento(inicio, fim));
        return this.alunoRepository.findAll(alunoSpecification);
    }

    /**
     * TESTE 33
     */
    @Transactional(readOnly = true)
    public List<Aluno> findAlunosByArmarios(List<Integer> numeros) {
        Sort sort = Sort.by(Sort.Direction.ASC, "nome");
        return this.alunoRepository.findAll(
                AlunoSpecifications.inArmario(numeros), sort);
    }

    /**
     * TESTE 34
     */
    @Transactional(readOnly = true)
    public long totalDeAlunosByAnoLetivoAndUF(int anoLetivo,
                                              String uf) {
        Specification<Aluno> alunoSpecification = Specification.allOf(
                AlunoSpecifications.equalsAnoLetivo(anoLetivo),
                AlunoSpecifications.likeUf(uf));

        return this.alunoRepository.count(alunoSpecification);
    }

    /**
     * TESTE 35
     */
    @Transactional(readOnly = true)
    public List<Aluno> findAlunosNaoBolsistasByUfAndCidadeAndAnoLetivo(String uf,
                                                                       String cidade,
                                                                       int anoLetivo) {
        Specification<Aluno> alunoSpecification = Specification.allOf(
                AlunoSpecifications.isNotBolsista(),
                AlunoSpecifications.likeUf(uf),
                AlunoSpecifications.likeCidade(cidade),
                AlunoSpecifications.equalsAnoLetivo(anoLetivo));
        Sort sort = Sort.by("nome").ascending()
                        .and(Sort.by("anoLetivo").descending());
        return this.alunoRepository.findAll(alunoSpecification, sort);
    }
}