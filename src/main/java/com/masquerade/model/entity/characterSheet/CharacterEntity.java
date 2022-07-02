package com.masquerade.model.entity.characterSheet;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.masquerade.model.entity.characterSheet.global.ArchetypeEntity;
import com.masquerade.model.entity.characterSheet.global.ClanEntity;
import com.masquerade.model.entity.characterSheet.global.SectEntity;
import com.masquerade.model.entity.characterSheet.parameter.JurisdictionEntity;

import javax.persistence.*;

@Table(name="character_sheet")
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    private Boolean physicalStrength;
    @Column(name = "physicaldexterity")
    private Boolean physicalDexterity;
    @Column(name = "physicalstamina")
    private Boolean physicalStamina;

    @Column(name = "social")
    private Integer social;
    @Column(name = "socialcharisma")
    private Boolean socialCharisma;
    @Column(name = "socialmanipulation")
    private Boolean socialManipulation;
    @Column(name = "socialappearance")
    private Boolean socialAppearance;

    @Column(name = "mental")
    private Integer mental;
    @Column(name = "mentalperception")
    private Boolean mentalPerception;
    @Column(name = "mentalintelligence")
    private Boolean mentalIntelligence;
    @Column(name = "mentalwits")
    private Boolean mentalWits;

    @Column(name = "generation")
    private Integer generation;
    @Column(name = "blood")
    private Integer blood;
    @Column(name = "willpower")
    private Integer willpower;

    @Column(name = "morality")
    private Integer morality;
    @Column(name = "morality_merit")
    private Integer moralityMerit;

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

    @ManyToOne
    @JoinColumn(name="archetype_id")
    private ArchetypeEntity archetype;
    @Column(name = "bloodline_id")
    private Integer bloodline;

    @ManyToOne
    @JoinColumn(name="sect_id")
    private SectEntity sect;
    @ManyToOne
    @JoinColumn(name="clan_id")
    private ClanEntity clan;

    @ManyToOne
    @JoinColumn(name="jurisdiction_id")
    private JurisdictionEntity jurisdiction;

    public CharacterEntity() {}

    public CharacterEntity(Long id, Boolean npc, Boolean archived, String player, String name, Integer physical, Boolean physicalStrength, Boolean physicalDexterity, Boolean physicalStamina, Integer social, Boolean socialCharisma, Boolean socialManipulation, Boolean socialAppearance, Integer mental, Boolean mentalPerception, Boolean mentalIntelligence, Boolean mentalWits, Integer generation, Integer blood, Integer willpower, Integer morality, Integer moralityMerit, Integer healthy, Integer injured, Integer incapacitated, Integer beast, Integer madness, String note, ArchetypeEntity archetype, Integer bloodline, SectEntity sect, ClanEntity clan, JurisdictionEntity jurisdiction) {
        this.id = id;
        this.npc = npc;
        this.archived = archived;
        this.player = player;
        this.name = name;
        this.physical = physical;
        this.physicalStrength = physicalStrength;
        this.physicalDexterity = physicalDexterity;
        this.physicalStamina = physicalStamina;
        this.social = social;
        this.socialCharisma = socialCharisma;
        this.socialManipulation = socialManipulation;
        this.socialAppearance = socialAppearance;
        this.mental = mental;
        this.mentalPerception = mentalPerception;
        this.mentalIntelligence = mentalIntelligence;
        this.mentalWits = mentalWits;
        this.generation = generation;
        this.blood = blood;
        this.willpower = willpower;
        this.morality = morality;
        this.moralityMerit = moralityMerit;
        this.healthy = healthy;
        this.injured = injured;
        this.incapacitated = incapacitated;
        this.beast = beast;
        this.madness = madness;
        this.note = note;
        this.archetype = archetype;
        this.bloodline = bloodline;
        this.sect = sect;
        this.clan = clan;
        this.jurisdiction = jurisdiction;
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

    public Integer getPhysical() {
        return physical;
    }

    public void setPhysical(Integer physical) {
        this.physical = physical;
    }

    public Boolean getPhysicalStrength() {
        return physicalStrength;
    }

    public void setPhysicalStrength(Boolean physicalStrength) {
        this.physicalStrength = physicalStrength;
    }

    public Boolean getPhysicalDexterity() {
        return physicalDexterity;
    }

    public void setPhysicalDexterity(Boolean physicalDexterity) {
        this.physicalDexterity = physicalDexterity;
    }

    public Boolean getPhysicalStamina() {
        return physicalStamina;
    }

    public void setPhysicalStamina(Boolean physicalStamina) {
        this.physicalStamina = physicalStamina;
    }

    public Integer getSocial() {
        return social;
    }

    public void setSocial(Integer social) {
        this.social = social;
    }

    public Boolean getSocialCharisma() {
        return socialCharisma;
    }

    public void setSocialCharisma(Boolean socialCharisma) {
        this.socialCharisma = socialCharisma;
    }

    public Boolean getSocialManipulation() {
        return socialManipulation;
    }

    public void setSocialManipulation(Boolean socialManipulation) {
        this.socialManipulation = socialManipulation;
    }

    public Boolean getSocialAppearance() {
        return socialAppearance;
    }

    public void setSocialAppearance(Boolean socialAppearance) {
        this.socialAppearance = socialAppearance;
    }

    public Integer getMental() {
        return mental;
    }

    public void setMental(Integer mental) {
        this.mental = mental;
    }

    public Boolean getMentalPerception() {
        return mentalPerception;
    }

    public void setMentalPerception(Boolean mentalPerception) {
        this.mentalPerception = mentalPerception;
    }

    public Boolean getMentalIntelligence() {
        return mentalIntelligence;
    }

    public void setMentalIntelligence(Boolean mentalIntelligence) {
        this.mentalIntelligence = mentalIntelligence;
    }

    public Boolean getMentalWits() {
        return mentalWits;
    }

    public void setMentalWits(Boolean mentalWits) {
        this.mentalWits = mentalWits;
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

    public Integer getMoralityMerit() { return moralityMerit; }

    public void setMoralityMerit(Integer moralityMerit) {
        this.moralityMerit = moralityMerit;
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

    public Integer getBloodline() {
        return bloodline;
    }

    public void setBloodline(Integer bloodline) {
        this.bloodline = bloodline;
    }

    public ClanEntity getClan() {
        return clan;
    }

    public void setClan(ClanEntity clan) {
        this.clan = clan;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public ArchetypeEntity getArchetype() {
        return archetype;
    }

    public void setArchetype(ArchetypeEntity archetype) {
        this.archetype = archetype;
    }

    public JurisdictionEntity getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(JurisdictionEntity jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public SectEntity getSect() {
        return sect;
    }

    public void setSect(SectEntity sect) {
        this.sect = sect;
    }

}
