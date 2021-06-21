package com.epam.esm.dao.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ActiveProfiles("testDao")
@ComponentScan("com.epam.esm.dao")
@EntityScan(basePackages = {"com.epam.esm.dao"})
@EnableJpaRepositories(basePackages = {"com.epam.esm.dao"})
public class TestConfig implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(TestConfig.class, args);
    }
}

