package com.example.helptek.user;

import com.example.helptek.role.Role;
import com.example.helptek.village.Village;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(name = "uk_user_username", columnNames = { "username" }),
        @UniqueConstraint(name = "uk_user_email", columnNames = { "email" })
})
public class User implements UserDetails {

    @NotBlank
    @Column(name = "email",length = 250, nullable = false,unique = true)
    private String email;

    @NotBlank
    @Column(name = "mobile",length = 10,unique = true)
    private String mobile;

    @NotBlank
    @Column(name = "first_name",length = 50, nullable = false)
    private String firstname;

    @NotBlank
    @Column(name = "last_name",length = 50, nullable = false)
    private String lastname;

    @NotBlank
    @Column(name = "username",length = 50, nullable = false)
    private String username;

    @Column(name = "password",length = 500, nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_deleted", columnDefinition = "tinyint(1) DEFAULT 0" )
    private Byte isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "village_id")
    private Village village;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
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
