package com.team23.tickets.Security;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.team23.tickets.DTO.UsuarioDTO;
import lombok.SneakyThrows;

import org.hibernate.service.spi.InjectService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    public JWTAuthenticationFilter() {
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        AuthCredentials authCredentials = new AuthCredentials();
        try{
            authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);

            UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
                    authCredentials.getUsername(),
                    authCredentials.getPassword()
                    ,authCredentials.getAuthorities()
            );

            return getAuthenticationManager().authenticate(usernamePAT);

        }catch (UsernameNotFoundException ex) {
            System.out.println(ex.getMessage());
            return getAuthenticationManager().authenticate(null);

        }


    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        String token = TokenUtils.createToken(userDetails.getNombreCompleto(), userDetails.getUsername(),userDetails.getUsuario().getIdUsuario(), userDetails.getAuthorities());
        JSONObject user = new JSONObject();
        user.put("username", userDetails.getUsername());
        user.put("nombre", userDetails.getNombreCompleto());
       //user.put("roles", userDetails.getAuthorities());
        user.put("usuario", userDetails.getUsuario().getIdUsuario());
        user.put("nombres", userDetails.getUsuario().getNombres());
        user.put("apellidos", userDetails.getUsuario().getApellidos());
        user.put("telefono", userDetails.getUsuario().getTelefono());
        user.put("rol", userDetails.getUsuario().getRol());
        user.put("token", token);
        response.getWriter().write(user.toString());
        //response.addHeader("Authorization", "Bearer "+ token);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }
}
