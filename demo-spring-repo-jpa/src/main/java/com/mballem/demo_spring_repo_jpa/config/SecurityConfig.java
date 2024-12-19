package com.mballem.demo_spring_repo_jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //criando usuarios em memoria para usar no sistema de auditoria
    @Bean
    public UserDetailsService detailsService() {
        // {noop} para informar que o password nao sera criptografado
        UserDetails editor = User.builder().username("editor@exemplo.com")
                                 .password("{noop}123456").roles("EDITOR")
                                 .build();

        UserDetails admin = User.builder().username("admin@exemplo.com")
                                .password("{noop}654321").roles("ADMIN")
                                .build();

        return new InMemoryUserDetailsManager(editor, admin);
    }

    //metodo para configuracao do security
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(
                    auth -> auth.requestMatchers(HttpMethod.GET).permitAll()
                                .anyRequest().authenticated())
            .formLogin(AbstractHttpConfigurer::disable)
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
