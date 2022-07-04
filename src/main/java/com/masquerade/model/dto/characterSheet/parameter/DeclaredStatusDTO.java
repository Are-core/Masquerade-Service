package com.masquerade.model.dto.characterSheet.parameter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.masquerade.model.entity.characterSheet.global.SectEntity;
import com.masquerade.model.entity.characterSheet.parameter.StatusEntity;
import com.masquerade.model.entity.characterSheet.parameter.StatusTypeEntity;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeclaredStatusDTO extends StatusEntity {
    //TODO mettre en place un dto pour le personnage
    private Long entity;

    public DeclaredStatusDTO(Long entity) {
        this.entity = entity;
    }

    public DeclaredStatusDTO(Long id, SectEntity sect, StatusTypeEntity statusType, String descriptionEN, String descriptionFR, String noteEN, String noteFR, Long entity) {
        super(id, sect, statusType, descriptionEN, descriptionFR, noteEN, noteFR);
        this.entity = entity;
    }

    public DeclaredStatusDTO(StatusEntity status, Long entity) {
        super(status.getId(), status.getSect(), status.getStatusType(), status.getDescriptionEN(), status.getDescriptionFR(), status.getNoteEN(), status.getNoteFR());
        this.entity = entity;
    }

    public Long getEntity() {
        return entity;
    }

    public void setEntity(Long entity) {
        this.entity = entity;
    }
}
