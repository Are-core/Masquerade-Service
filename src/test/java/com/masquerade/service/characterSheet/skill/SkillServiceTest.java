package com.masquerade.service.characterSheet.skill;

import com.masquerade.mocks.json.JsonMock;
import com.masquerade.model.dto.characterSheet.CharacterListItemDTO;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.entity.characterSheet.skill.SkillEntity;
import com.masquerade.repository.characterSheet.skill.SkillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SkillServiceTest {
    @Mock
    private SkillRepository skillRepository;

    SkillService skillService;

    @BeforeEach
    public void setUp() {
        skillRepository = spy(SkillRepository.class);
        setUpMocks();
        skillService = new SkillService(skillRepository);
    }

    private void setUpMocks() {
        List<SkillEntity> skills = new ArrayList<>();
        SkillEntity entity = new SkillEntity();
        SkillEntity updateEntity = new SkillEntity();
        skills.add(entity);
        Mockito.when(skillRepository.findAll()).thenReturn(skills);

        entity.setId(8L);
        entity.setDescriptionEN("Note");
        Mockito.when(skillRepository.findById(8L)).thenReturn(Optional.of(entity));

        updateEntity.setDescriptionEN("Note");
        Mockito.when(skillRepository.save(updateEntity)).thenReturn(entity);
        Mockito.when(skillRepository.save(entity)).thenReturn(entity);
        Mockito.when(skillRepository.existsById(8L)).thenReturn(true);
    }

    @Test
    public void testGetSkillsOk() {
        ResponseDTO response = skillService.getSkills();
        if (response.getBody() instanceof List<?>) {
            final List<?> listValue = (List<?>) response.getBody();
            assertNotNull(listValue);
            assertFalse(listValue.isEmpty());
            if (listValue.get(0) instanceof CharacterListItemDTO) {
                SkillEntity skill = (SkillEntity) listValue.get(0);
                assertTrue(skill.emptyObjectCheck());
            }
        }
    }

    @Test
    public void testGetSkillOk() {
        ResponseDTO response = skillService.getSkill(8L);
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        SkillEntity skill = (SkillEntity) response.getBody();
        assertEquals("Note", skill.getDescriptionEN());
        verify(skillRepository, times(1)).findById(any());
    }

    @Test
    public void testGetSkillMissingId() {
        ResponseDTO response = skillService.getSkill(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
        assertNotNull(response.getMessage());
    }

    @Test
    public void testGetSkillBadId() {
        ResponseDTO response = skillService.getSkill(1587L);
        verify(skillRepository, times(1)).findById(any());
        assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testCreateSkillOk() {
        ResponseDTO response = skillService.createSkill(JsonMock.getSkillJson());
        assertEquals(HttpStatus.CREATED, response.getHttpStatus());
        verify(skillRepository, times(1)).save(any());
    }

    @Test
    public void testCreateSkillMissingParameter() {
        ResponseDTO response = skillService.createSkill(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        verify(skillRepository, times(0)).save(any());
    }

    @Test
    public void testCreateSkillBadBody() {
        ResponseDTO response = skillService.createSkill(JsonMock.getBadSkillJson());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        verify(skillRepository, times(0)).save(any());
    }

    @Test
    public void testRemoveSkillOk() {
        ResponseDTO response = skillService.removeSkill(8L);
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        verify(skillRepository, times(1)).delete(any());
    }

    @Test
    public void testRemoveSkillMissingId() {
        ResponseDTO response = skillService.removeSkill(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        verify(skillRepository, times(0)).delete(any());
    }

    @Test
    public void testRemoveSkillBadId() {
        ResponseDTO response = skillService.removeSkill(0L);
        assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
        verify(skillRepository, times(0)).delete(any());
    }

    @Test
    public void testUpdateSkillOk() {
        ResponseDTO response = skillService.updateSkill(JsonMock.getSkillJson());
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        verify(skillRepository, times(1)).save(any());
    }

    @Test
    public void testUpdateSkillMissingBody() {
        ResponseDTO response = skillService.updateSkill(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        verify(skillRepository, times(0)).save(any());
    }

    @Test
    public void testUpdateSkillBadBody() {
        ResponseDTO response = skillService.updateSkill(JsonMock.getBadSkillJson());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        verify(skillRepository, times(0)).save(any());
    }

    @Test
    public void testUpdateSkillEmptyBody() {
        ResponseDTO response = skillService.updateSkill("{}");
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        verify(skillRepository, times(0)).save(any());
    }

    @Test
    public void testUpdateSkillDoesNotExist() {
        ResponseDTO response = skillService.updateSkill(JsonMock.getMissingSkillJson());
        assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
        verify(skillRepository, times(0)).save(any());
    }
}