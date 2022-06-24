package com.masquerade.model.DTO.parameter;

import com.masquerade.model.entity.parameter.SectEntity;
import com.masquerade.model.entity.parameter.StatusEntity;
import com.masquerade.model.entity.parameter.StatusTypeEntity;

public class StatusDTO {
    private Long id;

    private SectEntity sect;
    private StatusTypeEntity type;

    private String description_EN;
    private String description_FR;
    private String note_EN;
    private String note_FR;

    public StatusDTO(Long id, SectEntity sect, StatusTypeEntity type, String description_EN, String description_FR, String note_EN, String note_FR) {
        this.id = id;
        this.sect = sect;
        this.type = type;
        this.description_EN = description_EN;
        this.description_FR = description_FR;
        this.note_EN = note_EN;
        this.note_FR = note_FR;
    }

    public StatusDTO(StatusEntity status) {
        this(status.getId(), null, null, status.getDescription_EN(), status.getDescription_FR(), status.getNote_EN(), status.getNote_FR());
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

    public StatusTypeEntity getType() {
        return type;
    }

    public void setType(StatusTypeEntity type) {
        this.type = type;
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
