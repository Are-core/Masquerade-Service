package com.masquerade.model;

import javax.persistence.*;

@Table(name="archetype")
@Entity
public class ArchetypeEntity {
    @Id
    @Column(nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    public ArchetypeEntity() {
    }

    public ArchetypeEntity(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }
}
