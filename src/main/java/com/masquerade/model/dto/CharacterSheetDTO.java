package com.masquerade.model.dto;


import com.masquerade.model.dto.skill.DeclaredSkillDTO;
import com.masquerade.model.entity.characterSheet.CharacterEntity;
import com.masquerade.model.entity.characterSheet.global.ArchetypeEntity;
import com.masquerade.model.entity.characterSheet.global.ClanEntity;
import com.masquerade.model.entity.characterSheet.global.SectEntity;
import com.masquerade.model.entity.characterSheet.parameter.JurisdictionEntity;

import java.util.List;

public class CharacterSheetDTO {
    private Long id;

    private Boolean npc;
    private Boolean archived;
    private String player;
    private String name;

    private Integer physical;
    private Boolean physicalstrength;
    private Boolean physicaldexterity;
    private Boolean physicalstamina;

    private Integer social;
    private Boolean socialcharisma;
    private Boolean socialmanipulation;
    private Boolean socialappearance;

    private Integer mental;
    private Boolean mentalperception;
    private Boolean mentalintelligence;
    private Boolean mentalwits;

    private Integer generation;
    private Integer blood;
    private Integer willpower;

    private Integer morality;
    private Integer morality_merit;

    private Integer healthy;
    private Integer injured;
    private Integer incapacitated;
    private Integer beast;
    private Integer madness;

    private String note;

    private ArchetypeEntity archetype;

    private Integer bloodline_id;

    private SectEntity sect;

    private ClanEntity clan;

    private JurisdictionEntity jurisdiction;

    private List<DeclaredSkillDTO> skills;

    public CharacterSheetDTO() {
    }

    public CharacterSheetDTO(Long id, Boolean npc, Boolean archived, String player, String name, Integer physical, Boolean physicalstrength, Boolean physicaldexterity, Boolean physicalstamina, Integer social, Boolean socialcharisma, Boolean socialmanipulation, Boolean socialappearance, Integer mental, Boolean mentalperception, Boolean mentalintelligence, Boolean mentalwits, Integer generation, Integer blood, Integer willpower, Integer morality, Integer morality_merit, Integer healthy, Integer injured, Integer incapacitated, Integer beast, Integer madness, String note, ArchetypeEntity archetype, Integer bloodline_id, SectEntity sect, ClanEntity clan, JurisdictionEntity jurisdiction, List<DeclaredSkillDTO> skills) {
        this.id = id;
        this.npc = npc;
        this.archived = archived;
        this.player = player;
        this.name = name;
        this.physical = physical;
        this.physicalstrength = physicalstrength;
        this.physicaldexterity = physicaldexterity;
        this.physicalstamina = physicalstamina;
        this.social = social;
        this.socialcharisma = socialcharisma;
        this.socialmanipulation = socialmanipulation;
        this.socialappearance = socialappearance;
        this.mental = mental;
        this.mentalperception = mentalperception;
        this.mentalintelligence = mentalintelligence;
        this.mentalwits = mentalwits;
        this.generation = generation;
        this.blood = blood;
        this.willpower = willpower;
        this.morality = morality;
        this.morality_merit = morality_merit;
        this.healthy = healthy;
        this.injured = injured;
        this.incapacitated = incapacitated;
        this.beast = beast;
        this.madness = madness;
        this.note = note;
        this.archetype = archetype;
        this.bloodline_id = bloodline_id;
        this.sect = sect;
        this.clan = clan;
        this.jurisdiction = jurisdiction;
        this.skills = skills;
    }

    public CharacterSheetDTO(CharacterEntity character) {
        this.id = character.getId();
        this.npc = character.getNpc();
        this.archived = character.getArchived();
        this.player = character.getPlayer();
        this.name = character.getName();
        this.physical = character.getPhysical();
        this.physicalstrength = character.getPhysicalstrength();
        this.physicaldexterity = character.getPhysicaldexterity();
        this.physicalstamina = character.getPhysicalstamina();
        this.social = character.getSocial();
        this.socialcharisma = character.getSocialcharisma();
        this.socialmanipulation = character.getSocialmanipulation();
        this.socialappearance = character.getSocialappearance();
        this.mental = character.getMental();
        this.mentalperception = character.getMentalperception();
        this.mentalintelligence = character.getMentalintelligence();
        this.mentalwits = character.getMentalwits();
        this.generation = character.getGeneration();
        this.blood = character.getBlood();
        this.willpower = character.getWillpower();
        this.morality = character.getMorality();
        this.morality_merit = character.getMorality_merit();
        this.healthy = character.getHealthy();
        this.injured = character.getInjured();
        this.incapacitated = character.getIncapacitated();
        this.beast = character.getBeast();
        this.madness = character.getMadness();
        this.note = character.getNote();
        this.archetype = character.getArchetype();
        this.bloodline_id = character.getBloodline_id();
        this.sect = character.getSect();
        this.clan = character.getClan();
        this.jurisdiction = character.getJurisdiction();
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

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
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

    public ArchetypeEntity getArchetype() {
        return archetype;
    }

    public void setArchetype(ArchetypeEntity archetype) {
        this.archetype = archetype;
    }

    public Integer getBloodline_id() {
        return bloodline_id;
    }

    public void setBloodline_id(Integer bloodline_id) {
        this.bloodline_id = bloodline_id;
    }

    public SectEntity getSect() {
        return sect;
    }

    public void setSect(SectEntity sect) {
        this.sect = sect;
    }

    public ClanEntity getClan() {
        return clan;
    }

    public void setClan(ClanEntity clan) {
        this.clan = clan;
    }

    public JurisdictionEntity getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(JurisdictionEntity jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public List<DeclaredSkillDTO> getSkills() {
        return skills;
    }

    public void setSkills(List<DeclaredSkillDTO> skills) {
        this.skills = skills;
    }
}
