package com.mballem.demo_spring_repo_jpa.service;

import com.mballem.demo_spring_repo_jpa.entity.InfoAutor;
import com.mballem.demo_spring_repo_jpa.repository.InfoAutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class InfoAutorService {

    @Autowired
    private InfoAutorRepository infoAutorRepository;

    public InfoAutor findById(Long id){
        //QueryByExample, cria um objeto para passar como exemplo na query
        InfoAutor info = new InfoAutor();
        info.setId(id);

        return this.infoAutorRepository.findOne(Example.of(info))
                .orElseGet(InfoAutor::new);
    }

    public List<InfoAutor> findAllContainsCargo(String cargo){
        //QueryByExample, cria um objeto para passar como exemplo na query
        InfoAutor info = new InfoAutor();
        info.setCargo(cargo);

        //ExampleMatcher para simular um filtro de SQL, já que no QBE não se
        // usa query
        ExampleMatcher exampleMatcher =
                ExampleMatcher.matching().withMatcher("cargo",
                        ExampleMatcher.GenericPropertyMatchers.contains());

        return this.infoAutorRepository.findAll(Example.of(info, exampleMatcher));
    }

    public List<InfoAutor> findAllCargoAndEmpresa(String cargo,
                                                  String empresa){
        //QueryByExample, cria um objeto para passar como exemplo na query
        InfoAutor info = new InfoAutor();
        info.setCargo(cargo);
        info.setBio(empresa);

        //ExampleMatcher para simular um filtro de SQL, já que no QBE não se
        // usa query
        // aqui simula um AND
        ExampleMatcher exampleMatcher =
                ExampleMatcher.matchingAll().withMatcher("cargo",
                        ExampleMatcher.GenericPropertyMatchers.startsWith())
                        .withMatcher("bio",
                                ExampleMatcher.GenericPropertyMatchers.contains());

        return this.infoAutorRepository
                .findAll(Example.of(info, exampleMatcher));
    }

    public InfoAutor findFromBio(String bio){
        //QueryByExample, cria um objeto para passar como exemplo na query
        InfoAutor info = new InfoAutor();
        info.setBio(bio);

        //ExampleMatcher para simular um filtro de SQL, já que no QBE não se
        // usa query
        // aqui simula um AND
        ExampleMatcher exampleMatcher =
                ExampleMatcher.matching().withMatcher("bio",
                        ExampleMatcher.GenericPropertyMatchers.contains());

        return this.infoAutorRepository.findBy(
                Example.of(info, exampleMatcher),

                //FluentQuery -> cria uma nova variavel "query" para ordenar a
                // busca e mostrar o primeiro
                query -> query.sortBy(Sort.by("cargo")
                        .descending())
                        .first()
                        .orElseGet(InfoAutor::new)
        );
    }
}
