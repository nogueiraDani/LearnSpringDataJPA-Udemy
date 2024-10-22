package com.dani.clinica_medica.controller;


import com.dani.clinica_medica.dao.EspecialidadeDao;
import com.dani.clinica_medica.entity.Especialidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("especialidades")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeDao especialidadeDao;

    @PostMapping
    public Especialidade salvarEspecialidade(@RequestBody Especialidade especialidade) {
        especialidadeDao.save(especialidade);
        return especialidade;
    }

    @PutMapping
    public Especialidade atualizarEspecialidade(@RequestBody Especialidade especialidade) {
        especialidadeDao.update(especialidade);
        return especialidade;
    }

    @DeleteMapping("{id}")
    public String deletarEspecialidade(@PathVariable Long id) {
        especialidadeDao.delete(id);
        try {
            return "Especialiade id: " + id + " deletado com sucesso";
        } catch (Exception e) {
            // parei aqui, mostra o erro no postman mas não cai aqui no catch
            return "Especialidade vinculada com cadastro de médico, não é " + "possivel deletar";
        }
    }

    @GetMapping
    public List<Especialidade> buscarTodasEspecialidades() {
        return especialidadeDao.findAll();
    }

    @GetMapping("{descricao}")
    public Especialidade buscarPelaDescricao(@PathVariable String descricao) {
        return especialidadeDao.findByDescricao(descricao);
    }
}
