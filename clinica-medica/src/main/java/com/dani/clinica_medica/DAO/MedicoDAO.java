package com.dani.clinica_medica.DAO;

import com.dani.clinica_medica.entity.Medico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MedicoDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Medico medico){
        this.entityManager.persist(medico);
    }
}
