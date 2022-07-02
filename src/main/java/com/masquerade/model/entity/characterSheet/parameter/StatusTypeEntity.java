package com.masquerade.model.entity.characterSheet.parameter;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Table(name="status_type")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class StatusTypeEntity {
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

    public StatusTypeEntity() {}

    public StatusTypeEntity(String descriptionEN, String descriptionFR, String noteEN, String noteFR) {
        this.descriptionEN = descriptionEN;
        this.descriptionFR = descriptionFR;
        this.noteEN = noteEN;
        this.noteFR = noteFR;
    }

    public StatusTypeEntity(Long id, String descriptionEN, String descriptionFR, String noteEN, String noteFR) {
        this(descriptionEN, descriptionFR, noteEN, noteFR);
        this.id = id;
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

    public String getDescriptionEN() {
        return descriptionEN;
    }

    public void setDescriptionEN(String descriptionEN) {
        this.descriptionEN = descriptionEN;
    }

    public String getDescriptionFR() {
        return descriptionFR;
    }

    public void setDescriptionFR(String descriptionFR) {
        this.descriptionFR = descriptionFR;
    }

    public String getNoteEN() {
        return noteEN;
    }

    public void setNoteEN(String noteEN) {
        this.noteEN = noteEN;
    }

    public String getNoteFR() {
        return noteFR;
    }

    public void setNoteFR(String noteFR) {
        this.noteFR = noteFR;
    }
}
