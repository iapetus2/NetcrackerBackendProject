package com.projectparty.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "userRoles")
public class Role{

    @Id
    private Long id;

    private RoleType roleName;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(Long id, RoleType name) {
        this.id = id;
        this.roleName = name;
    }

}