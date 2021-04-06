package com.epam.esm.web.config;

import com.epam.esm.dao.config.DaoConfig;
import com.epam.esm.service.config.ServiceConfig;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                WebConfig.class,
                ServiceConfig.class,
                DaoConfig.class
        };
    }
    @Override
    protected String @NotNull [] getServletMappings() {
        return new String[]{"/"};
    }
}
