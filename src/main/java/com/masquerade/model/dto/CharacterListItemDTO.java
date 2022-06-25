package com.masquerade.model.dto;

import com.masquerade.model.entity.CharacterEntity;

public class CharacterListItemDTO {
    private Long id;
    private Boolean npc;
    private Boolean archived;
    private String player;
    private String name;

    public CharacterListItemDTO() {}

    public CharacterListItemDTO(CharacterEntity entity) {
        this.id = entity.getId();
        this.npc = entity.getNpc();
        this.archived = entity.getArchived();
        this.player = entity.getPlayer();
        this.name = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getNpc() {
        return npc;
    }

    public void setNpc(Boolean npc) {
        this.npc = npc;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }
}
