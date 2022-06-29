package com.masquerade.service.characterSheet.skill;

import com.masquerade.exception.BadRequestException;
import com.masquerade.model.entity.characterSheet.CharacterEntity;
import com.masquerade.model.entity.characterSheet.skill.SkillSpecializationEntity;
import com.masquerade.repository.characterSheet.skill.SkillSpecializationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class SkillSpecializationServiceTest {

    @Mock
    SkillSpecializationRepository skillSpecializationRepository;

    SkillSpecializationService skillSpecializationService;

    @BeforeEach
    public void setUp() {
        skillSpecializationRepository = spy(SkillSpecializationRepository.class);
        setUpMocks();
        skillSpecializationService = new SkillSpecializationService(skillSpecializationRepository);
    }

    private void setUpMocks(){
        List<SkillSpecializationEntity> list = new ArrayList<>();
        SkillSpecializationEntity skillSpe = new SkillSpecializationEntity();
        skillSpe.setId(2L);
        skillSpe.setDescriptionEN("A small description");
        skillSpe.setDescriptionFR("Une petite description");
        list.add(skillSpe);
        skillSpe.setId(1L);
        skillSpe.setDescriptionEN("A small description 2");
        skillSpe.setDescriptionFR("Une petite description 2");
        list.add(skillSpe);
        Mockito.when(skillSpecializationRepository.findAll()).thenReturn(list);
        Mockito.when(skillSpecializationRepository.findById(1L)).thenReturn(Optional.of(skillSpe));
    }

    @Test
    void getSkillSpecializationsOK() {
        List<SkillSpecializationEntity> list = skillSpecializationService.getSkillSpecializations();
        assertNotNull(list);
        assertEquals(2, list.size());
        assertNotNull(list.get(0).getId());
        assertNotNull(list.get(0).getDescriptionEN());
        assertNotNull(list.get(0).getDescriptionFR());
        assertEquals(1L, (long) list.get(0).getId());
        verify(skillSpecializationRepository, times(1)).findAll();
    }

    @Test
    void getSkillSpecializationByIdOK() {
        try {
            SkillSpecializationEntity skillSpe = skillSpecializationService.getSkillSpecialization(1L);
            assertNotNull(skillSpe);
            assertNotNull(skillSpe.getId());
            assertNotNull(skillSpe.getDescriptionEN());
            assertNotNull(skillSpe.getDescriptionFR());
            assertEquals(1L, (long) skillSpe.getId());
            verify(skillSpecializationRepository, times(1)).findById(1L);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getSkillSpecializationByIdWithIncorrectId()  {
        try {
            SkillSpecializationEntity skillSpe = skillSpecializationService.getSkillSpecialization(8L);
            fail();
        }
        catch (IllegalArgumentException i) {
            verify(skillSpecializationRepository, times(1)).findById(8L);
            assertTrue(true);
        }
        catch (Exception e) {
            fail();
        }
    }

    @Test
    void getSkillSpecializationByIdMissingParameter() {
        try {
            skillSpecializationService.getSkillSpecialization(null);
            fail();
        } catch (BadRequestException e) {
            verify(skillSpecializationRepository, times(0)).findById(any());
            assertTrue(true);
        }
    }

    @Test
    void createSkillSpecialization() {
    }

    @Test
    void removeSkillSpecialization() {
    }

    @Test
    void updateSkillSpecialization() {
    }
}