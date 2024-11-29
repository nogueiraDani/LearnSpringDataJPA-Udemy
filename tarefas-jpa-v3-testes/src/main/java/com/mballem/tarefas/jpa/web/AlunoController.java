package com.mballem.tarefas.jpa.web;

import com.mballem.tarefas.jpa.domain.Aluno;
import com.mballem.tarefas.jpa.dto.AlunoArmarioDto;
import com.mballem.tarefas.jpa.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AlunoController {
    @Autowired
    private AlunoService service;

    @GetMapping(path = "alunos", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Aluno> getByListaDeIds(@RequestParam List<Long> ids) {
        return service.findByIds(ids);
    }

    @GetMapping(path = "alunos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean isExistById(@PathVariable Long id) {
        return service.isExists(id);
    }

    @GetMapping(path = "alunos/primeiro-nome/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Aluno> getByPrimeiroNome(@PathVariable String nome, @RequestParam String order) {
        return service.findByPrimeiroNome(nome, order);
    }

    @GetMapping(path = "alunos/matriculas", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AlunoArmarioDto> getByMatriculas(@RequestParam List<String> numeros) {
        return service.findByMatriculas(numeros);
    }

    @DeleteMapping(path = "alunos/sem-armario", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Aluno> getAndRemoveAlunosSemArmarios() {
        return service.findAndRemoveAlunosSemArmarios();
    }
}
