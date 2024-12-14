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


    @GetMapping(path = "alunos/ano-letivo", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Aluno> getAndRemoveAlunosSemArmarios(@RequestParam Integer inicio, @RequestParam Integer fim) {
        return service.findAllAnoLetivo(inicio, fim);
    }

    @GetMapping(path = "alunos/nome/{nome}/sobrenome/{sobrenome}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Aluno> getAndRemoveAlunosSemArmarios(@PathVariable String nome, @PathVariable String sobrenome) {
        return service.findAllAlunos(nome, sobrenome);
    }

    @GetMapping(path = "alunos/presenteados", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Aluno> find3AlunosDoAnoLetivoMaisAtual() {
        return service.find3AlunosDoAnoLetivoMaisAtual();
    }

    @DeleteMapping(path = "alunos/matricula/{matricula}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String excluirAlunoPorMatricula(@PathVariable String matricula) {
        List<AlunoArmarioDto> alunos = service.findByMatriculas(List.of(matricula));
        if (alunos.isEmpty()) return "Aluno já excluído anteriormente ou nunca cadastrado.";
        service.excluirAlunoByMatricula(matricula);
        alunos = service.findByMatriculas(List.of(matricula));
        if (alunos.isEmpty()) return "Aluno excluído com sucesso.";
        else return "Aluno não foi excluído da base de dados, tente novamente.";
    }
}
