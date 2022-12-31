package ru.halavandala.jwtauthentication.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "Users")
@Data
public class User extends BaseEntity{

    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password_hash")
    private String passwordHash;
    @Column(name = "username", unique = true)
    private String username;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private List<Role> roles;

}
