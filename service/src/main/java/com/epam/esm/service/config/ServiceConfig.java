package com.epam.esm.service.config;
import com.epam.esm.dao.config.DaoConfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
@Configuration
@ComponentScan("com.epam.esm")
public class ServiceConfig {
    private final DaoConfig daoConfig;

    public ServiceConfig(DaoConfig daoConfig) {
        this.daoConfig = daoConfig;
    }
}
