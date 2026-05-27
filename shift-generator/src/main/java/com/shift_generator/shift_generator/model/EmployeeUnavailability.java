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
@Table(name = "employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeUnavailability {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="date", nullable = false)
    private String date;

    @Column(name="reason", nullable = true)
    private String reason;

    
}