package com.epam.esm.web.config;

import com.epam.esm.service.AuthenticatedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private final JwtProperties properties;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder encoder;
    private final AuthenticatedUserService userDetailsService;

    @Autowired
    public AuthorizationServerConfig(JwtProperties properties, AuthenticationManager authenticationManager,
                                     BCryptPasswordEncoder encoder,
                                     AuthenticatedUserService userDetailsService) {
        this.properties = properties;
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(properties.getSigningKey());
        converter.setVerifierKey(properties.getSigningKey());
        return converter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints){
        endpoints
                .userDetailsService(userDetailsService)
                .reuseRefreshTokens(false)
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter())
                .pathMapping("/oauth/token", "/login");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security){
        security.allowFormAuthenticationForClients();
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(properties.getClientId())
                .secret(encoder.encode(properties.getClientSecret()))
                .scopes(properties.getScope())
                .authorizedGrantTypes(properties.getGrantType())
                .accessTokenValiditySeconds(properties.getAccessTokenValiditySeconds())
                .refreshTokenValiditySeconds(properties.getRefreshTokenValiditySeconds());
    }
}
