package com.raihan.springsecurity.entity;

import com.raihan.springsecurity.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name = "user_info")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name", columnDefinition = "VARCHAR(50)")
    private String fullName;

    @Column(name = "username", columnDefinition = "VARCHAR(10)", nullable = false, unique = true)
    private String username;

    @Column(name = "phone_number", columnDefinition = "VARCHAR(11)", unique = true)
    private String phoneNumber;

    @Column(name = "email", columnDefinition = "VARCHAR(50)", nullable = false, unique = true)
    private String email;

    @Column(name = "password", columnDefinition = "VARCHAR(100)", nullable = false)
    private String password;

    @Column(name = "role", columnDefinition = "VARCHAR(100) DEFAULT 'USER'")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));

        authorities.addAll(role.getPermissions().stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.name()))
                .collect(Collectors.toSet())
        );

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
