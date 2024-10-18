package com.dani.clinica_medica.dao;

import com.dani.clinica_medica.entity.Especialidade;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EspecialidadeDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = false)
    public void save (Especialidade especialidade){
        this.entityManager.persist(especialidade);
    }
}
