package lshh.pollservice.common.bean;

import lombok.extern.slf4j.Slf4j;
import lshh.pollservice.common.lib.ClockManager;
import lshh.pollservice.common.lib.HashTokenHelper;
import lshh.pollservice.common.lib.JwtHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class TokenHelperConfig {
    @Value("${security.authentication.secret-key}")
    private String authenticationSecretKey;
    @Value("${security.authentication.expiration}")
    private Long authenticationTokenExpiration;
    @Value("${security.refresh.secret-key}")
    private String refreshSecretKey;
    @Value("${security.refresh.expiration}")
    private Long refreshTokenExpiration;
    @Value("${security.access.secret-key}")
    private String accessSecretKey;
    @Value("${security.access.expiration}")
    private Long jwtExpiration;

    @Bean("authenticationTokenHelper")
    public HashTokenHelper authenticationTokenHelper(ClockManager clockManager) {
        return new HashTokenHelper(authenticationSecretKey, authenticationTokenExpiration, clockManager, log);
    }

    @Bean("refreshTokenHelper")
    public HashTokenHelper hashTokenHelper(ClockManager clockManager) {
        return new HashTokenHelper(refreshSecretKey, refreshTokenExpiration, clockManager, log);
    }

    @Bean
    public JwtHelper jwtHelper(ClockManager clockManager) {
        return new JwtHelper(accessSecretKey, jwtExpiration, clockManager, log);
    }
}
