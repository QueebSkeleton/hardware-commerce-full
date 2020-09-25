package com.github.queebskeleton.hardwarecommerce.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        String role =
            authentication.getAuthorities()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid authentication object"))
                    .getAuthority();

        SavedRequest savedRequest =
            new HttpSessionRequestCache().getRequest(request, response);

        if(role.contentEquals("ROLE_ADMINISTRATOR")) {
            if(savedRequest != null && savedRequest.getRedirectUrl() != null)
                response.sendRedirect(savedRequest.getRedirectUrl());

            else
                response.sendRedirect("/admin/dashboard");
        }

        else if(role.contentEquals("ROLE_CUSTOMER")){
            if(savedRequest != null && savedRequest.getRedirectUrl() != null)
                response.sendRedirect(savedRequest.getRedirectUrl());

            else
                response.sendRedirect("/");
        }
        
    }
    
}