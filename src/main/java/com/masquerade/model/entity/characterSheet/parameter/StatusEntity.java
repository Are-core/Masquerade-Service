package com.masquerade.model.entity.characterSheet.parameter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.masquerade.model.entity.characterSheet.global.SectEntity;

import javax.persistence.*;

@Table(name = "status")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class StatusEntity {

    @Id
    @Column(nullable = false, unique = true, updatable = false, name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sect_id")
    private SectEntity sect;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private StatusTypeEntity statusType;

    @Column(name = "description_EN")
    private String descriptionEN;
    @Column(name = "description_FR")
    private String descriptionFR;
    @Column(name = "note_EN")
    private String noteEN;
    @Column(name = "note_FR")
    private String noteFR;

    public StatusEntity() {
    }

    public StatusEntity(SectEntity sect, StatusTypeEntity statusType, String descriptionEN, String descriptionFR, String noteEN, String noteFR) {
        this.sect = sect;
        this.statusType = statusType;
        this.descriptionEN = descriptionEN;
        this.descriptionFR = descriptionFR;
        this.noteEN = noteEN;
        this.noteFR = noteFR;
    }

    public StatusEntity(Long id, SectEntity sect, StatusTypeEntity statusType, String descriptionEN, String descriptionFR, String noteEN, String noteFR) {
        this(sect, statusType, descriptionEN, descriptionFR, noteEN, noteFR);
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

    public SectEntity getSect() {
        return sect;
    }

    public void setSect(SectEntity sect_id) {
        this.sect = sect_id;
    }

    public StatusTypeEntity getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusTypeEntity statusType) {
        this.statusType = statusType;
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
