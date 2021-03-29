package com.projectparty.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projectparty.entities.User;
import com.projectparty.entities.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Id;
import java.util.Collection;

@Data
@AllArgsConstructor
public class UserDataImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    @Id
    private int id;

    private String username;

    private String email;
    @JsonIgnore
    private String password;

    private UserRoleEnum role;

    public static UserDataImpl build(User user) {

        return new UserDataImpl(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRole());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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

}