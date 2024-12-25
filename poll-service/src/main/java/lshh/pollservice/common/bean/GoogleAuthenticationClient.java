package lshh.pollservice.common.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GoogleAuthenticationClient {
    @Value("${security.google.secret-key}")
    private String secretKey;
    @Value("${security.google.expiration}")
    private Long jwtExpiration;

    // todo feign client
}
