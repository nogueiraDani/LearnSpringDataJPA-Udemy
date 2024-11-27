package com.mballem.demo_spring_repo_jpa.service;

import com.mballem.demo_spring_repo_jpa.entity.Autor;
import com.mballem.demo_spring_repo_jpa.entity.InfoAutor;
import com.mballem.demo_spring_repo_jpa.projection.AutorInfoProjection;
import com.mballem.demo_spring_repo_jpa.repository.AutorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    @PersistenceContext
    private EntityManager manager;

    @Transactional(readOnly = false)
    public void save(Autor autor) {
        //this.manager.persist(autor);
        this.repository.save(autor);
    }

    @Transactional(readOnly = false)
    public void update(Autor autor) {
        //this.manager.merge(autor);
        this.repository.save(autor);
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {
        //this.manager.remove(this.manager.getReference(Autor.class, id));
        this.repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Autor findById(Long id) {
        //return this.manager.find(Autor.class, id);

        //return this.repository.findById(id).get(); // nao trata o erro q
        // pode aparecer quando nao houver id no banco

        //return  this.repository.findById(id).orElseGet(Autor::new); //
        // retorna um novo autor vazio para mostrar q nao existe id nop banco

        return this.repository.findById(id).orElseThrow(() -> new RuntimeException("Id nao encontrado"));
    }

    @Transactional(readOnly = true)
    public List<Autor> findAll() {
        //String query = "select a from Autor a";
        //return this.manager.createQuery(query, Autor.class).getResultList();

        return this.repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Autor> findAllByNomeOrSobrenome(String termo) {
        /*String query = "select a from Autor a " +
                "where a.nome like :termo OR a.sobrenome like :termo";
        return this.manager.createQuery(query, Autor.class)
                .setParameter("termo", "%" + termo + "%")
                .getResultList();
                */
        return this.repository.findAllByNomeOrSobrenome("%" + termo + "%");
    }

    @Transactional(readOnly = true)
    public Long getTotalElements() {
        //String query = "select count(1) from Autor a ";
        //return this.manager.createQuery(query, Long.class).getSingleResult();

        return this.repository.count();
    }

    @Transactional(readOnly = false)
    public Autor saveInfoAutor(InfoAutor infoAutor, Long autorId) {
        Autor autor = findById(autorId);
        autor.setInfoAutor(infoAutor);
        return autor;
    }

    @Transactional(readOnly = true)
    public List<Autor> findByCargo(String cargo) {
        /*String query = """
                select a from Autor a
                where a.infoAutor.cargo like :cargo 
                order by a.nome asc
                """;
        return this.manager.createQuery(query, Autor.class)
                .setParameter("cargo", "%" + cargo + "%")
                .getResultList();
                */

        return this.repository.findByCargo("%" + cargo + "%");
    }

    @Transactional(readOnly = true)
    public AutorInfoProjection findAutorInfoById(Long id) {
        /*
        String query = """
                select new AutorInfoProjection(a.nome, a.sobrenome, a.infoAutor.cargo, a.infoAutor.bio)
                from Autor a
                where a.id = :id
                """;
        return this.manager.createQuery(query, AutorInfoProjection.class)
                .setParameter("id", id)
                .getSingleResult();

         */
        return this.repository.findAutorInfoById(id);
    }

}
