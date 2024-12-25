package lshh.auth.lib.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;

import java.security.Key;
import java.time.Clock;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public class JwtHelper {

    private final String secretKey;
    private final Long expiration;
    private final ClockManager clockManager;
    private final Logger log;

    public JwtHelper(String secretKey, Long expiration, ClockManager clockManager, Logger log) {
        this.secretKey = secretKey;
        this.expiration = expiration;
        this.clockManager = clockManager;
        this.log = log;
    }

    public String extractSubject(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);

        if(!claims.isEmpty())
            return claimsResolver.apply(claims);
        else
            return null;
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String subject, Map<String, Object> claims) {
        var result = buildToken(subject, claims, expiration);
        log.info("generateToken: {}", result);
        return result;
    }

    private String buildToken(String subject, Map<String, Object> extraClaims, long jwtExpiration) {
        Clock clock = clockManager.getClock();
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(subject)
                .setIssuedAt(new Date(clock.millis()))
                .setExpiration(new Date(clock.millis() + jwtExpiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public long getExpirationTime(){
        return this.expiration;
    }
}
