package com.mballem.demo_spring_repo_jpa.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class AuditoriaConfig implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        //busca a autenticaçao ou nao do contexto
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        //verifica a autenticacao
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        User principal = (User) authentication.getPrincipal();
        return Optional.of(principal.getUsername());
    }
}
