package com.mballem.demo_spring_repo_jpa.controller;

import com.mballem.demo_spring_repo_jpa.entity.Endereco;
import com.mballem.demo_spring_repo_jpa.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public Endereco salvar(@RequestBody Endereco endereco) {
        return this.enderecoService.save(endereco);
    }

    @GetMapping("uf/{uf}/cidade/{cidade}")
    public List<Endereco> getByUfAndCidade(@PathVariable String uf,
                                           @PathVariable String cidade) {
        return this.enderecoService.findByUfAndCidade(uf, cidade);
    }

    @GetMapping("uf/{uf}/logradouro/{logradouro}")
    public List<Endereco> getByUfAndLogradouro(@PathVariable String uf,
                                               @PathVariable String logradouro) {
        return this.enderecoService.findByUfAndLogradouro(uf, logradouro);
    }

    @GetMapping("uf/{uf}/cidades")
    public List<Endereco> getByUfAndInCidades(@PathVariable String uf,
                                              @RequestParam(name = "nomes") List<String> cidades) {
        return this.enderecoService.findByUfAndInCidades(uf, cidades);
    }

    @GetMapping("autores/nome/{nome}/sobrenome/{sobrenome}")
    public List<Endereco> getByAutorNomeOrAutorSobrenome(@PathVariable String nome,
                                                         @PathVariable String sobrenome) {
        return this.enderecoService.findByNomeOrSobrenome(nome, sobrenome);
    }

    @GetMapping("autor/nome/{nome}/sobrenome/{sobrenome}")
    public Endereco getByAutorNomeAndAutorSobrenome(@PathVariable String nome,
                                                    @PathVariable String sobrenome) {
        return this.enderecoService.findByNomeAndSobrenome(nome, sobrenome);
    }

    @GetMapping("autores/total-posts")
    public List<Endereco> getByAutoresTotalDePostsPorCidade(@RequestParam long total,
                                                            @RequestParam List<String> cidades) {
        return this.enderecoService.findByAutorTotalPostsAndCidades(total,
                cidades);
    }

    @PutMapping("{id}/novo-endereco")
    public String updateEndereco(@PathVariable Long id,
                                 @RequestParam String bairro,
                                 @RequestParam String logradouro,
                                 @RequestParam int numero) {
        return this.enderecoService.updateEndereco(id, bairro, logradouro,
                numero) == 1 ? "Endereco " + "atualizado " + "com " + "sucesso" : "Endereco nao atualizado";
    }

    @GetMapping("uf/{uf}")
    private List<Endereco> getEnderecosByUf(@PathVariable String uf) {
        return this.enderecoService.findByUf(uf);
    }

    @PutMapping("{id}/novo-numero")
    private String updateNumero(@PathVariable Long id,
                                @RequestParam int numero) {
        return this.enderecoService.updateEnrderecoNumero(id, numero);
    }

    @GetMapping("{id}/endereco-completo")
    private String getEnderecoCompleto(@PathVariable Long id) {
        return this.enderecoService.getEnderecoCompleto(id);
    }

    @GetMapping("autor/{id}")
    private Endereco getEnderecoByAutorId(@PathVariable Long id) {
        return this.enderecoService.getEnderecoByAutorId(id);
    }
}
