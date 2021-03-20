package com.projectparty.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projectparty.entities.User;
import com.projectparty.entities.UserRoleEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private String password;



    public UserDetailsImpl(int id, String username, String email, String password,
                           UserRoleEnum role) {
        this.password = password;
    }

    public static UserDetailsImpl build(User user) {

        return new UserDetailsImpl(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRole());
    }


    public String getPassword() {
        return null;
    }


    public String getUsername() {
        return null;
    }


    public boolean isAccountNonExpired() {
        return false;
    }


    public boolean isAccountNonLocked() {
        return false;
    }


    public boolean isCredentialsNonExpired() {
        return false;
    }


    public boolean isEnabled() {
        return false;
    }
}