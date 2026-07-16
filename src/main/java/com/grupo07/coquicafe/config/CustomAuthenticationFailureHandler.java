package com.grupo07.coquicafe.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;

public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                         AuthenticationException exception) throws IOException, ServletException {

        String redirectUrl;
        if (exception instanceof DisabledException || exception instanceof UsernameNotFoundException) {
            redirectUrl = "/login?inactivo";
        } else {
            redirectUrl = "/login?error";
        }

        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}