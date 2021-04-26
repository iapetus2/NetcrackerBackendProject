package com.projectparty.security.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger logger = Logger.getLogger(AuthEntryPointJwt.class.getName());

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        logger.log(Level.SEVERE, "Unauthorized effort to get access: ", authException.getStackTrace());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized effort to get access");
    }

}
