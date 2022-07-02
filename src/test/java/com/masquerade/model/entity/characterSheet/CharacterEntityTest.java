package com.masquerade.model.entity.characterSheet;

import com.masquerade.model.entity.characterSheet.global.ArchetypeEntity;
import com.masquerade.model.entity.characterSheet.global.ClanEntity;
import com.masquerade.model.entity.characterSheet.global.SectEntity;
import com.masquerade.model.entity.characterSheet.parameter.JurisdictionEntity;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class CharacterEntityTest {

    CharacterEntity character;

    @BeforeEach
    public void setUp() {
        character = new CharacterEntity();
    }

    @org.junit.Test
    @Before
    @Test
    public void testNullFields()  {
        Field[] fields = character.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if(!field.getName().equals("__$lineHits$__")) {
                    assertNull(field.get(character));
                }
            } catch (Exception e) {
                fail();
            }
        }
    }

    /* Constructor */

    @Test
    void testConstructor() {
        character = new CharacterEntity(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    /* Setter / Getters */

    @Test
    void testId() {
        character.setId(5L);
        assertEquals(5L, character.getId());
    }

    @Test
    void testNpc() {
        character.setNpc(true);
        assertEquals(true, character.getNpc());
    }

    @Test
    void testPlayer() {
        character.setPlayer("player_1");
        assertEquals("player_1", character.getPlayer());
    }

    @Test
    void testName() {
        character.setName("character_1");
        assertEquals("character_1", character.getName());
    }

    @Test
    void testPhysical() {
        character.setPhysical(3);
        assertEquals(3, character.getPhysical());
    }

    @Test
    void testPhysicalStrength() {
        character.setPhysicalStrength(false);
        assertEquals(false, character.getPhysicalStrength());
    }

    @Test
    void testPhysicalDexterity() {
        character.setPhysicalDexterity(true);
        assertEquals(true, character.getPhysicalDexterity());
    }

    @Test
    void testPhysicalStamina() {
        character.setPhysicalStamina(false);
        assertEquals(false, character.getPhysicalStamina());
    }

    @Test
    void testSocial() {
        character.setSocial(2);
        assertEquals(2, character.getSocial());
    }

    @Test
    void testSocialCharisma() {
        character.setSocialCharisma(false);
        assertEquals(false, character.getSocialCharisma());
    }

    @Test
    void testSocialManipulation() {
        character.setSocialManipulation(true);
        assertEquals(true, character.getSocialManipulation());
    }

    @Test
    void testSocialAppearance() {
        character.setSocialAppearance(false);
        assertEquals(false, character.getSocialAppearance());
    }

    @Test
    void testMental() {
        character.setMental(5);
        assertEquals(5, character.getMental());
    }

    @Test
    void testMentalPerception() {
        character.setMentalPerception(false);
        assertEquals(false, character.getMentalPerception());
    }

    @Test
    void testMentalIntelligence() {
        character.setMentalIntelligence(true);
        assertEquals(true, character.getMentalIntelligence());
    }

    @Test
    void testMentalWits() {
        character.setMentalWits(false);
        assertEquals(false, character.getMentalWits());
    }

    @Test
    void testGeneration() {
        character.setGeneration(4);
        assertEquals(4, character.getGeneration());
    }

    @Test
    void testBlood() {
        character.setBlood(4);
        assertEquals(4, character.getBlood());
    }

    @Test
    void testWillpower() {
        character.setWillpower(0);
        assertEquals(0, character.getWillpower());
    }

    @Test
    void testMorality() {
        character.setMorality(5);
        assertEquals(5, character.getMorality());
    }

    @Test
    void testMoralityMerit() {
        character.setMoralityMerit(5);
        assertEquals(5, character.getMoralityMerit());
    }

    @Test
    void testHealthy() {
        character.setHealthy(1);
        assertEquals(1, character.getHealthy());
    }

    @Test
    void testInjured() {
        character.setInjured(6);
        assertEquals(6, character.getInjured());
    }

    @Test
    void testIncapacitated() {
        character.setIncapacitated(6);
        assertEquals(6, character.getIncapacitated());
    }

    @Test
    void testBeast() {
        character.setBeast(1);
        assertEquals(1, character.getBeast());
    }

    @Test
    void testMadness() {
        character.setMadness(1);
        assertEquals(1, character.getMadness());
    }

    @Test
    void testNote() {
        character.setNote("a test note");
        assertEquals("a test note", character.getNote());
    }

    @Test
    void testBloodline() {
        character.setBloodline(3);
        assertEquals(3, character.getBloodline());
    }

    @Test
    void testClan() {
        ClanEntity clan = new ClanEntity();
        clan.setId(9L);
        character.setClan(clan);
        assertNotNull(character.getClan());
        assertEquals(9L, character.getClan().getId());
    }

    @Test
    void testArchived() {
        character.setArchived(false);
        assertEquals(false, character.getArchived());
    }

    @Test
    void testArchetype() {
        ArchetypeEntity archetype = new ArchetypeEntity();
        archetype.setId(6L);
        character.setArchetype(archetype);
        assertNotNull(character.getArchetype());
        assertEquals(6L, character.getArchetype().getId());
    }

    @Test
    void testJurisdiction() {
        JurisdictionEntity jurisdiction = new JurisdictionEntity();
        jurisdiction.setId(6L);
        character.setJurisdiction(jurisdiction);
        assertNotNull(character.getJurisdiction());
        assertEquals(6L, character.getJurisdiction().getId());
    }

    @Test
    void testSect() {
        SectEntity sect = new SectEntity();
        sect.setId(5L);
        character.setSect(sect);
        assertNotNull(character.getSect());
        assertEquals(5L, character.getSect().getId());
    }
}