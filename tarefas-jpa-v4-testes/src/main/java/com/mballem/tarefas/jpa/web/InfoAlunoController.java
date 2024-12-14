package com.mballem.tarefas.jpa.web;

import com.mballem.tarefas.jpa.domain.InfoAluno;
import com.mballem.tarefas.jpa.service.InfoAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InfoAlunoController {
    @Autowired
    private InfoAlunoService service;

    @GetMapping("info/total/uf/{uf}")
    public long getTotalByUF(@PathVariable String uf) {
        return this.service.getTotalCidadesByUF(uf);
    }

    @GetMapping("info/existe-cidade/{cidade}")
    public boolean hasCidade(@PathVariable String cidade) {
        return this.service.isExistByCidade(cidade);
    }

    @GetMapping("info/cidade/{cidade}")
    public List<InfoAluno> getAllByCidade(@PathVariable String cidade) {
        return this.service.findByCidade(cidade);
    }

    @GetMapping("info/nome/{nome}")
    public InfoAluno getByNomeAluno(@PathVariable String nome) {
        return this.service.findByNomeAluno(nome);
    }

    @GetMapping("info/uf/{uf}/ano/{ano}")
    public List<InfoAluno> getAllByCidadeAndUF(@PathVariable String uf, @PathVariable Integer ano) {
        return this.service.findByUFAndAnoLetivo(uf, ano);
    }

    @GetMapping("info/total/data-nascimento")
    public long getTotalAlunosComDataNascimento() {
        return this.service.totalAlunosComDataNascimento();
    }

    @GetMapping("info/total/bolsistas/uf/{uf}")
    public long getTotalAlunosBolsistasPorUf(@PathVariable String uf) {
        return this.service.totalAlunosBolsistasPorUF(uf);
    }


    @GetMapping(path = "info/alunos/sobrenome/ano-letivo/{ano}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<InfoAluno> getAndRemoveAlunosSemArmarios(@PathVariable int ano, @RequestParam(defaultValue = "nulo") String sobrenome) {
        return this.service.findAllAlunoUltimoAndAnoLetivoExcludente(sobrenome, ano);
    }

    @GetMapping(path = "info/bolsistas", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<InfoAluno> getAllBolsistas(@RequestParam String field, @RequestParam String dir) {
        return this.service.findAllBolsistas(field, dir);
    }

    @GetMapping(path = "info/aluno-mais-velho/cidade/{cidade}", produces = MediaType.APPLICATION_JSON_VALUE)
    public InfoAluno getByCidadeAlunoMaisVelho(@PathVariable String cidade) {
        return this.service.findByCidadeAlunoMaisVelho(cidade);
    }
}
