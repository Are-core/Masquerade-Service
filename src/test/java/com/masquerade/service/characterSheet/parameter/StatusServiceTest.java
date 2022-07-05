package com.masquerade.service.characterSheet.parameter;

import com.masquerade.mocks.json.JsonMock;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.entity.characterSheet.global.SectEntity;
import com.masquerade.model.entity.characterSheet.parameter.StatusEntity;
import com.masquerade.model.entity.characterSheet.parameter.StatusTypeEntity;
import com.masquerade.service.repository.characterSheet.parameter.StatusRepository;
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

class StatusServiceTest {

    @Mock
    StatusRepository statusRepository;

    StatusService statusService;

    @BeforeEach
    public void setUp() {
        statusRepository = spy(StatusRepository.class);
        setUpMocks();
        statusService = new StatusService(statusRepository);
    }

    private void setUpMocks(){
        List<StatusEntity> statusList = new ArrayList<>();
        statusList.add(new StatusEntity(1L, new SectEntity(), new StatusTypeEntity(), "", "" ,"", ""));
        statusList.add(new StatusEntity(2L, new SectEntity(), new StatusTypeEntity(), "", "" ,"", ""));
        Mockito.when(statusRepository.findAll()).thenReturn(statusList);
        Mockito.when(statusRepository.findById(1L)).thenReturn(Optional.of(new StatusEntity(1L, new SectEntity(), new StatusTypeEntity(), "Test", "", "", "")));
        Mockito.when(statusRepository.save(any())).thenReturn(new StatusEntity(1L, new SectEntity(), new StatusTypeEntity(), "Test", "", "", ""));
        Mockito.when(statusRepository.existsById(69L)).thenReturn(true);
        Mockito.when(statusRepository.findById(69L)).thenReturn(Optional.of(new StatusEntity(69L, new SectEntity(), new StatusTypeEntity(), "Test", "", "", "")));
    }

    @Test
    public void testStatusList() {
        ResponseDTO response = statusService.getStatus();
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertNotNull(response.getBody());
    }

    @Test
    public void testStatusByIdOk() {
        ResponseDTO response = statusService.getStatus(1L);
        StatusEntity status = (StatusEntity) response.getBody();
        assertNotNull(status);
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertEquals(1L, status.getId());
        assertEquals("Test", status.getDescriptionEN());
    }

    @Test
    public void testStatusByIdWithMissingParameter() {
        ResponseDTO response = statusService.getStatus(null);
        StatusEntity status = (StatusEntity) response.getBody();
        assertNull(status);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }

    @Test
    public void testStatusByIdWithNotExistingId() {
        ResponseDTO response = statusService.getStatus(45L);
        StatusEntity status = (StatusEntity) response.getBody();
        assertNull(status);
        assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
    }

    @Test
    public void testCreateStatus() {
        ResponseDTO response = statusService.createStatus(JsonMock.getStatus());
        assertEquals(HttpStatus.CREATED, response.getHttpStatus());
        StatusEntity status = (StatusEntity) response.getBody();
        assertEquals("Test", status.getDescriptionEN());
    }

    @Test
    public void testCreateStatusWithMissingBody() {
        ResponseDTO response = statusService.createStatus(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }

    @Test
    public void testCreateStatusWithBadBody() {
        ResponseDTO response = statusService.createStatus(JsonMock.getBadStatus());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }

    @Test
    public void testUpdateStatus() {
        ResponseDTO response = statusService.updateStatus(JsonMock.getStatus());
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        StatusEntity status = (StatusEntity) response.getBody();
        assertEquals("Test", status.getDescriptionEN());
    }

    @Test
    public void testUpdateStatusWithMissingBody() {
        ResponseDTO response = statusService.updateStatus(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }

    @Test
    public void testUpdateStatusWithEmptyBody() {
        ResponseDTO response = statusService.updateStatus("{}");
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }

    @Test
    public void testUpdateStatusWithNotExistingStatus() {
        ResponseDTO response = statusService.updateStatus(JsonMock.getNotExistingStatus());
        assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
    }

    @Test
    public void testUpdateStatusWithBadStatus() {
        ResponseDTO response = statusService.updateStatus(JsonMock.getBadStatus());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }

    @Test
    public void testRemoveStatusOk() {
        ResponseDTO response = statusService.removeStatus(69L);
        assertEquals(HttpStatus.OK, response.getHttpStatus());
    }

    @Test
    public void testRemoveStatusWithMissingId() {
        ResponseDTO response = statusService.removeStatus(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }

    @Test
    public void testRemoveStatusWithNotExistingId() {
        ResponseDTO response = statusService.removeStatus(50L);
        assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
    }
}