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

    @Transactional
    public void save (Especialidade especialidade){
        this.entityManager.persist(especialidade);
    }

    @Transactional
    public void update (Especialidade especialidade) {
        this.entityManager.merge(especialidade);
    }

    @Transactional
    public String updateById (Especialidade especialidade){
        String query = """
                UPDATE Especialidade e
                SET e.descricao = :novoNome
                WHERE e.id_especialidade = :id
                """;
        this.entityManager.createQuery(query)
                .setParameter("novoNome", especialidade.getDescricao())
                .setParameter("id", especialidade.getId_especialidade())
                .executeUpdate();
        return "Especialidade atualizada";
    }

    @Transactional
    public void delete (Long id){
        this.entityManager.remove(this.entityManager.getReference(Especialidade.class, id));
    }

    @Transactional
    public List<Especialidade> findAll(){
        String query = "SELECT e FROM Especialidade e";
        return this.entityManager.createQuery(query, Especialidade.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Especialidade findById(Long id){
        return this.entityManager.find(Especialidade.class, id);
    }

    @Transactional(readOnly = true)
    public List<Especialidade> findByDescricao (String descricao) {
        String query = """
                SELECT e FROM Especialidade e WHERE e.descricao LIKE :descricao
                """;
        return this.entityManager.createQuery(query, Especialidade.class).setParameter("descricao",descricao).getResultList();
    }
}
