package com.masquerade.model.entity.characterSheet.global;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Table(name="archetype")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class ArchetypeEntity {
    @Id
    @Column(nullable = false, unique = true, updatable = false, name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description_EN")
    private String descriptionEN;
    @Column(name = "description_FR")
    private String descriptionFR;
    @Column(name = "note_EN")
    private String noteEN;
    @Column(name = "note_FR")
    private String noteFR;

    public ArchetypeEntity() {
    }

    public ArchetypeEntity(String descriptionEN, String descriptionFR, String noteEN, String noteFR) {
        this.descriptionEN = descriptionEN;
        this.descriptionFR = descriptionFR;
        this.noteEN = noteEN;
        this.noteFR = noteFR;
    }

    public ArchetypeEntity(Long id, String descriptionEN, String descriptionFR, String noteEN, String noteFR) {
        this(descriptionEN, descriptionFR, noteEN, noteFR);
        this.id = id;
    }

    public boolean emptyObjectCheck() {
        return (this.getDescriptionEN() == null &&
                this.getDescriptionFR() == null &&
                this.getNoteEN() == null &&
                this.getNoteFR() == null);
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

    public String getDescriptionEN() { return descriptionEN; }

    public void setDescriptionEN(String description) {
        this.descriptionEN = description;
    }

    public String getDescriptionFR() { return descriptionFR; }

    public void setDescriptionFR(String description) {
        this.descriptionFR = description;
    }

    public String getNoteEN() { return noteEN; }

    public void setNoteEN(String note) {
        this.noteEN = note;
    }

    public String getNoteFR() { return noteFR; }

    public void setNoteFR(String note) {
        this.noteFR = note;
    }
}
