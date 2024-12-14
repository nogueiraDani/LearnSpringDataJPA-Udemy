package com.mballem.tarefas.jpa.service;

import com.mballem.tarefas.jpa.domain.Aluno;
import com.mballem.tarefas.jpa.dto.AlunoArmarioDto;
import com.mballem.tarefas.jpa.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    /**
     * TESTE 9
     */
    @Transactional(readOnly = true)
    public List<Aluno> findByIds(List<Long> ids) {
        return this.repository.findAllById(ids);
    }

    /**
     * TESTE 10
     */
    @Transactional(readOnly = true)
    public boolean isExists(Long id) {
        return this.repository.existsById(id);
    }

    /**
     * TESTE 11
     */
    @Transactional(readOnly = true)
    public List<Aluno> findByPrimeiroNome(String nome, String order) {
        Sort sort = Sort.by(Sort.Direction.fromString(order), "nome");
        return this.repository.findByPrimeiroNome(nome, sort);
    }

    /**
     * TESTE 12
     */
    @Transactional(readOnly = true)
    public List<AlunoArmarioDto> findByMatriculas(List<String> matriculas) {
        return this.repository.findByMatriculas(matriculas);
    }

    /**
     * TESTE 13
     */
    @Transactional(readOnly = false)
    public List<Aluno> findAndRemoveAlunosSemArmarios() {
        List<Aluno> alunos = this.repository.findAlunosSemArmarios();
        this.repository.deleteAllInBatch(alunos);
        return alunos;
    }


    @Transactional(readOnly = true)
    public List<Aluno> findAllAnoLetivo(Integer inicio, Integer fim) {
        return this.repository.findByAnoLetivoIsBetweenOrderByAnoLetivoDescNomeAsc(inicio, fim);
    }

    /**
     * TESTE 24
     */
    @Transactional(readOnly = true)
    public List<Aluno> findAllAlunos(String nome, String sobrenome) {
        return this.repository.findByNomeStartsWithIgnoreCaseAndNomeEndingWithIgnoreCase(nome,
                sobrenome);
    }

    /**
     * TESTE 27
     */
    @Transactional(readOnly = true)
    public List<Aluno> find3AlunosDoAnoLetivoMaisAtual() {
        return this.repository.findFirst3ByOrderByAnoLetivoDescIdAsc();
    }

    /**
     * TESTE 28
     */
    @Transactional()
    public void excluirAlunoByMatricula(String matricula) {
        this.repository.deleteByMatricula(matricula);
    }
}
