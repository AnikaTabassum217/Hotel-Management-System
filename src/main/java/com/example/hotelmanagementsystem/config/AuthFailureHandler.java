package com.example.hotelmanagementsystem.config;

import com.example.hotelmanagementsystem.model.ErrorModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 Fahim created at 11/15/2020
 */
public class AuthFailureHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException ex) throws IOException, ServletException {
        ErrorModel errorModel = new ErrorModel(ex.getMessage());

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, errorModel);
        out.flush();
    }
}
