package com.mballem.demo_spring_repo_jpa.controller;

import com.mballem.demo_spring_repo_jpa.entity.Post;
import com.mballem.demo_spring_repo_jpa.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public Post salvar(@RequestBody Post post) {
        return this.postService.save(post);
    }

    @GetMapping("categoria/{categoria}/autor/{autorId}")
    public List<Post> getByCategoriaAndAutor(@PathVariable String categoria,
                                             @PathVariable Long autorId){
        return this.postService.findAllByCategoriaAndAutorId(categoria,
                autorId);
    }

    @GetMapping("autor/nome/{nome}/sobrenome/{sobrenome}")
    public List<Post> getByAutor(@PathVariable String nome,
                                             @PathVariable String sobrenome){
        return this.postService.findAllByAutor(nome, sobrenome);
    }

    @GetMapping("titulo/{titulo}")
    public List<Post> getByTitulo(@PathVariable String titulo){
        return this.postService.findAllByTitulo(titulo);
    }

    @GetMapping("data-publicacao/{data}")
    public List<Post> getByData(@PathVariable LocalDate data){
        return this.postService.findAllByDataPublicacaoMaiorOuIgual(data);
    }

    @GetMapping("sem-data-publicacao")
    public List<Post> getBySemDataPublicacao(){
        return this.postService.findAllBySemDataPublicacao();
    }

}
