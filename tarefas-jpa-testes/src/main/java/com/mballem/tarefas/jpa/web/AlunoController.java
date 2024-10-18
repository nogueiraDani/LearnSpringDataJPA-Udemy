package com.mballem.tarefas.jpa.web;

import com.mballem.tarefas.jpa.dao.AlunoDao;
import com.mballem.tarefas.jpa.domain.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * NENHUMA ALTERACAO NECESSARIA NESTA CLASSE PARA RESOLVER OS TESTES
 */
@RestController
public class AlunoController {
    @Autowired
    private AlunoDao dao;

    @GetMapping(path = "alunos/nome/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Aluno getByNomeCompleto(@PathVariable String nome) {
        return dao.findByNomeCompleto(nome);
    }

    @GetMapping(path = "alunos/matricula/{matricula}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Aluno getByMatricula(@PathVariable String matricula) {
        return dao.findByMatricula(matricula);
    }

    @GetMapping(path = "alunos/ano-letivo")
    public Long getByAnoLetivo(@RequestParam(name = "inicio") int inicio, @RequestParam(name = "fim") int fim) {
        return dao.findByAnoLetivo(inicio, fim);
    }

    @GetMapping(path = "alunos/parcial", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Aluno> getByNomeParcial(@RequestParam(name = "nome") String nome) {
        return dao.findByNomeParcial(nome);
    }

    @GetMapping(path = "alunos/armario/{numero}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Aluno getByNumeroArmario(@PathVariable Integer numero) {
        return dao.findByNumeroArmario(numero);
    }

    @GetMapping(path = "alunos/armarios")
    public List<Long> getByNumerosDeArmarios(@RequestParam List<Integer> numeros) {
        return dao.findByNumerosDeArmarios(numeros);
    }

    @Transactional
    @PutMapping(path = "alunos/{matricula}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Aluno editNomeById(@PathVariable String matricula, @RequestParam String nome) {
        return dao.updateNomeById(matricula, nome);
    }

    @GetMapping(path = "alunos/lista-amarios", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<?> getAlunosAndArmariosByNomeAndAnoLetivo(@RequestParam(name = "nome") String nome,
                                                          @RequestParam(name = "ano") int ano) {
        return dao.findAlunosAndArmariosByNomeAndAnoLetivo(nome, ano);
    }
}
