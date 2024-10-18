package com.dani.clinica_medica.controller;

import com.dani.clinica_medica.dao.MedicoDao;
import com.dani.clinica_medica.entity.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoDao medicoDao;

    @PostMapping
    public Medico salvarMedico(@RequestBody Medico medico){
        medicoDao.save(medico);
        return medico;
    }
}
