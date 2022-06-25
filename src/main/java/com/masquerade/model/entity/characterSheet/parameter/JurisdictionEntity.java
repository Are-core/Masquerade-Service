package com.masquerade.model.entity.characterSheet.parameter;

import javax.persistence.*;

@Table(name="jurisdiction")
@Entity
public class JurisdictionEntity {
    @Id
    @Column(nullable = false, unique = true, updatable = false, name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;


    public JurisdictionEntity() {}

    public JurisdictionEntity(String description) {
        this.description = description;
    }

    public boolean emptyObjectCheck() {
        return (this.getDescription() == null);
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
