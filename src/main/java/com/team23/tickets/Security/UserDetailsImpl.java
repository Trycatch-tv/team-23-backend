package com.team23.tickets.Security;

import com.team23.tickets.Entities.Usuario;
import com.team23.tickets.Enum.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final Usuario usuario;
    private List<SimpleGrantedAuthority> authorities;


    UserDetailsImpl(Usuario usuario){
        this.usuario = usuario;
        this.authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(usuario.getRol().name()));
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  this.authorities;
    }

    @Override
    public String getPassword() {
        return this.usuario.getPassword();
    }



    public  String getUsername() {
        return this.usuario.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getNombreCompleto(){
        return  this.usuario.getNombres()+" "+this.usuario.getApellidos();
    }

}
