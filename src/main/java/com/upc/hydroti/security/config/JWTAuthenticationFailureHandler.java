package com.upc.hydroti.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upc.hydroti.common.application.dto.HttpErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final ObjectMapper mapper;

    public JWTAuthenticationFailureHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        HttpErrorInfo httpErrorInfo = new HttpErrorInfo(HttpStatus.UNAUTHORIZED,"Authentication failed: bad credentials.");
        httpServletResponse.setStatus(401);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().print(mapper.writeValueAsString(httpErrorInfo));
        httpServletResponse.getWriter().flush();

    }
}
