package com.masquerade.controller.characterSheet.parameter;

import com.masquerade.mocks.json.JsonMock;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.dto.controller.ResponseEntityDTO;
import com.masquerade.model.entity.characterSheet.parameter.StatusTypeEntity;
import com.masquerade.service.characterSheet.parameter.StatusTypeService;
import com.masquerade.service.repository.characterSheet.parameter.StatusTypeRepository;
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

class StatusTypeControllerTest {

    StatusTypeService statusTypeService;

    @Mock
    StatusTypeRepository statusTypeRepository;

    StatusTypeController statusTypeController;

    @BeforeEach
    public void setUp() {
        statusTypeRepository = spy(StatusTypeRepository.class);
        setUpMocks();
        statusTypeService = new StatusTypeService(statusTypeRepository);
        statusTypeController = new StatusTypeController(statusTypeService);
    }

    private void setUpMocks(){
        List<StatusTypeEntity> list = new ArrayList<>();
        StatusTypeEntity entity = new StatusTypeEntity();
        entity.setId(1L);
        list.add(entity);
        entity.setId(2L);
        list.add(entity);
        Mockito.when(statusTypeRepository.existsById(1L)).thenReturn(true);
        Mockito.when(statusTypeRepository.findById(2L)).thenReturn(Optional.of(entity));
        Mockito.when(statusTypeRepository.findById(1L)).thenReturn(Optional.of(entity));
        Mockito.when(statusTypeRepository.findAll()).thenReturn(list);
        Mockito.when(statusTypeRepository.save(any())).thenReturn(entity);
    }

    @Test
    public void testGetStatusTypes() {
        ResponseEntityDTO<ResponseDTO> response = statusTypeController.getStatusTypes();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetStatusTypeOk() {
        ResponseEntityDTO<ResponseDTO> response = statusTypeController.getStatusType(1L);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetStatusTypeWithMissingId() {
        ResponseEntityDTO<ResponseDTO> response = statusTypeController.getStatusType(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testGetStatusTypeWithNotExistingId() {
        ResponseEntityDTO<ResponseDTO> response = statusTypeController.getStatusType(85654L);
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void testRemoveStatusTypeOk() {
        ResponseEntityDTO<ResponseDTO> response = statusTypeController.removeStatusType(1L);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testRemoveStatusTypeWithMissingId() {
        ResponseEntityDTO<ResponseDTO> response = statusTypeController.removeStatusType(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testRemoveStatusTypeWithNotExistingId() {
        ResponseEntityDTO<ResponseDTO> response = statusTypeController.removeStatusType(564561L);
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void testCreateStatusTypeOk() {
        ResponseEntityDTO<ResponseDTO> response = statusTypeController.createStatusType(JsonMock.getStatusType());
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testCreateStatusTypeWithMissingBody() {
        ResponseEntityDTO<ResponseDTO> response = statusTypeController.createStatusType(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testCreateStatusTypeWithBadBody() {
        ResponseEntityDTO<ResponseDTO> response = statusTypeController.createStatusType(JsonMock.getBadStatusType());
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateStatusTypeOk() {
        ResponseEntityDTO<ResponseDTO> response = statusTypeController.updateStatusType(JsonMock.getStatusType());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUpdateStatusTypeWithMissingBody() {
        ResponseEntityDTO<ResponseDTO> response = statusTypeController.updateStatusType(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateStatusTypeWithEmptyBody() {
        ResponseEntityDTO<ResponseDTO> response = statusTypeController.updateStatusType("{}");
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateStatusTypeWithBadBody() {
        ResponseEntityDTO<ResponseDTO> response = statusTypeController.updateStatusType(JsonMock.getBadStatusType());
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateStatusTypeWithNotExistingBody() {
        ResponseEntityDTO<ResponseDTO> response = statusTypeController.updateStatusType(JsonMock.getNotExistingStatusType());
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }
}