package com.epam.esm.dao.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ActiveProfiles("test")
@ComponentScan("com.epam.esm.dao")
@EntityScan(basePackages = {"com.epam.esm.dao"})
public class TestConfig implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(TestConfig.class, args);
        int i=0;
    }
}

