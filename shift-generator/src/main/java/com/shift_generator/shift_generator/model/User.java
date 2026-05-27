package com.shift_generator.shift_generator.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;



@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserRole userRole;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public enum UserRole {
        USER, ADMIN
    }

    // Interface implementations
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Maps role following AOP standards
        return List.of(new SimpleGrantedAuthority("ROLE_" + userRole.name()));
    }

    @Override public boolean isAccountNonExpired()      { return true; }
    @Override public boolean isAccountNonLocked()       { return true; }
    @Override public boolean isCredentialsNonExpired()  { return true; }
    @Override public boolean isEnabled()                { return true; }
}
