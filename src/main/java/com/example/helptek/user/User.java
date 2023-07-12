package com.example.helptek.user;

import com.example.helptek.role.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(name = "uk_user_username", columnNames = { "username" }),
        @UniqueConstraint(name = "uk_user_email", columnNames = { "email" }) ,@UniqueConstraint(columnNames = { "code" })
})
public class User {

    @Column(name = "email",length = 250, nullable = false,unique = true)
    private String email;

    @Column(name = "mobile",length = 10, nullable = false,unique = true)
    private String mobile;

    @Column(name = "first_name",length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name",length = 50, nullable = false)
    private String lastName;

    @Column(name = "username",length = 50, nullable = false)
    private String username;

    @Column(name = "password",length = 50, nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



}
