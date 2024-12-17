package com.mballem.demo_spring_repo_jpa.service;

import com.mballem.demo_spring_repo_jpa.entity.Endereco;
import com.mballem.demo_spring_repo_jpa.repository.EnderecoRepository;
import com.mballem.demo_spring_repo_jpa.specification.EnderecoSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional
    public Endereco save(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    @Transactional(readOnly = true)
    public List<Endereco> findByUfAndCidade(String uf,
                                            String cidade) {
        Specification<Endereco> enderecoSpecification = Specification.where(
                EnderecoSpecifications.likeUf(uf)
                                      .and(EnderecoSpecifications.likeCidade(
                                              cidade)));
        return this.enderecoRepository.findAll(enderecoSpecification);
    }

    @Transactional(readOnly = true)
    public List<Endereco> findByUfAndLogradouro(String uf,
                                                String logradouro) {
        Specification<Endereco> enderecoSpecification = Specification.where(
                EnderecoSpecifications.likeUf(uf)
                                      .and(EnderecoSpecifications.likeLogradouro(
                                              logradouro)));
        return this.enderecoRepository.findAll(enderecoSpecification);
    }

    @Transactional(readOnly = true)
    public List<Endereco> findByUfAndInCidades(String uf,
                                               List<String> cidades) {
        Specification<Endereco> enderecoSpecification = Specification.where(
                EnderecoSpecifications.likeUf(uf)
                                      .and(EnderecoSpecifications.inCidades(
                                              cidades)));
        return this.enderecoRepository.findAll(enderecoSpecification);
    }

    @Transactional(readOnly = true)
    public List<Endereco> findByNomeOrSobrenome(String nome,
                                                String sobrenome) {
        Specification<Endereco> enderecoSpecification = Specification.where(
                EnderecoSpecifications.likeAutorNome(nome)
                                      .or(EnderecoSpecifications.likeAutorSobrenome(
                                              sobrenome)));
        return this.enderecoRepository.findAll(enderecoSpecification);
    }

    @Transactional(readOnly = true)
    public Endereco findByNomeAndSobrenome(String nome,
                                           String sobrenome) {

        return this.enderecoRepository.findOne(
                EnderecoSpecifications.likeAutorNomeAndAutorSobrenome(nome,
                        sobrenome)).orElseGet(Endereco::new);
    }

    @Transactional(readOnly = true)
    public List<Endereco> findByAutorTotalPostsAndCidades(long total,
                                                          List<String> cidades) {
        Specification<Endereco> enderecoSpecification = Specification.where(
                EnderecoSpecifications.inCidades(cidades).and(EnderecoSpecifications.byGreaterThanEqualToPosts(total))
        );
        return this.enderecoRepository.findAll(enderecoSpecification);
    }

}
