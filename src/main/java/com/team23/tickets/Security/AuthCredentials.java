package com.team23.tickets.Security;

import com.team23.tickets.Enum.Roles;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

@Data
public class AuthCredentials {
    private String username;
    private String password;
    private List<SimpleGrantedAuthority> authorities;


}
