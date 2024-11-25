package lshh.pollservice.common;

import lshh.pollservice.common.lib.JwtHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Value("${security.access.secret-key}")
    private String secretKey;
    @Value("${security.access.expiration}")
    private Long jwtExpiration;

    @Bean
    public JwtHelper jwtHelper() {
        return new JwtHelper(secretKey, jwtExpiration);
    }
}
