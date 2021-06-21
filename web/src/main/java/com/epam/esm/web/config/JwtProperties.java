package com.epam.esm.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
@Configuration
@ConfigurationProperties( prefix = "security.oauth2.client")
@Data
public class JwtProperties {
    @NotNull
    private Integer accessTokenValiditySeconds;
    @NotNull
    private Integer refreshTokenValiditySeconds;
    @NotBlank
    private String clientId;
    @NotBlank
    private String clientSecret;
    @NotNull
    private String[] grantType;
    @NotBlank
    private String signingKey;
    @NotNull
    private String[] scope;
}
