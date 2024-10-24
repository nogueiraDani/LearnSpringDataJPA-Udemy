package com.dani.clinica_medica.controller;

import com.dani.clinica_medica.DAO.MedicoDAO;
import com.dani.clinica_medica.DTO.MedicoDTO;
import com.dani.clinica_medica.entity.Especialidade;
import com.dani.clinica_medica.entity.Medico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoDAO medicoDao;

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    public ResponseEntity<Medico> salvarMedico(@RequestBody MedicoDTO medicoDTO) {

        // Consulta para buscar a especialidade
        String query = """
                SELECT e
                FROM Especialidade e
                WHERE e.id_especialidade = :id
                """;
        TypedQuery<Especialidade> typedQuery = entityManager
                .createQuery(query, Especialidade.class)
                .setParameter("id", medicoDTO.getEspecialidadeId());

        Especialidade especialidade;

        try {
            especialidade = typedQuery.getSingleResult(); // Busca a especialidade
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null); // Retorna 404 se não encontrar
        }

        Medico medico = new Medico();
        medico.setCrm(medicoDTO.getCrm());
        medico.setNome(medicoDTO.getNome());
        medico.setSobrenome(medicoDTO.getSobrenome());
        medico.setTelefone(medicoDTO.getTelefone());
        medico.setEmail(medicoDTO.getEmail());
        medico.setEspecialidade(especialidade);

        medicoDao.save(medico);
        return ResponseEntity.ok(medico);

    }
}
