package com.masquerade.model.entity.parameter;

import javax.persistence.*;

@Table(name="status")
@Entity
public class StatusEntity {

    @Id
    @Column(nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sect_id;
    private Long type_id;

    private String description_EN;
    private String description_FR;
    private String note_EN;
    private String note_FR;

    public StatusEntity() {}

    public StatusEntity(Long id, Long sect_id, Long type_id, String description_EN, String description_FR, String note_EN, String note_FR) {
        this.id = id;
        this.sect_id = sect_id;
        this.type_id = type_id;
        this.description_EN = description_EN;
        this.description_FR = description_FR;
        this.note_EN = note_EN;
        this.note_FR = note_FR;
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

    public Long getSect_id() {
        return sect_id;
    }

    public void setSect_id(Long sect_id) {
        this.sect_id = sect_id;
    }

    public Long getType_id() {
        return type_id;
    }

    public void setType_id(Long type_id) {
        this.type_id = type_id;
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
