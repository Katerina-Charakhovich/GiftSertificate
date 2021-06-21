package com.epam.esm.web.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration
public class PasswordEncoder  {
    private static final int ENCRYPTION_STRENGTH = 4;

    /**
     * Password encoder bean.
     *
     * @return the password encoder
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(ENCRYPTION_STRENGTH );
    }
}
