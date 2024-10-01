package com.dani.demo_spring_revisao_jpa.dao;

import com.dani.demo_spring_revisao_jpa.entity.Autor;
import com.dani.demo_spring_revisao_jpa.entity.InfoAutor;
import com.dani.demo_spring_revisao_jpa.projection.AutorInfoProjection;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AutorDao {

    @PersistenceContext
    private EntityManager manager;

    @Transactional(readOnly = false)
    public void save(Autor autor) {
        this.manager.persist(autor);
    }

    @Transactional(readOnly = false)
    public void update(Autor autor) {
        // merge faz o save ou update quando
        // enviado o id inexistente
        this.manager.merge(autor);
    }

    @Transactional(readOnly = false)
    public void delete(long id){
        this.manager.remove(this.manager.getReference(Autor.class, id));
    }

    @Transactional(readOnly = true)
    public Autor findById(Long id){
        return this.manager.find(Autor.class, id);
    }

    @Transactional(readOnly = true)
    public List<Autor> findAll(){
        //JPQL no lugar do SQL
        String query = "select a from Autor a";
        return this.manager.createQuery(query, Autor.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public List<Autor> findAllByNomeOrSobrenome(String termo){
        //JPQL no lugar do SQL
        String query = "select a from Autor a" +
                " where a.nome like : termo OR " +
                "a.sobrenome like : termo";
        return this.manager
                .createQuery(query, Autor.class)
                .setParameter("termo", "%" + termo + "%")
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Long getTotalElements(){
        //JPQL no lugar do SQL
        String query = "select count(1) from Autor a ";
        return this.manager
                .createQuery(query, Long.class)
                .getSingleResult();
    }

    @Transactional(readOnly = false)
    public Autor saveInfoAutor(InfoAutor infoAutor, Long autorId) {
        Autor autor = findById(autorId);
        autor.setInfoAutor(infoAutor);
        return autor;
    }

    @Transactional(readOnly = true)
    public List<Autor> findbyCargo(String cargo) {
        String query = """
                select a from Autor a
                where a.infoAutor.cargo like : cargo
                order by a.nome asc
                """;
        return this.manager.createQuery(query, Autor.class)
                .setParameter("cargo", "%" + cargo + "%")
                .getResultList();
    }

    @Transactional(readOnly = true)
    public AutorInfoProjection findAutorInfoById(Long id) {
        String query = """
                select new AutorInfoProjection(
                a.nome, a.sobrenome, a.infoAutor.cargo, a.infoAutor.bio)
                from Autor a
                where a.id = :id
                """;
        return this.manager.createQuery(query, AutorInfoProjection.class)
                .setParameter("id",id)
                .getSingleResult();
    }






}
