package com.masquerade.controller.characterSheet.skill;

import com.masquerade.mocks.json.JsonMock;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.dto.controller.ResponseEntityDTO;
import com.masquerade.model.entity.characterSheet.skill.SkillEntity;
import com.masquerade.service.characterSheet.skill.SkillService;
import com.masquerade.service.repository.characterSheet.skill.SkillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

class SkillControllerTest {
    @Mock
    private SkillRepository skillRepository;

    SkillService skillService;

    SkillController skillController;

    @BeforeEach
    public void setUp() {
        skillRepository = spy(SkillRepository.class);
        setUpMocks();
        skillService = new SkillService(skillRepository);
        skillController = new SkillController(skillService);
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
        Mockito.when(skillRepository.findById(1L)).thenReturn(Optional.of(entity));

        updateEntity.setDescriptionEN("Note");
        Mockito.when(skillRepository.save(updateEntity)).thenReturn(entity);
        Mockito.when(skillRepository.save(entity)).thenReturn(entity);
        Mockito.when(skillRepository.existsById(8L)).thenReturn(true);
        Mockito.when(skillRepository.existsById(1L)).thenReturn(true);
    }

    @Test
    public void testGetSkills() {
        ResponseEntityDTO<ResponseDTO> response = skillController.getSkills();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetSkillOk() {
        ResponseEntityDTO<ResponseDTO> response = skillController.getSkill(8L);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetSkillWithMissingId() {
        ResponseEntityDTO<ResponseDTO> response = skillController.getSkill(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testGetSkillWithNotExistingId() {
        ResponseEntityDTO<ResponseDTO> response = skillController.getSkill(85654L);
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void testRemoveSkillOk() {
        ResponseEntityDTO<ResponseDTO> response = skillController.removeSkill(8L);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testRemoveSkillWithMissingId() {
        ResponseEntityDTO<ResponseDTO> response = skillController.removeSkill(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testRemoveSkillWithNotExistingId() {
        ResponseEntityDTO<ResponseDTO> response = skillController.removeSkill(564561L);
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void testCreateSkillOk() {
        ResponseEntityDTO<ResponseDTO> response = skillController.createSkill(JsonMock.getSkillSpecializationJson());
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testCreateSkillWithMissingBody() {
        ResponseEntityDTO<ResponseDTO> response = skillController.createSkill(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testCreateSkillWithBadBody() {
        ResponseEntityDTO<ResponseDTO> response = skillController.createSkill(JsonMock.getBadSkillSpecializationJson());
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateSkillOk() {
        ResponseEntityDTO<ResponseDTO> response = skillController.updateSkill(JsonMock.getSkillSpecializationJson());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testUpdateSkillWithMissingBody() {
        ResponseEntityDTO<ResponseDTO> response = skillController.updateSkill(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateSkillWithEmptyBody() {
        ResponseEntityDTO<ResponseDTO> response = skillController.updateSkill("{}");
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateSkillWithBadBody() {
        ResponseEntityDTO<ResponseDTO> response = skillController.updateSkill(JsonMock.getBadSkillSpecializationJson());
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateSkillWithNotExistingBody() {
        ResponseEntityDTO<ResponseDTO> response = skillController.updateSkill(JsonMock.getNotExistingSkillSpecializationJson());
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }
}