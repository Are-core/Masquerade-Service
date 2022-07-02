package com.masquerade.model.dto.characterSheet;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.masquerade.model.dto.characterSheet.skill.DeclaredSkillDTO;
import com.masquerade.model.entity.characterSheet.CharacterEntity;
import com.masquerade.model.entity.characterSheet.global.ArchetypeEntity;
import com.masquerade.model.entity.characterSheet.global.ClanEntity;
import com.masquerade.model.entity.characterSheet.global.SectEntity;
import com.masquerade.model.entity.characterSheet.parameter.JurisdictionEntity;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CharacterSheetDTO {
    private Long id;

    private Boolean npc;
    private Boolean archived;
    private String player;
    private String name;

    private Integer physical;
    private Boolean physicalStrength;
    private Boolean physicalDexterity;
    private Boolean physicalStamina;

    private Integer social;
    private Boolean socialCharisma;
    private Boolean socialManipulation;
    private Boolean socialAppearance;

    private Integer mental;
    private Boolean mentalPerception;
    private Boolean mentalIntelligence;
    private Boolean mentalWits;

    private Integer generation;
    private Integer blood;
    private Integer willpower;

    private Integer morality;
    private Integer moralityMerit;

    private Integer healthy;
    private Integer injured;
    private Integer incapacitated;
    private Integer beast;
    private Integer madness;

    private String note;

    private ArchetypeEntity archetype;

    private Integer bloodlineId;

    private SectEntity sect;

    private ClanEntity clan;

    private JurisdictionEntity jurisdiction;

    private List<DeclaredSkillDTO> skills;

    public CharacterSheetDTO() {
    }

    public CharacterSheetDTO(Long id, Boolean npc, Boolean archived, String player, String name, Integer physical, Boolean physicalStrength, Boolean physicalDexterity, Boolean physicalStamina, Integer social, Boolean socialCharisma, Boolean socialManipulation, Boolean socialAppearance, Integer mental, Boolean mentalPerception, Boolean mentalIntelligence, Boolean mentalWits, Integer generation, Integer blood, Integer willpower, Integer morality, Integer moralityMerit, Integer healthy, Integer injured, Integer incapacitated, Integer beast, Integer madness, String note, ArchetypeEntity archetype, Integer bloodlineId, SectEntity sect, ClanEntity clan, JurisdictionEntity jurisdiction, List<DeclaredSkillDTO> skills) {
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
        this.bloodlineId = bloodlineId;
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
        this.physicalStrength = character.getPhysicalStrength();
        this.physicalDexterity = character.getPhysicalDexterity();
        this.physicalStamina = character.getPhysicalStamina();
        this.social = character.getSocial();
        this.socialCharisma = character.getSocialCharisma();
        this.socialManipulation = character.getSocialManipulation();
        this.socialAppearance = character.getSocialAppearance();
        this.mental = character.getMental();
        this.mentalPerception = character.getMentalPerception();
        this.mentalIntelligence = character.getMentalIntelligence();
        this.mentalWits = character.getMentalWits();
        this.generation = character.getGeneration();
        this.blood = character.getBlood();
        this.willpower = character.getWillpower();
        this.morality = character.getMorality();
        this.moralityMerit = character.getMoralityMerit();
        this.healthy = character.getHealthy();
        this.injured = character.getInjured();
        this.incapacitated = character.getIncapacitated();
        this.beast = character.getBeast();
        this.madness = character.getMadness();
        this.note = character.getNote();
        this.archetype = character.getArchetype();
        this.bloodlineId = character.getBloodline();
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

    public Integer getMoralityMerit() {
        return moralityMerit;
    }

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

    public ArchetypeEntity getArchetype() {
        return archetype;
    }

    public void setArchetype(ArchetypeEntity archetype) {
        this.archetype = archetype;
    }

    public Integer getBloodlineId() {
        return bloodlineId;
    }

    public void setBloodlineId(Integer bloodlineId) {
        this.bloodlineId = bloodlineId;
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
