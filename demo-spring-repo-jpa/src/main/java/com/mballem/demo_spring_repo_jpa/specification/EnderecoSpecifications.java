package com.mballem.demo_spring_repo_jpa.specification;

import com.mballem.demo_spring_repo_jpa.entity.Autor;
import com.mballem.demo_spring_repo_jpa.entity.Endereco;
import com.mballem.demo_spring_repo_jpa.entity.Post;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class EnderecoSpecifications {

    public static Specification<Endereco> likeUf(String uf) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                root.get("uf"), uf);
    }

    public static Specification<Endereco> likeCidade(String cidade) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(
                root.get("cidade"), cidade));
    }

    public static Specification<Endereco> likeLogradouro(String logradouro) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(
                root.get("logradouro"), "%" + logradouro + "%"));
    }

    public static Specification<Endereco> inCidades(List<String> cidades) {
        return ((root, query, criteriaBuilder) -> root.get("cidade")
                                                      .in(cidades));
    }

    public static Specification<Endereco> likeAutorNome(String nome) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(
                root.get("autor").get("nome"), nome));
    }

    public static Specification<Endereco> likeAutorSobrenome(String sobrenome) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(
                root.get("autor").get("sobrenome"), sobrenome));
    }

    public static Specification<Endereco> likeAutorNomeAndAutorSobrenome(String nome,
                                                                         String sobrenome) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.like(root.get("autor").get("nome"), nome),
                criteriaBuilder.like(root.get("autor").get("sobrenome"),
                        sobrenome));
    }

    public static Specification<Endereco> byGreaterThanEqualToPosts(long total) {
        /**
         * specification equivalente a SQL abaixo:
         *
         * select e.id_endereco, e.cidade, a.id_autor
         * from Enderecos e
         * join Autor a
         *      on e.id_autor = a.id_autor
         * join Posts p
         *      on a.id_autor = p.id_autor
         * group by p.id_autor
         * having count(p.id_post) >= 7
         *
         */
        return (root, query, criteriaBuilder) -> {
            //Faz o join entre Endereco e Autor
            Join<Endereco, Autor> autor = root.join("autor");

            //Faz o join entre Autor e Post
            Join<Autor, Post> post = autor.join("posts");

            //group by por autor
            query.groupBy(post.get("autor"));

            //having count
            query.having(criteriaBuilder.greaterThanOrEqualTo(
                    criteriaBuilder.count(post.get("id")),total
            ));

            return query.getRestriction();
        };
    }

}
