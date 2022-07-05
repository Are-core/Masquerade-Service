package com.masquerade.service.characterSheet.parameter;

import com.masquerade.mocks.json.JsonMock;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.entity.characterSheet.parameter.JurisdictionEntity;
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

class JurisdictionServiceTest {
    JurisdictionService jurisdictionService;

    @Mock
    JurisdictionRepository jurisdictionRepository;

    @BeforeEach
    public void setUp() {
        jurisdictionRepository = spy(JurisdictionRepository.class);
        setUpMocks();
        jurisdictionService = new JurisdictionService(jurisdictionRepository);
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
        Mockito.when(jurisdictionRepository.findAll()).thenReturn(list);
        Mockito.when(jurisdictionRepository.save(any())).thenReturn(entity);
    }

    @Test
    public void testGetStatusTypes(){
        ResponseDTO response = jurisdictionService.getJurisdictions();
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetStatusTypeOk(){
        ResponseDTO response = jurisdictionService.getJurisdiction(2L);
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertNotNull(response.getBody());
        JurisdictionEntity entity = (JurisdictionEntity) response.getBody();
        assertEquals(2L, entity.getId());
    }

    @Test
    public void testGetStatusTypeWithNotExistingId(){
        ResponseDTO response = jurisdictionService.getJurisdiction(12L);
        assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testGetStatusTypeWithMissingId(){
        ResponseDTO response = jurisdictionService.getJurisdiction(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testCreateStatusTypeOk(){
        ResponseDTO response = jurisdictionService.createJurisdiction(JsonMock.getJurisdiction());
        assertEquals(HttpStatus.CREATED, response.getHttpStatus());
        assertNotNull(response.getBody());
        JurisdictionEntity jurisdiction = (JurisdictionEntity) response.getBody();
        assertEquals(2L, jurisdiction.getId());
    }

    @Test
    public void testCreateStatusTypeWithMissingBody(){
        ResponseDTO response = jurisdictionService.createJurisdiction(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testCreateStatusTypeWithBadBody(){
        ResponseDTO response = jurisdictionService.createJurisdiction(JsonMock.getBadJurisdiction());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testRemoveStatusTypeOk(){
        ResponseDTO response = jurisdictionService.removeJurisdiction(2L);
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertNotNull(response.getBody());
    }

    @Test
    public void testRemoveStatusTypeWithMissingId(){
        ResponseDTO response = jurisdictionService.removeJurisdiction(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testRemoveStatusTypeNotExistingId(){
        ResponseDTO response = jurisdictionService.removeJurisdiction(5L);
        assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testUpdateStatusTypeOk(){
        ResponseDTO response = jurisdictionService.updateJurisdiction(JsonMock.getJurisdiction());
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertNotNull(response.getBody());
        JurisdictionEntity jurisdictionEntity = (JurisdictionEntity) response.getBody();
        assertEquals(2L, jurisdictionEntity.getId());
    }

    @Test
    public void testUpdateStatusTypeWithMissingBody(){
        ResponseDTO response = jurisdictionService.updateJurisdiction(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testUpdateStatusTypeWithNotExistingStatusType(){
        ResponseDTO response = jurisdictionService.updateJurisdiction(JsonMock.getNotExistingJurisdiction());
        assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testUpdateStatusTypeWithEmptyBody(){
        ResponseDTO response = jurisdictionService.updateJurisdiction("{}");
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testUpdateStatusTypeWithBadBody(){
        ResponseDTO response = jurisdictionService.updateJurisdiction(JsonMock.getBadJurisdiction());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
    }
}