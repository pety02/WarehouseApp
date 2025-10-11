package com.example.warehouseapp.model;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Address address;
    @OneToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;
    @OneToMany(mappedBy = "location")
    private List<Employee> employees;
    @OneToMany
    private List<EmployeeRole> roles;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}