package com.example.hotelmanagementsystem.filter;

import com.example.hotelmanagementsystem.config.JwtTokenProvider;
import com.example.hotelmanagementsystem.exception.InvalidJwtAuthenticationException;
import com.example.hotelmanagementsystem.model.ErrorModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/*
Fahim created at 11/15/2020
*/
public class JwtTokenFilter extends GenericFilterBean {
    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = token != null ? jwtTokenProvider.getAuthentication(token) : null;
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            filterChain.doFilter(req, res);
        } catch (InvalidJwtAuthenticationException ex) {
            ErrorModel errorModel = new ErrorModel(ex.getMessage());

            HttpServletResponse response = (HttpServletResponse)res;
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            OutputStream out = res.getOutputStream();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(out, errorModel);
            out.flush();
        }
    }
}