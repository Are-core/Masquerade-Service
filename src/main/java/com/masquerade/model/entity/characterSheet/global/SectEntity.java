package com.masquerade.model.entity.characterSheet.global;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.*;

@Table(name = "sect")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class SectEntity {
    @Id
    @Column(nullable = false, unique = true, updatable = false, name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    public SectEntity() {
    }

    public SectEntity(String description) {
        this.description = description;
    }

    public SectEntity(Long id, String description) {
        this(description);
        this.id = id;
    }

    public boolean emptyObjectCheck() {
        return (this.getId() == null && this.getDescription() == null);
    }

    public boolean isUpdatable() {
        return this.id != null && this.id > 0L;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
