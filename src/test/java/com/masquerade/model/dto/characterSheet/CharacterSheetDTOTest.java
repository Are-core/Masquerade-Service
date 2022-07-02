package com.masquerade.model.dto.characterSheet;

import com.masquerade.model.dto.characterSheet.skill.DeclaredSkillDTO;
import com.masquerade.model.entity.characterSheet.CharacterEntity;
import com.masquerade.model.entity.characterSheet.global.ArchetypeEntity;
import com.masquerade.model.entity.characterSheet.global.ClanEntity;
import com.masquerade.model.entity.characterSheet.global.SectEntity;
import com.masquerade.model.entity.characterSheet.parameter.JurisdictionEntity;
import com.masquerade.model.entity.characterSheet.skill.SkillSpecializationEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CharacterSheetDTOTest {
    CharacterSheetDTO characterSheetDTO;

    @BeforeEach
    public void setUp() {
        characterSheetDTO = new CharacterSheetDTO();
    }

    @Test
    public void testNullFields() {
        setUp();
        Field[] fields = characterSheetDTO.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (!field.getName().equals("__$lineHits$__")) {
                    assertNull(field.get(characterSheetDTO));
                }
            } catch (Exception e) {
                fail();
            }
        }
    }

    @Test
    public void testConstructors() {
        characterSheetDTO = new CharacterSheetDTO();
        assertNull(characterSheetDTO.getId());
        assertNull(characterSheetDTO.getName());

        characterSheetDTO = new CharacterSheetDTO(9L, false, false, "Bob", "Vladimir", 0, false, false, false, 0, false, false, false, 0, false, false, false, 5, 2, 4, 5, 3, 5, 2, 0, 1, 2, "", new ArchetypeEntity(), 0, new SectEntity(), new ClanEntity(), new JurisdictionEntity(), new ArrayList<>());
        assertNotNull(characterSheetDTO.getId());
        assertEquals(9L, characterSheetDTO.getId());
        assertNotNull(characterSheetDTO.getName());
        assertEquals("Vladimir", characterSheetDTO.getName());

        CharacterEntity entity = new CharacterEntity(45L, null, null, "Jean", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        CharacterSheetDTO copy = new CharacterSheetDTO(entity);
        assertEquals(entity.getId(), copy.getId());
        assertEquals(entity.getPlayer(), copy.getPlayer());
    }

    /* Setter / Getters */
    @Test
    public void testId() {
        characterSheetDTO.setId(54L);
        assertEquals(54L, characterSheetDTO.getId());
    }

    @Test
    public void testNpc() {
        characterSheetDTO.setNpc(true);
        assertEquals(true, characterSheetDTO.getNpc());
        characterSheetDTO.setNpc(false);
        assertEquals(false, characterSheetDTO.getNpc());
    }

    @Test
    public void testArchived() {
        characterSheetDTO.setArchived(true);
        assertEquals(true, characterSheetDTO.getArchived());
        characterSheetDTO.setArchived(false);
        assertEquals(false, characterSheetDTO.getArchived());
    }

    @Test
    public void testPlayer() {
        characterSheetDTO.setPlayer("Bob");
        assertEquals("Bob", characterSheetDTO.getPlayer());
    }

    @Test
    public void testName() {
        characterSheetDTO.setName("Bob");
        assertEquals("Bob", characterSheetDTO.getName());
    }

    @Test
    public void testPhysical() {
        characterSheetDTO.setPhysical(5);
        assertEquals(5, characterSheetDTO.getPhysical());
    }

    @Test
    public void testPhysicalStrength() {
        characterSheetDTO.setPhysicalStrength(true);
        assertEquals(true, characterSheetDTO.getPhysicalStrength());
        characterSheetDTO.setPhysicalStrength(false);
        assertEquals(false, characterSheetDTO.getPhysicalStrength());
    }
    @Test
    public void testPhysicalDexterity() {
        characterSheetDTO.setPhysicalDexterity(true);
        assertEquals(true, characterSheetDTO.getPhysicalDexterity());
        characterSheetDTO.setPhysicalDexterity(false);
        assertEquals(false, characterSheetDTO.getPhysicalDexterity());
    }
    @Test
    public void testPhysicalStamina() {
        characterSheetDTO.setPhysicalStamina(true);
        assertEquals(true, characterSheetDTO.getPhysicalStamina());
        characterSheetDTO.setPhysicalStamina(false);
        assertEquals(false, characterSheetDTO.getPhysicalStamina());
    }

    @Test
    public void testSocial() {
        characterSheetDTO.setSocial(6);
        assertEquals(6, characterSheetDTO.getSocial());
    }

    @Test
    public void testSocialCharisma() {
        characterSheetDTO.setSocialCharisma(true);
        assertEquals(true, characterSheetDTO.getSocialCharisma());
        characterSheetDTO.setSocialCharisma(false);
        assertEquals(false, characterSheetDTO.getSocialCharisma());
    }
    @Test
    public void testSocialManipulation() {
        characterSheetDTO.setSocialManipulation(true);
        assertEquals(true, characterSheetDTO.getSocialManipulation());
        characterSheetDTO.setSocialManipulation(false);
        assertEquals(false, characterSheetDTO.getSocialManipulation());
    }
    @Test
    public void testSocialAppearance() {
        characterSheetDTO.setSocialAppearance(true);
        assertEquals(true, characterSheetDTO.getSocialAppearance());
        characterSheetDTO.setSocialAppearance(false);
        assertEquals(false, characterSheetDTO.getSocialAppearance());
    }

    @Test
    public void testMental() {
        characterSheetDTO.setMental(4);
        assertEquals(4, characterSheetDTO.getMental());
    }

    @Test
    public void testMentalPerception() {
        characterSheetDTO.setMentalPerception(true);
        assertEquals(true, characterSheetDTO.getMentalPerception());
        characterSheetDTO.setMentalPerception(false);
        assertEquals(false, characterSheetDTO.getMentalPerception());
    }
    @Test
    public void testMentalIntelligence() {
        characterSheetDTO.setMentalIntelligence(true);
        assertEquals(true, characterSheetDTO.getMentalIntelligence());
        characterSheetDTO.setMentalIntelligence(false);
        assertEquals(false, characterSheetDTO.getMentalIntelligence());
    }
    @Test
    public void testMentalWits() {
        characterSheetDTO.setMentalWits(true);
        assertEquals(true, characterSheetDTO.getMentalWits());
        characterSheetDTO.setMentalWits(false);
        assertEquals(false, characterSheetDTO.getMentalWits());
    }

    @Test
    public void testBlood() {
        characterSheetDTO.setBlood(4);
        assertEquals(4, characterSheetDTO.getBlood());
    }

    @Test
    public void testWillpower() {
        characterSheetDTO.setWillpower(40);
        assertEquals(40, characterSheetDTO.getWillpower());
    }

    @Test
    public void testMorality() {
        characterSheetDTO.setMorality(1);
        assertEquals(1, characterSheetDTO.getMorality());
    }

    @Test
    public void testGeneration() {
        characterSheetDTO.setGeneration(4);
        assertEquals(4, characterSheetDTO.getGeneration());
    }

    @Test
    public void testMoralityMerit() {
        characterSheetDTO.setMoralityMerit(4);
        assertEquals(4, characterSheetDTO.getMoralityMerit());
    }

    @Test
    public void testHealthy() {
        characterSheetDTO.setHealthy(1);
        assertEquals(1, characterSheetDTO.getHealthy());
    }

    @Test
    public void testInjured() {
        characterSheetDTO.setInjured(1);
        assertEquals(1, characterSheetDTO.getInjured());
    }

    @Test
    public void testIncapacitated() {
        characterSheetDTO.setIncapacitated(10);
        assertEquals(10, characterSheetDTO.getIncapacitated());
    }

    @Test
    public void testBeast() {
        characterSheetDTO.setBeast(0);
        assertEquals(0, characterSheetDTO.getBeast());
    }

    @Test
    public void testMadness() {
        characterSheetDTO.setMadness(0);
        assertEquals(0, characterSheetDTO.getMadness());
    }

    @Test
    public void testBloodline() {
        characterSheetDTO.setBloodlineId(0);
        assertEquals(0, characterSheetDTO.getBloodlineId());
    }

    @Test
    public void testNote() {
        characterSheetDTO.setNote("One note");
        assertEquals("One note", characterSheetDTO.getNote());
    }

    @Test
    public void testClan() {
        ClanEntity clan = new ClanEntity();
        clan.setId(6L);
        characterSheetDTO.setClan(clan);
        assertNotNull(characterSheetDTO.getClan());
        assertEquals(6L, characterSheetDTO.getClan().getId());
    }

    @Test
    public void testArchetype() {
        ArchetypeEntity archetype = new ArchetypeEntity();
        archetype.setId(6L);
        characterSheetDTO.setArchetype(archetype);
        assertNotNull(characterSheetDTO.getArchetype());
        assertEquals(6L, characterSheetDTO.getArchetype().getId());
    }

    @Test
    public void testJurisdiction() {
        JurisdictionEntity jurisdiction = new JurisdictionEntity();
        jurisdiction.setId(6L);
        characterSheetDTO.setJurisdiction(jurisdiction);
        assertNotNull(characterSheetDTO.getJurisdiction());
        assertEquals(6L, characterSheetDTO.getJurisdiction().getId());
    }

    @Test
    public void testSkills() {
        List<DeclaredSkillDTO> skills= new ArrayList<>();
        DeclaredSkillDTO skill = new DeclaredSkillDTO(1L, 0, "", "", false, false, "", "", 0, new SkillSpecializationEntity());
        skills.add(skill);
        characterSheetDTO.setSkills(skills);
        assertNotNull(characterSheetDTO.getSkills());
        assertEquals(1L, characterSheetDTO.getSkills().get(0).getId());
    }
}