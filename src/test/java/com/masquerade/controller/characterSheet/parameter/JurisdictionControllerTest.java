package com.masquerade.controller.characterSheet.parameter;

import com.masquerade.mocks.json.JsonMock;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.dto.controller.ResponseEntityDTO;
import com.masquerade.model.entity.characterSheet.parameter.JurisdictionEntity;
import com.masquerade.service.characterSheet.parameter.JurisdictionService;
import com.masquerade.repository.characterSheet.parameter.JurisdictionRepository;
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
import static org.mockito.Mockito.spy;

class JurisdictionControllerTest {
    JurisdictionService jurisdictionService;

    @Mock
    JurisdictionRepository jurisdictionRepository;

    JurisdictionController jurisdictionController;

    @BeforeEach
    public void setUp() {
        jurisdictionRepository = spy(JurisdictionRepository.class);
        setUpMocks();
        jurisdictionService = new JurisdictionService(jurisdictionRepository);
        jurisdictionController = new JurisdictionController(jurisdictionService);

    }

    private void setUpMocks(){
        List<JurisdictionEntity> list = new ArrayList<>();
        JurisdictionEntity entity = new JurisdictionEntity();
        entity.setId(1L);
        list.add(entity);
        entity.setId(2L);
        list.add(entity);
        Mockito.when(jurisdictionRepository.existsById(1L)).thenReturn(true);
        Mockito.when(jurisdictionRepository.findById(2L)).thenReturn(Optional.of(entity));
        Mockito.when(jurisdictionRepository.findById(1L)).thenReturn(Optional.of(entity));
        Mockito.when(jurisdictionRepository.findAll()).thenReturn(list);
        Mockito.when(jurisdictionRepository.save(any())).thenReturn(entity);
    }

    @Test
    public void testGetSkills() {
        ResponseEntityDTO<ResponseDTO> response = jurisdictionController.getJurisdictions();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetSkillOk() {
        ResponseEntityDTO<ResponseDTO> response = jurisdictionController.getJurisdiction(1L);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetSkillWithMissingId() {
        ResponseEntityDTO<ResponseDTO> response = jurisdictionController.getJurisdiction(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testGetSkillWithNotExistingId() {
        ResponseEntityDTO<ResponseDTO> response = jurisdictionController.getJurisdiction(85654L);
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void testRemoveSkillOk() {
        ResponseEntityDTO<ResponseDTO> response = jurisdictionController.removeJurisdiction(1L);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testRemoveSkillWithMissingId() {
        ResponseEntityDTO<ResponseDTO> response = jurisdictionController.removeJurisdiction(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testRemoveSkillWithNotExistingId() {
        ResponseEntityDTO<ResponseDTO> response = jurisdictionController.removeJurisdiction(564561L);
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void testCreateSkillOk() {
        ResponseEntityDTO<ResponseDTO> response = jurisdictionController.createJurisdiction(JsonMock.getJurisdiction());
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testCreateSkillWithMissingBody() {
        ResponseEntityDTO<ResponseDTO> response = jurisdictionController.createJurisdiction(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testCreateSkillWithBadBody() {
        ResponseEntityDTO<ResponseDTO> response = jurisdictionController.createJurisdiction(JsonMock.getBadJurisdiction());
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateSkillOk() {
        ResponseEntityDTO<ResponseDTO> response = jurisdictionController.updateJurisdiction(JsonMock.getJurisdiction());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testUpdateSkillWithMissingBody() {
        ResponseEntityDTO<ResponseDTO> response = jurisdictionController.updateJurisdiction(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateSkillWithEmptyBody() {
        ResponseEntityDTO<ResponseDTO> response = jurisdictionController.updateJurisdiction("{}");
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateSkillWithBadBody() {
        ResponseEntityDTO<ResponseDTO> response = jurisdictionController.updateJurisdiction(JsonMock.getBadJurisdiction());
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateSkillWithNotExistingBody() {
        ResponseEntityDTO<ResponseDTO> response = jurisdictionController.updateJurisdiction(JsonMock.getNotExistingJurisdiction());
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }
}