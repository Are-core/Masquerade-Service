package com.masquerade.model;

import javax.persistence.*;

@Table(name="character_sheet")
@Entity
public class SimpleCharacterEntity {
    @Id
    @Column(nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean npc;
    private String player;
    private String name;

    public SimpleCharacterEntity() {}

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
}
