package com.upc.hydroti.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upc.hydroti.security.application.dto.SignInRequest;
import com.upc.hydroti.security.application.dto.SignInResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.server.MethodNotAllowedException;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.upc.hydroti.security.config.Constants.*;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final ObjectMapper mapper;

    public AuthenticationFilter(AuthenticationManager authenticationManager, ObjectMapper mapper) {
        super.setFilterProcessesUrl(TOKEN_URL);

        this.authenticationManager = authenticationManager;
        this.mapper = mapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        if (!req.getMethod().equals(HttpMethod.POST.name())) {
            throw new MethodNotAllowedException(req.getMethod(), List.of(HttpMethod.POST));
        }

        try (InputStream is = req.getInputStream()) {
            SignInRequest user = new ObjectMapper().readValue(is, SignInRequest.class);

            return authenticationManager.authenticate(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                         Authentication auth) throws IOException {
        User principal = (User) auth.getPrincipal();
        Map<String, Object> claims = Map.of(ROLE_CLAIM,
                principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(principal.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        SignInResponse signInResponse = new SignInResponse(principal.getUsername(), token);

        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        res.setCharacterEncoding("UTF-8");
        res.getWriter().print(mapper.writeValueAsString(signInResponse));
        res.getWriter().flush();
    }
}
