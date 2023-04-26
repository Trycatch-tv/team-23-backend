package com.team23.tickets.Security;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;

import org.json.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {



    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        AuthCredentials authCredentials = new AuthCredentials();
        try{
            authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);

        }catch (IOException e) {
            e.printStackTrace();

        }

        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
                authCredentials.getUsername(),
                authCredentials.getPassword()
                ,authCredentials.getAuthorities()
                );

        return getAuthenticationManager().authenticate(usernamePAT);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        String token = TokenUtils.createToken(userDetails.getNombreCompleto(), userDetails.getUsername(),userDetails.getUsuario().getIdUsuario(), userDetails.getAuthorities());
        JSONObject user = new JSONObject();
        user.put("username", userDetails.getUsername());
        user.put("email", userDetails.getNombreCompleto());
        user.put("roles", userDetails.getAuthorities());
        user.put("token", token);
        response.getWriter().write(user.toString());
        response.addHeader("Authorization", "Bearer "+ token);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }
}
