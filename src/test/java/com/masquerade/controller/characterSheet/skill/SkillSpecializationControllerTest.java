package com.masquerade.controller.characterSheet.skill;

import com.masquerade.mocks.json.JsonMock;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.dto.controller.ResponseEntityDTO;
import com.masquerade.model.entity.characterSheet.skill.SkillSpecializationEntity;
import com.masquerade.service.characterSheet.skill.SkillSpecializationService;
import com.masquerade.repository.characterSheet.skill.SkillSpecializationRepository;
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

class SkillSpecializationControllerTest {
    @Mock
    SkillSpecializationRepository skillSpecializationRepository;

    SkillSpecializationService skillSpecializationService;

    SkillSpecializationController skillSpecializationController;

    @BeforeEach
    public void setUp() {
        skillSpecializationRepository = spy(SkillSpecializationRepository.class);
        setUpMocks();
        skillSpecializationService = new SkillSpecializationService(skillSpecializationRepository);
        skillSpecializationController = new SkillSpecializationController(skillSpecializationService);
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
        Mockito.when(skillSpecializationRepository.existsById(1L)).thenReturn(true);
        Mockito.when(skillSpecializationRepository.existsById(5L)).thenReturn(false);
        skillSpe.setId(null);
        Mockito.when(skillSpecializationRepository.save(skillSpe)).thenReturn(skillSpe);
    }

    @Test
    public void testGetSpecializations() {
        ResponseEntityDTO<ResponseDTO> response = skillSpecializationController.getSkillSpecializations();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetSpecializationOk() {
        ResponseEntityDTO<ResponseDTO> response = skillSpecializationController.getSkillSpecialization(1L);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetSpecializationWithMissingId() {
        ResponseEntityDTO<ResponseDTO> response = skillSpecializationController.getSkillSpecialization(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testGetSpecializationWithNotExistingId() {
        ResponseEntityDTO<ResponseDTO> response = skillSpecializationController.getSkillSpecialization(85654L);
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void testRemoveSpecializationOk() {
        ResponseEntityDTO<ResponseDTO> response = skillSpecializationController.removeSkillSpecialization(1L);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testRemoveSpecializationWithMissingId() {
        ResponseEntityDTO<ResponseDTO> response = skillSpecializationController.removeSkillSpecialization(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testRemoveSpecializationWithNotExistingId() {
        ResponseEntityDTO<ResponseDTO> response = skillSpecializationController.removeSkillSpecialization(564561L);
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void testCreateSpecializationOk() {
        ResponseEntityDTO<ResponseDTO> response = skillSpecializationController.createSkillSpecialization(JsonMock.getSkillSpecializationJson());
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testCreateSpecializationWithMissingBody() {
        ResponseEntityDTO<ResponseDTO> response = skillSpecializationController.createSkillSpecialization(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testCreateSpecializationWithBadBody() {
        ResponseEntityDTO<ResponseDTO> response = skillSpecializationController.createSkillSpecialization(JsonMock.getBadSkillSpecializationJson());
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateSpecializationOk() {
        ResponseEntityDTO<ResponseDTO> response = skillSpecializationController.updateSkillSpecialization(JsonMock.getSkillSpecializationJson());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testUpdateSpecializationWithMissingBody() {
        ResponseEntityDTO<ResponseDTO> response = skillSpecializationController.updateSkillSpecialization(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateSpecializationWithEmptyBody() {
        ResponseEntityDTO<ResponseDTO> response = skillSpecializationController.updateSkillSpecialization("{}");
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateSpecializationWithBadBody() {
        ResponseEntityDTO<ResponseDTO> response = skillSpecializationController.updateSkillSpecialization(JsonMock.getBadSkillSpecializationJson());
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateSpecializationWithNotExistingBody() {
        ResponseEntityDTO<ResponseDTO> response = skillSpecializationController.updateSkillSpecialization(JsonMock.getNotExistingSkillSpecializationJson());
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }
}