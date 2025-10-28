package com.example.warehouseapp.model.entites;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;
    private String name;
    private String surname;
    private String uidNo;
    private LocalDate hireDate;
    private LocalDate fireDate;
    private String createdBy;
    private String updatedBy;
    private Instant createdAt;
    private Instant updatedAt;
    @OneToOne(fetch = FetchType.LAZY)
    private EmployeeCredentials credentials;
    @ManyToOne(fetch = FetchType.EAGER)
    private EmployeeRole role;
    @ManyToOne(fetch = FetchType.EAGER)
    private Location location;
}