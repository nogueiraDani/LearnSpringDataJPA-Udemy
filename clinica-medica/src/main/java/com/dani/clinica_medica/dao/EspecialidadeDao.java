package com.dani.clinica_medica.dao;

import com.dani.clinica_medica.entity.Especialidade;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EspecialidadeDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = false)
    public void save (Especialidade especialidade){
        this.entityManager.persist(especialidade);
    }

    @Transactional
    public void update (Especialidade especialidade) {
        this.entityManager.merge(especialidade);
    }

    @Transactional
    public void delete (Long id){
        this.entityManager.remove(this.entityManager.getReference(Especialidade.class, id));
    }

    @Transactional
    public List<Especialidade> findAll(){
        String query = "select especialidade from Especialidade especialidade";
        return this.entityManager.createQuery(query, Especialidade.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Especialidade findById(Long id){
        return this.entityManager.find(Especialidade.class, id);
    }

    @Transactional(readOnly = true)
    public Especialidade findByDescricao (String descricao) {
        String query = """
                select e from Especialidade e where e.descricao like :descricao
                """;
        return this.entityManager.createQuery(query, Especialidade.class).setParameter("descricao",descricao).getSingleResult();
    }
}
