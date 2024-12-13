package com.mballem.demo_spring_repo_jpa.service;

import com.mballem.demo_spring_repo_jpa.entity.Autor;
import com.mballem.demo_spring_repo_jpa.entity.Categoria;
import com.mballem.demo_spring_repo_jpa.entity.Post;
import com.mballem.demo_spring_repo_jpa.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;


    @Autowired
    private AutorService autorService;

    @Autowired
    private CategoriaService categoriaService;

    @Transactional
    public Post save(Post post){
        Autor autor = this.autorService.findById(post.getAutor().getId());
        post.setAutor(autor);

        List<String> titulos = post.getCategorias().stream().map(
                Categoria::getTitulo
        ).toList();
        List<Categoria> categorias =
                this.categoriaService.findByTitulos(titulos);
        post.setCategorias(categorias);

        return this.postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllByCategoriaAndAutorId(String categoria,
                                                  Long autorId){
        return this.postRepository.findByCategoriasTituloAndAutorId(categoria, autorId);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllByAutor(String autorNome,
                                                  String autorSobrenome){
        return this.postRepository.findByAutorNomeOrAutorSobrenome(autorNome
                , autorSobrenome);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllByTitulo(String titulo){
        return this.postRepository.findByTituloContainsOrderByAutorNomeAsc(titulo);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllByDataPublicacaoMaiorOuIgual(LocalDate data){
        return this.postRepository.findByDataPublicacaoIsGreaterThanEqual(data);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllBySemDataPublicacao() {
        return this.postRepository.findByDataPublicacaoIsNull();

    }
}
