package com.mballem.tarefas.jpa.specification;

import com.mballem.tarefas.jpa.domain.Aluno;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;

public class AlunoSpecifications {

    public static Specification<Aluno> likeMatricula(String matricula) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                root.get("matricula"), matricula);
    }

    public static Specification<Aluno> equalsAnoLetivo(int anoLetivo) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get("anoLetivo"), anoLetivo);
    }

    public static Specification<Aluno> likeUf(String uf) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                root.get("infoAluno").get("uf"), uf.toUpperCase());
    }

    public static Specification<Aluno> isBolsista() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(
                root.get("infoAluno").get("bolsista"));
    }

    public static Specification<Aluno> betweenDataNascimento(LocalDate inicio,
                                                             LocalDate fim) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(
                root.get("infoAluno").get("dataNascimento"), inicio, fim);
    }

    public static Specification<Aluno> inArmario(List<Integer> numeros) {
        return ((root, query, criteriaBuilder) -> root.get("armario")
                                                      .get("numero")
                                                      .in(numeros));
    }

    public static Specification<Aluno> isNotBolsista() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isFalse(
                root.get("infoAluno").get("bolsista"));
    }

    public static Specification<Aluno> likeCidade(String cidade) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.upper(root.get("infoAluno").get("cidade")),
                cidade.toUpperCase()));
    }


}
