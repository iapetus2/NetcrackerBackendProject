package com.projectparty.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @NonNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Id
    private int userId;

    @Column(name = "username")
    @NonNull
    private String username;

    @Column(name = "email")
    @NonNull
    private String email;

    @NonNull
    private String password;

    private long cash;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles;

    @Transient
    private long frozenCash;

    @Transient
    private Map<Integer, Integer> frozenItems;

    @ElementCollection
    @MapKeyColumn(name = "tradingItemId")
    private Map<Integer, Integer> items;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @OneToMany(mappedBy = "user")
    private List<Deal> deals;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
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