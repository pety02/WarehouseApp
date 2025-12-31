package com.example.warehouseapp.model.entites;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, length = 100)
    private String name;
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb", nullable = false)
    private Address address;
    private String createdBy;
    private String updatedBy;
    private Instant createdAt;
    private Instant updatedAt;
    @OneToOne(fetch = FetchType.LAZY)
    private Employee manager;
    @OneToMany
    private List<WarehouseZone> warehouseZones;
}
