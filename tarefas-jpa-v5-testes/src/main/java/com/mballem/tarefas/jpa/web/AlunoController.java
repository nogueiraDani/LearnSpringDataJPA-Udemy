package com.mballem.tarefas.jpa.web;

import com.mballem.tarefas.jpa.domain.Aluno;
import com.mballem.tarefas.jpa.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class AlunoController {
    @Autowired
    private AlunoService service;

    @GetMapping(path = "alunos/matricula/{matricula}/ano/{ano}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean existsAlunoByMatriculaAndAnoLetivo(@PathVariable String matricula, @PathVariable int ano) {
        return service.hasAlunoByMatriculaAndAnoLetivo(matricula, ano);
    }

    @GetMapping(path = "alunos/matricula/{matricula}/uf/{uf}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Aluno getAlunoBolsistaByMatriculaAndUf(@PathVariable String matricula, @PathVariable String uf) {
        return service.findAlunoBolsistaByMatriculaAndUf(matricula, uf);
    }

    @GetMapping(path = "alunos/ano/{ano}/periodo", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Aluno> getAlunoBolsistaByMatriculaAndUf(@PathVariable int ano, @RequestParam LocalDate inicio,
                                                        @RequestParam LocalDate fim) {
        return service.findAlunoByAnoLetivoAndDataDeNascimento(ano, fim, inicio);
    }

    @GetMapping(path = "alunos/armarios", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Aluno> getAlunoNaoBolsistaByMatricula(@RequestParam(name = "n") List<Integer> numeros) {
        return service.findAlunosByArmarios(numeros);
    }

    @GetMapping(path = "alunos/total/ano/{ano}/uf/{uf}", produces = MediaType.APPLICATION_JSON_VALUE)
    public long getTotalDeAlunosByAnoLetivoAndUF(@PathVariable int ano, @PathVariable String uf) {
        return service.totalDeAlunosByAnoLetivoAndUF(ano, uf);
    }

    @GetMapping(path = "alunos/uf/{uf}/cidade/{cidade}/ano/{ano}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Aluno> getAlunosNaoBolsistasByUfAndCidadeAndAnoLetivo(@PathVariable String uf, @PathVariable String cidade,
                                                                      @PathVariable int ano) {
        return service.findAlunosNaoBolsistasByUfAndCidadeAndAnoLetivo(uf, cidade, ano);
    }

}
