package lshh.auth.lib.util;

import lshh.auth.exception.AuthenticationException;
import org.slf4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Clock;


public class HashTokenHelper {
    private final String secretKey;
    private final Long expiration;
    private final ClockManager clockManager;
    private final Logger log;

    public HashTokenHelper(String secretKey, Long expiration, ClockManager clockManager, Logger log) {
        this.secretKey = secretKey;
        this.expiration = expiration;
        this.clockManager = clockManager;
        this.log = log;
    }

    public String generateToken(UserDetails userDetails){
        Clock clock =  clockManager.getClock();
        try{
            return encrypt(userDetails.getUsername() + secretKey +  clock.instant());
        } catch (NoSuchAlgorithmException e){
            log.error("NoSuchAlgorithmException: {}", e.getMessage());
            throw new AuthenticationException("NoSuchAlgorithmException: " + e.getMessage());
        }
    }

    String encrypt(String token) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(token.getBytes());
        return bytesToHex(messageDigest.digest());
    }

    String bytesToHex(byte[] bytes){
        StringBuilder builder = new StringBuilder();
        for(byte b : bytes){
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    public long getExpirationTime(){
        return this.expiration;
    }
}
