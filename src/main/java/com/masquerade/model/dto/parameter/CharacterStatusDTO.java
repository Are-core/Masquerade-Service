package com.masquerade.model.dto.parameter;

import java.util.List;

public class CharacterStatusDTO {
    private Long id;
    private String name;
    private List<DeclaredStatusDTO> status;

    public CharacterStatusDTO() {
    }

    public CharacterStatusDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CharacterStatusDTO(Long id, String name, List<DeclaredStatusDTO> status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DeclaredStatusDTO> getStatus() {
        return status;
    }

    public void setStatus(List<DeclaredStatusDTO> status) {
        this.status = status;
    }
}
