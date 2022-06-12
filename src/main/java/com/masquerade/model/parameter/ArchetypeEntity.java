package com.masquerade.model.parameter;

import javax.persistence.*;

@Table(name="archetype")
@Entity
public class ArchetypeEntity {
    @Id
    @Column(nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description_EN;
    private String description_FR;
    private String note_EN;
    private String note_FR;

    public ArchetypeEntity() {
    }

    public ArchetypeEntity(String descriptionEN, String descriptionFR, String noteEN, String noteFR) {
        this.description_EN = descriptionEN;
        this.description_FR = descriptionFR;
        this.note_EN = noteEN;
        this.note_FR = noteFR;
    }

    public boolean emptyObjectCheck() {
        return (this.getDescriptionEN() == null &&
                this.getDescriptionFR() == null &&
                this.getNoteEN() == null &&
                this.getNoteFR() == null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescriptionEN() { return description_EN; }

    public void setDescriptionEN(String description) {
        this.description_EN = description;
    }

    public String getDescriptionFR() { return description_FR; }

    public void setDescriptionFR(String description) {
        this.description_FR = description;
    }

    public String getNoteEN() { return note_EN; }

    public void setNoteEN(String note) {
        this.note_EN = note;
    }

    public String getNoteFR() { return note_FR; }

    public void setNoteFR(String note) {
        this.note_FR = note;
    }
}
