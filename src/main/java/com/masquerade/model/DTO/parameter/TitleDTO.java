package com.masquerade.model.DTO.parameter;

import com.masquerade.model.entity.parameter.SectEntity;
import com.masquerade.model.entity.parameter.StatusEntity;
import com.masquerade.model.entity.parameter.TitleEntity;

import java.util.List;

public class TitleDTO {
    private Long id;

    private SectEntity sect;

    private List<StatusEntity> status;

    private String description_EN;
    private String description_FR;
    private String note_EN;
    private String note_FR;

    public TitleDTO() {}

    public TitleDTO(Long id, SectEntity sect, String description_EN, String description_FR, String note_EN, String note_FR, List<StatusEntity> status) {
        this.id = id;
        this.sect = sect;
        this.description_EN = description_EN;
        this.description_FR = description_FR;
        this.note_EN = note_EN;
        this.note_FR = note_FR;
        this.status = status;
    }

    public TitleDTO(TitleEntity title) {
        this(title.getId(), null, title.getDescription_EN(), title.getDescription_FR(), title.getNote_EN(), title.getNote_FR(), title.getStatus());
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

    public List<StatusEntity> getStatus() {
        return status;
    }

    public void setStatus(List<StatusEntity> status) {
        this.status = status;
    }
}
