package com.upc.hydroti.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.upc.hydroti.security.config.Constants.*;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private final UserDetailsService userDetailsService;

    public AuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(AUTHORIZATION);

        if (Objects.isNull(header) || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        Optional<UsernamePasswordAuthenticationToken> authentication = getAuthentication(req);
        authentication.ifPresentOrElse(e -> SecurityContextHolder.getContext().setAuthentication(e),
                SecurityContextHolder::clearContext);
        chain.doFilter(req, res);
    }

    private Optional<UsernamePasswordAuthenticationToken> getAuthentication(HttpServletRequest request) {

        String token = request.getHeader(AUTHORIZATION);

        if (Objects.nonNull(token)) {
            Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();

            String subject = claims.getSubject();
            List<String> authorities = (List<String>) claims.get(ROLE_CLAIM);
            if (Objects.nonNull(subject)) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
                if (subject.equals(userDetails.getUsername()) && claims.getExpiration().before(new Date())){
                    return Optional.of(new UsernamePasswordAuthenticationToken(
                            subject,
                            null,
                            Objects.nonNull(authorities)?authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()) : Collections.emptyList()));
                }
            }
        }

        return Optional.empty();
    }

}
