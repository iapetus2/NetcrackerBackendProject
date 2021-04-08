package com.projectparty.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int userId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @NotBlank
    private String password;

    @JsonIgnore
    private UserRoleEnum role;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }


    @ElementCollection
    @MapKeyColumn(name = "tradingItemId")
    private Map<Integer, Integer> frozenItems;

    @ElementCollection
    @MapKeyColumn(name = "tradingItemId")
    private Map<Integer, Integer> items;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Deal> deals;

    @Column(name = "cash")
    private long cash;

    @Column(name = "frozen_cash")
    private long frozenCash;

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