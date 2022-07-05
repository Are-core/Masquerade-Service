package com.masquerade.controller.characterSheet.parameter;

import com.masquerade.mocks.json.JsonMock;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.dto.controller.ResponseEntityDTO;
import com.masquerade.model.entity.characterSheet.global.SectEntity;
import com.masquerade.model.entity.characterSheet.parameter.StatusEntity;
import com.masquerade.model.entity.characterSheet.parameter.StatusTypeEntity;
import com.masquerade.service.characterSheet.parameter.StatusService;
import com.masquerade.repository.characterSheet.parameter.StatusRepository;
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

class StatusControllerTest {
    @Mock
    StatusRepository statusRepository;

    StatusService statusService;

    StatusController statusController;

    @BeforeEach
    public void setUp() {
        statusRepository = spy(StatusRepository.class);
        setUpMocks();
        statusService = new StatusService(statusRepository);
        statusController = new StatusController(statusService);
    }

    private void setUpMocks(){
        List<StatusEntity> statusList = new ArrayList<>();
        statusList.add(new StatusEntity(1L, new SectEntity(), new StatusTypeEntity(), "", "" ,"", ""));
        statusList.add(new StatusEntity(2L, new SectEntity(), new StatusTypeEntity(), "", "" ,"", ""));
        Mockito.when(statusRepository.findAll()).thenReturn(statusList);
        Mockito.when(statusRepository.findById(1L)).thenReturn(Optional.of(new StatusEntity(1L, new SectEntity(), new StatusTypeEntity(), "Test", "", "", "")));
        Mockito.when(statusRepository.save(any())).thenReturn(new StatusEntity(1L, new SectEntity(), new StatusTypeEntity(), "Test", "", "", ""));
        Mockito.when(statusRepository.existsById(69L)).thenReturn(true);
        Mockito.when(statusRepository.existsById(8L)).thenReturn(true);
        Mockito.when(statusRepository.findById(69L)).thenReturn(Optional.of(new StatusEntity(69L, new SectEntity(), new StatusTypeEntity(), "Test", "", "", "")));
    }

    @Test
    public void testGetSkills() {
        ResponseEntityDTO<ResponseDTO> response = statusController.getStatus();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetSkillOk() {
        ResponseEntityDTO<ResponseDTO> response = statusController.getStatusById(1L);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetSkillWithMissingId() {
        ResponseEntityDTO<ResponseDTO> response = statusController.getStatusById(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testGetSkillWithNotExistingId() {
        ResponseEntityDTO<ResponseDTO> response = statusController.getStatusById(85654L);
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void testRemoveSkillOk() {
        ResponseEntityDTO<ResponseDTO> response = statusController.removeStatus(1L);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testRemoveSkillWithMissingId() {
        ResponseEntityDTO<ResponseDTO> response = statusController.removeStatus(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testRemoveSkillWithNotExistingId() {
        ResponseEntityDTO<ResponseDTO> response = statusController.removeStatus(564561L);
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void testCreateSkillOk() {
        ResponseEntityDTO<ResponseDTO> response = statusController.createStatus(JsonMock.getSkillJson());
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testCreateSkillWithMissingBody() {
        ResponseEntityDTO<ResponseDTO> response = statusController.createStatus(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testCreateSkillWithBadBody() {
        ResponseEntityDTO<ResponseDTO> response = statusController.createStatus(JsonMock.getBadSkillJson());
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateSkillOk() {
        ResponseEntityDTO<ResponseDTO> response = statusController.updateStatus(JsonMock.getSkillJson());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUpdateSkillWithMissingBody() {
        ResponseEntityDTO<ResponseDTO> response = statusController.updateStatus(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateSkillWithEmptyBody() {
        ResponseEntityDTO<ResponseDTO> response = statusController.updateStatus("{}");
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateSkillWithBadBody() {
        ResponseEntityDTO<ResponseDTO> response = statusController.updateStatus(JsonMock.getBadSkillJson());
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateSkillWithNotExistingBody() {
        ResponseEntityDTO<ResponseDTO> response = statusController.updateStatus(JsonMock.getNotExistingStatus());
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }
}