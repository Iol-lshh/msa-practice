package lshh.pollservice.common.bean;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lshh.auth.exception.AccessTokenException;
import lshh.core.lib.util.TraceThreadManager;
import lshh.pollservice.domain.AuthService;
import lshh.pollservice.domain.component.auth.AccessJwtManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationProvideFilter extends OncePerRequestFilter {
    private final AuthService service;
    private final AccessJwtManager jwtManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        try {
            String username = extractUserName(request);
            UserDetails userDetails = service.loadUserByUsername(username);
            confirm(filterChain, request, response, userDetails);
            return;
        } catch (AccessTokenException e){
            log.warn("AccessTokenException: {}", e.getMessage());
        } catch (SignatureException e) {
            log.warn("SignatureException: {}", e.getMessage());
        } catch (MalformedJwtException e){
            log.warn("MalformedJwtException: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.warn("ExpiredJwtException: {}", e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    private String extractUserName(HttpServletRequest request) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        log.info("authHeader : {}", authHeader);
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            throw new AccessTokenException("No authHeader");
        }
        final String jwt = authHeader.replaceFirst("Bearer ", "");
        final String username = jwtManager.extractUsername(jwt);
        if(username == null){
            throw new AccessTokenException("Fail to extract username");
        }
        return username;
    }

    private void confirm(FilterChain filterChain, HttpServletRequest request, HttpServletResponse response, UserDetails userDetails) throws ServletException, IOException {
        log.info("getUsername : " + userDetails.getUsername());
        TraceThreadManager.threadUserId(userDetails.getUsername());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        log.info("SUCCESS - access token");
        filterChain.doFilter(request, response);
    }
}

