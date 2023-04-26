package com.team23.tickets.Security;

import com.team23.tickets.DTO.GenericResponseDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.logging.Logger;

@Slf4j
@Component
public class TokenUtils {

    private final static String ACCESS_TOKEN_SECRET = "JVBERi0xLjcNCiW1tbW1DQoxIDAgb2JqDQo8PC";
    private final static long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;

    public static String createToken(String nombre, String email, Integer idUsuario, Collection authorities){
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS*1_000l;
        Date expirationDate = new Date(System.currentTimeMillis()*expirationTime);

        Map<String, Object> extra = new HashMap<>();
        extra.put("nombre", nombre);
        extra.put("user", idUsuario);
        extra.put("authorities", authorities);

         return Jwts.builder()
                .setSubject(email)
                 .addClaims(extra)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, ACCESS_TOKEN_SECRET.getBytes())
                .compact();

    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token){

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
            String email = claims.getSubject();
            List<?> objetoMapa = (List<?>) claims.get("authorities");
            Map<String, String> objetoMapa2 = (HashMap<String, String>) objetoMapa.get(0);
            String authorities = objetoMapa2.get("authority");
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(authorities));

            return new UsernamePasswordAuthenticationToken(email, null, grantedAuthorities);

        }catch (JwtException j){
            Logger.getLogger(j.getMessage());
            log.error(j.getMessage());
            return null;

        }

    }

}
