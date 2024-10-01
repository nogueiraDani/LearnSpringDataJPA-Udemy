package com.dani.demo_spring_revisao_jpa.controller;

import com.dani.demo_spring_revisao_jpa.dao.AutorDao;
import com.dani.demo_spring_revisao_jpa.entity.Autor;
import com.dani.demo_spring_revisao_jpa.entity.InfoAutor;
import com.dani.demo_spring_revisao_jpa.projection.AutorInfoProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("autores")
public class AutorController {

    @Autowired
    private AutorDao dao;

    @PostMapping
    public Autor salvar(@RequestBody Autor autor){
        dao.save(autor);
        return autor;
    }

    @PutMapping
    public Autor autalizar(@RequestBody Autor autor){
        dao.update(autor);
        return autor;
    }

    @DeleteMapping("{id}")
    public String remover(@PathVariable Long id){
        dao.delete(id);
        return "Autor id: " + id + " excluido com sucesso";
    }

    @GetMapping("{id}")
    public  Autor getById(@PathVariable Long id){
        return dao.findById(id);
    }

    @GetMapping
    public List<Autor> getAll(){
        return dao.findAll();
    }

    @GetMapping("nomeOrSobrenome")
    public List<Autor> getAllbyNomeOrSobrenome(@RequestParam String termo){
        return dao.findAllByNomeOrSobrenome(termo);
    }

    @GetMapping("total")
    public Long getTotalAltores(){
        return dao.getTotalElements();
    }

    @PutMapping("{id}/info")
    public Autor salvarInfoAutor(@PathVariable Long id,
                                 @RequestBody InfoAutor infoAutor){
        return dao.saveInfoAutor(infoAutor, id);
    }

    @GetMapping("info")
    public List<Autor> getAllByCargo(@RequestParam String cargo){
        return dao.findbyCargo(cargo);
    }

    @GetMapping("autor-info")
    public AutorInfoProjection salvarInfoAutor(@RequestParam Long id){
        return dao.findAutorInfoById(id);
    }

}
