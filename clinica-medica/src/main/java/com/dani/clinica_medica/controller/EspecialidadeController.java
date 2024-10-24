package com.dani.clinica_medica.controller;


import com.dani.clinica_medica.DAO.EspecialidadeDAO;
import com.dani.clinica_medica.entity.Especialidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("especialidades")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeDAO especialidadeDao;

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

    @PutMapping("{id}")
    public String atualizarEspecialidadeById(@PathVariable Long id, @RequestBody Especialidade especialidade) {
        especialidade.setId_especialidade(id);
        return especialidadeDao.updateById(especialidade);
    }

    @DeleteMapping("{id}")
    public String deletarEspecialidade(@PathVariable Long id) {
        try {
            especialidadeDao.delete(id);
            return "Especialiade id: " + id + " deletado com sucesso";
        } catch (DataIntegrityViolationException e) {
            return "Especialidade vinculada com cadastro de médico, não é " +
                    "possível deletar.";
        } catch (Exception e) {
            return "Especialidade vinculada com cadastro de médico, não é possivel deletar";
        }
    }

    @GetMapping
    public List<Especialidade> buscarTodasEspecialidades() {
        return especialidadeDao.findAll();
    }

    @GetMapping("{nomeEspecialidade}")
    public List<Especialidade> buscarPelaDescricao(@PathVariable String nomeEspecialidade) {
        return especialidadeDao.findByNomeEspecialidade(nomeEspecialidade);
    }
}
