package com.masquerade.model.parameter;

import javax.persistence.*;

@Table(name="sect")
@Entity
public class SectEntity {
    @Id
    @Column(nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    public SectEntity() {
    }

    public SectEntity(String description) { this.description = description; }

    public boolean isNull() {
        return (this.getId() == null && this.getDescription() == null);
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
