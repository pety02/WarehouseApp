package com.example.warehouseapp.model.entites;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

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
    @JoinColumn(name = "credentials_id")
    private EmployeeCredentials credentials;

    @ManyToOne(fetch = FetchType.EAGER)
    private EmployeeRole role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    private boolean isActive;
}