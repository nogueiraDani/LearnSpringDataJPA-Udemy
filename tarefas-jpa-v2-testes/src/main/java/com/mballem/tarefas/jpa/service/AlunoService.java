package com.mballem.tarefas.jpa.service;

import com.mballem.tarefas.jpa.domain.Aluno;
import com.mballem.tarefas.jpa.dto.AlunoArmarioDto;
import com.mballem.tarefas.jpa.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;


    @Transactional(readOnly = true)
    public List<Aluno> findByIds(List<Long> ids) {
        return this.alunoRepository.findAllById(ids);
    }

    @Transactional(readOnly = true)
    public boolean isExists(Long id) {
        return this.alunoRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public List<Aluno> findByPrimeiroNome(String nome, String order) {
        //usar o Sort para informar a ordenação da Lista.
        Sort sort = Sort.by(Sort.Direction.fromString(order), "nome");
        return this.alunoRepository.findByPrimeiroNome(nome, sort);
    }

    @Transactional(readOnly = true)
    public List<AlunoArmarioDto> findByMatriculas(List<String> matriculas) {
        return this.alunoRepository.findByMatriculas(matriculas);
    }

    @Transactional
    public List<Aluno> findAndRemoveAlunosSemArmarios() {
        List<Aluno> alunosSemArmario =
                this.alunoRepository.findAlunosSemArmarios();
        this.alunoRepository.deleteAllInBatch(alunosSemArmario);
        return alunosSemArmario;
    }


}
