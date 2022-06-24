package com.masquerade.model.entity.parameter;

import javax.persistence.*;

@Table(name="clan")
@Entity
public class ClanEntity {
    @Id
    @Column(nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description_EN;
    private String description_FR;
    private String note_EN;
    private String note_FR;

    public ClanEntity() {}

    public ClanEntity(String descriptionEN, String descriptionFR, String noteEN, String noteFR) {
        this.description_EN = descriptionEN;
        this.description_FR = descriptionFR;
        this.note_EN = noteEN;
        this.note_FR = noteFR;
    }

    public boolean emptyObjectCheck() {
        return (this.getDescription_EN() == null &&
                this.getDescription_FR() == null &&
                this.getNote_EN() == null &&
                this.getNote_FR() == null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription_EN() {
        return description_EN;
    }

    public void setDescription_EN(String description_EN) {
        this.description_EN = description_EN;
    }

    public String getDescription_FR() {
        return description_FR;
    }

    public void setDescription_FR(String description_FR) {
        this.description_FR = description_FR;
    }

    public String getNote_EN() {
        return note_EN;
    }

    public void setNote_EN(String note_EN) {
        this.note_EN = note_EN;
    }

    public String getNote_FR() {
        return note_FR;
    }

    public void setNote_FR(String note_FR) {
        this.note_FR = note_FR;
    }
}
