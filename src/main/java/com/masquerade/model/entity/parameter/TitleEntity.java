package com.masquerade.model.entity.parameter;

import javax.persistence.*;
import java.util.List;

@Table(name="title")
@Entity
public class TitleEntity {
    @Id
    @Column(nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sect_id")
    private SectEntity sect;

    @ManyToMany
    @JoinTable(
            name = "title_has_status",
            joinColumns = @JoinColumn(name = "title_id"),
            inverseJoinColumns = @JoinColumn(name = "status_id"))
    private List<StatusEntity> status;

    @Column(name = "description_EN")
    private String descriptionEN;
    @Column(name = "description_FR")
    private String descriptionFR;
    @Column(name = "note_EN")
    private String noteEN;
    @Column(name = "note_FR")
    private String noteFR;

    public TitleEntity() {
    }

    public TitleEntity(Long id, SectEntity sect, String descriptionEN, String descriptionFR, String noteEN, String noteFR) {
        this.id = id;
        this.sect = sect;
        this.descriptionEN = descriptionEN;
        this.descriptionFR = descriptionFR;
        this.noteEN = noteEN;
        this.noteFR = noteFR;
    }

    public boolean emptyObjectCheck() {
        return (this.getDescriptionEN() == null &&
                this.getDescriptionFR() == null &&
                this.getNoteEN() == null &&
                this.getNoteFR() == null);
    }

    public List<StatusEntity> getStatus() {
        return status;
    }

    public void setStatus(List<StatusEntity> status) {
        this.status = status;
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

    public void setSect(SectEntity sect) {
        this.sect = sect;
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
