package com.mballem.tarefas.jpa.web;

import com.mballem.tarefas.jpa.domain.InfoAluno;
import com.mballem.tarefas.jpa.service.InfoAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
