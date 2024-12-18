package com.mballem.tarefas.jpa;

import com.mballem.datasource.config.H2Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@SpringBootApplication(
        scanBasePackages = {"com.mballem.datasource.config", "com.mballem.tarefas"}
)
public class TarefasJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TarefasJpaApplication.class, args);
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        // ALTER USER sa SET PASSWORD 'Hdoi$';
        return new H2Config().dataSource();
    }
}
