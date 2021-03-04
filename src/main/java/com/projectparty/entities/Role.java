package com.projectparty.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "userRoles")
public class Role implements GrantedAuthority {

    @Id
    private Long id;

    private UserRoleEnum roleName;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(Long id, UserRoleEnum name) {
        this.id = id;
        this.roleName = name;
    }

    @Override
    public String getAuthority() {
        return getRoleName().toString();
    }
}