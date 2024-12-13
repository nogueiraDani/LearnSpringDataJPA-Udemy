package com.mballem.demo_spring_repo_jpa.controller;


import com.mballem.demo_spring_repo_jpa.entity.Categoria;
import com.mballem.demo_spring_repo_jpa.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public List<Categoria> salvar(@RequestBody List<Categoria> categorias){
        return this.categoriaService
                .save(categorias);
    }

    @GetMapping("titulo/{titulo}")
    public Categoria getPorTitulo(@PathVariable String titulo) {
        return this.categoriaService
                .findByTitulo(titulo);
    }

    @GetMapping("titulo/inicio/{titulo}")
    public List<Categoria> getByInicioTitulo(@PathVariable String titulo){
        return this.categoriaService.findByInicioTitulo(titulo);
    }

    @GetMapping("titulos")
    public List<Categoria> getByTitulos(@RequestParam(name = "t") List<String> titulos){
        return this.categoriaService.findByTitulos(titulos);
    }

    @GetMapping
    public List<Categoria> getAllCategoriasOrderAsc(){
        return this.categoriaService.findAllOrderByTituloAsc();
    }
}

