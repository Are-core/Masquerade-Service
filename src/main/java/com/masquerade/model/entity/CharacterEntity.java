package com.masquerade.model.entity;

import javax.persistence.*;

@Table(name="character_sheet")
@Entity
public class CharacterEntity {
    @Id
    @Column(nullable = false, unique = true, updatable = false, name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "npc")
    private Boolean npc;
    @Column(name = "archived")
    private Boolean archived;
    @Column(name = "player")
    private String player;
    @Column(name = "name")
    private String name;

    @Column(name = "physical")
    private Integer physical;
    @Column(name = "physicalstrength")
    private Boolean physicalstrength;
    @Column(name = "physicaldexterity")
    private Boolean physicaldexterity;
    @Column(name = "physicalstamina")
    private Boolean physicalstamina;

    @Column(name = "social")
    private Integer social;
    @Column(name = "socialcharisma")
    private Boolean socialcharisma;
    @Column(name = "socialmanipulation")
    private Boolean socialmanipulation;
    @Column(name = "socialappearance")
    private Boolean socialappearance;

    @Column(name = "mental")
    private Integer mental;
    @Column(name = "mentalperception")
    private Boolean mentalperception;
    @Column(name = "mentalintelligence")
    private Boolean mentalintelligence;
    @Column(name = "mentalwits")
    private Boolean mentalwits;

    @Column(name = "generation")
    private Integer generation;
    @Column(name = "blood")
    private Integer blood;
    @Column(name = "willpower")
    private Integer willpower;

    @Column(name = "morality")
    private Integer morality;
    @Column(name = "morality_merit")
    private Integer morality_merit;

    @Column(name = "healthy")
    private Integer healthy;
    @Column(name = "injured")
    private Integer injured;
    @Column(name = "incapacitated")
    private Integer incapacitated;
    @Column(name = "beast")
    private Integer beast;
    @Column(name = "madness")
    private Integer madness;

    @Column(name = "note")
    private String note;

    @Column(name = "archetype_id")
    private Integer archetype_id;
    @Column(name = "bloodline_id")
    private Integer bloodline_id;
    @Column(name = "sect_id")
    private Integer sect_id;
    @Column(name = "clan_id")
    private Integer clan_id;

    public CharacterEntity() {}

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

    public Integer getPhysical() {
        return physical;
    }

    public void setPhysical(Integer physical) {
        this.physical = physical;
    }

    public Boolean getPhysicalstrength() {
        return physicalstrength;
    }

    public void setPhysicalstrength(Boolean physicalstrength) {
        this.physicalstrength = physicalstrength;
    }

    public Boolean getPhysicaldexterity() {
        return physicaldexterity;
    }

    public void setPhysicaldexterity(Boolean physicaldexterity) {
        this.physicaldexterity = physicaldexterity;
    }

    public Boolean getPhysicalstamina() {
        return physicalstamina;
    }

    public void setPhysicalstamina(Boolean physicalstamina) {
        this.physicalstamina = physicalstamina;
    }

    public Integer getSocial() {
        return social;
    }

    public void setSocial(Integer social) {
        this.social = social;
    }

    public Boolean getSocialcharisma() {
        return socialcharisma;
    }

    public void setSocialcharisma(Boolean socialcharisma) {
        this.socialcharisma = socialcharisma;
    }

    public Boolean getSocialmanipulation() {
        return socialmanipulation;
    }

    public void setSocialmanipulation(Boolean socialmanipulation) {
        this.socialmanipulation = socialmanipulation;
    }

    public Boolean getSocialappearance() {
        return socialappearance;
    }

    public void setSocialappearance(Boolean socialappearance) {
        this.socialappearance = socialappearance;
    }

    public Integer getMental() {
        return mental;
    }

    public void setMental(Integer mental) {
        this.mental = mental;
    }

    public Boolean getMentalperception() {
        return mentalperception;
    }

    public void setMentalperception(Boolean mentalperception) {
        this.mentalperception = mentalperception;
    }

    public Boolean getMentalintelligence() {
        return mentalintelligence;
    }

    public void setMentalintelligence(Boolean mentalintelligence) {
        this.mentalintelligence = mentalintelligence;
    }

    public Boolean getMentalwits() {
        return mentalwits;
    }

    public void setMentalwits(Boolean mentalwits) {
        this.mentalwits = mentalwits;
    }

    public Integer getGeneration() {
        return generation;
    }

    public void setGeneration(Integer generation) {
        this.generation = generation;
    }

    public Integer getBlood() {
        return blood;
    }

    public void setBlood(Integer blood) {
        this.blood = blood;
    }

    public Integer getWillpower() {
        return willpower;
    }

    public void setWillpower(Integer willpower) {
        this.willpower = willpower;
    }

    public Integer getMorality() {
        return morality;
    }

    public void setMorality(Integer morality) {
        this.morality = morality;
    }

    public Integer getMorality_merit() {
        return morality_merit;
    }

    public void setMorality_merit(Integer morality_merit) {
        this.morality_merit = morality_merit;
    }

    public Integer getHealthy() {
        return healthy;
    }

    public void setHealthy(Integer healthy) {
        this.healthy = healthy;
    }

    public Integer getInjured() {
        return injured;
    }

    public void setInjured(Integer injured) {
        this.injured = injured;
    }

    public Integer getIncapacitated() {
        return incapacitated;
    }

    public void setIncapacitated(Integer incapacitated) {
        this.incapacitated = incapacitated;
    }

    public Integer getBeast() {
        return beast;
    }

    public void setBeast(Integer beast) {
        this.beast = beast;
    }

    public Integer getMadness() {
        return madness;
    }

    public void setMadness(Integer madness) {
        this.madness = madness;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getArchetype_id() {
        return archetype_id;
    }

    public void setArchetype_id(Integer archetype_id) {
        this.archetype_id = archetype_id;
    }

    public Integer getBloodline_id() {
        return bloodline_id;
    }

    public void setBloodline_id(Integer bloodline_id) {
        this.bloodline_id = bloodline_id;
    }

    public Integer getSect_id() {
        return sect_id;
    }

    public void setSect_id(Integer sect_id) {
        this.sect_id = sect_id;
    }

    public Integer getClan_id() {
        return clan_id;
    }

    public void setClan_id(Integer clan_id) {
        this.clan_id = clan_id;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }
}
