package lshh.pollservice.domain.component.auth;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lshh.pollservice.common.lib.JwtHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Map;
import java.util.function.Function;

@RequiredArgsConstructor
@Component
public class AccessJwtManager {

    private final JwtHelper accessJwtHelper;

    public String extractUsername(String jwt) {
        return accessJwtHelper.extractSubject(jwt);
    }
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        return accessJwtHelper.extractClaim(token, claimsResolver);
    }

    private Claims extractAllClaims(String token) {
        return accessJwtHelper.extractAllClaims(token);
    }

    public Key getSignInKey() {
       return accessJwtHelper.getSignInKey();
    }

    public String generateToken(UserDetails userDetails) {
        return accessJwtHelper.generateToken(userDetails.getUsername(), Map.of("pos", "dev"));
    }

    public long getExpirationTime(){
        return accessJwtHelper.getExpirationTime();
    }
}
