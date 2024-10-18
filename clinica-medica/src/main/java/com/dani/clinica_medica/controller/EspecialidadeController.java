package com.dani.clinica_medica.controller;


import com.dani.clinica_medica.dao.EspecialidadeDao;
import com.dani.clinica_medica.entity.Especialidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("especialidades")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeDao especialidadeDao;

    @PostMapping
    public Especialidade salvarEspecialidade(@RequestBody Especialidade
                                                         especialidade){
        especialidadeDao.save(especialidade);
        return especialidade;
    }
}
