package com.projectparty.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "userRoles")
public class Role {

    @Id
    private int id;

    @NonNull
    private RoleType type;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(int id, RoleType name) {
        this.id = id;
        this.type = name;
    }

}