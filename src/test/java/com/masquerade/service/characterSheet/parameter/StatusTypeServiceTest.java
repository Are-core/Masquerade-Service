package com.masquerade.service.characterSheet.parameter;

import com.masquerade.mocks.json.JsonMock;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.entity.characterSheet.parameter.StatusTypeEntity;
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

class StatusTypeServiceTest {
    @Mock
    StatusTypeService statusTypeService;

    StatusTypeRepository statusTypeRepository;

    @BeforeEach
    public void setUp() {
        statusTypeRepository = spy(StatusTypeRepository.class);
        setUpMocks();
        statusTypeService = new StatusTypeService(statusTypeRepository);
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
        Mockito.when(statusTypeRepository.findAll()).thenReturn(list);
        Mockito.when(statusTypeRepository.save(any())).thenReturn(entity);
    }

    @Test
    public void testGetStatusTypes(){
        ResponseDTO response = statusTypeService.getStatusTypes();
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetStatusTypeOk(){
        ResponseDTO response = statusTypeService.getStatusType(2L);
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertNotNull(response.getBody());
        StatusTypeEntity entity = (StatusTypeEntity) response.getBody();
        assertEquals(2L, entity.getId());
    }

    @Test
    public void testGetStatusTypeWithNotExistingId(){
        ResponseDTO response = statusTypeService.getStatusType(12L);
        assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testGetStatusTypeWithMissingId(){
        ResponseDTO response = statusTypeService.getStatusType(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testCreateStatusTypeOk(){
        ResponseDTO response = statusTypeService.createStatusType(JsonMock.getStatusType());
        assertEquals(HttpStatus.CREATED, response.getHttpStatus());
        assertNotNull(response.getBody());
        StatusTypeEntity statusType = (StatusTypeEntity) response.getBody();
        assertEquals(2L, statusType.getId());
    }

    @Test
    public void testCreateStatusTypeWithMissingBody(){
        ResponseDTO response = statusTypeService.createStatusType(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testCreateStatusTypeWithBadBody(){
        ResponseDTO response = statusTypeService.createStatusType(JsonMock.getBadStatusType());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testRemoveStatusTypeOk(){
        ResponseDTO response = statusTypeService.removeStatusType(2L);
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertNotNull(response.getBody());
    }

    @Test
    public void testRemoveStatusTypeWithMissingId(){
        ResponseDTO response = statusTypeService.removeStatusType(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testRemoveStatusTypeNotExistingId(){
        ResponseDTO response = statusTypeService.removeStatusType(5L);
        assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testUpdateStatusTypeOk(){
        ResponseDTO response = statusTypeService.updateStatusType(JsonMock.getStatusType());
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertNotNull(response.getBody());
        StatusTypeEntity statusType = (StatusTypeEntity) response.getBody();
        assertEquals(2L, statusType.getId());
    }

    @Test
    public void testUpdateStatusTypeWithMissingBody(){
        ResponseDTO response = statusTypeService.updateStatusType(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testUpdateStatusTypeWithNotExistingStatusType(){
        ResponseDTO response = statusTypeService.updateStatusType(JsonMock.getNotExistingStatusType());
        assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testUpdateStatusTypeWithEmptyBody(){
        ResponseDTO response = statusTypeService.updateStatusType("{}");
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testUpdateStatusTypeWithBadBody(){
        ResponseDTO response = statusTypeService.updateStatusType(JsonMock.getBadStatusType());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
    }
}