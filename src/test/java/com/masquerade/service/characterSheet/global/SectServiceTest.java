package com.masquerade.service.characterSheet.global;

import com.masquerade.mocks.json.JsonMock;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.entity.characterSheet.global.SectEntity;
import com.masquerade.repository.characterSheet.global.SectRepository;
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

class SectServiceTest {
    @Mock
    SectRepository sectRepository;

    SectService sectService;

    @BeforeEach
    public void setUp() {
        sectRepository = spy(SectRepository.class);
        setUpMocks();
        sectService = new SectService(sectRepository);
    }

    private void setUpMocks(){
        List<SectEntity> sectList = new ArrayList<>();

        sectList.add(new SectEntity(1L,"Test 1"));
        sectList.add(new SectEntity(2L,"Test 2"));
        sectList.add(new SectEntity(3L,"Test 3"));

        Mockito.when(sectRepository.findAll()).thenReturn(sectList);
        Mockito.when(sectRepository.existsById(1L)).thenReturn(true);
        Mockito.when(sectRepository.findById(1L)).thenReturn(Optional.of(new SectEntity(1L,"Test 1")));
        Mockito.when(sectRepository.save(any())).thenReturn(new SectEntity(2L,"Test 2"));
    }

    @Test
    public void testGetSects() {
        ResponseDTO response = sectService.getSects();
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertNotNull(response.getBody());
        if (response.getBody() instanceof List<?>) {
            final List<?> listValue = (List<?>) response.getBody();
            assertNotNull(listValue);
            assertEquals(3, listValue.size());
            if (listValue.get(0) instanceof SectEntity) {
                SectEntity entity = (SectEntity) listValue.get(0);
                assertNotNull(entity.getDescription());
                verify(sectRepository, times(1)).findAll();
                assertSame(response.getHttpStatus(), HttpStatus.OK);
            } else {
                fail();
            }
        }
        else {
            fail();
        }
    }

    @Test
    public void testGetSectOk() {
        ResponseDTO response = sectService.getSect(1L);
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertNotNull(response.getBody());
        SectEntity entity = (SectEntity) response.getBody();
        assertNotNull(entity.getDescription());
        verify(sectRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetSectWithMissingId() {
        ResponseDTO response = sectService.getSect(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
        verify(sectRepository, times(0)).findById(any());
    }

    @Test
    public void testGetSectWithNotExistingId() {
        ResponseDTO response = sectService.getSect(5646L);
        assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
        assertNull(response.getBody());
        verify(sectRepository, times(1)).findById(5646L);
    }

    @Test
    public void testRemoveSectOk() {
        ResponseDTO response = sectService.removeSect(1L);
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertNotNull(response.getBody());
        verify(sectRepository, times(1)).findById(1L);
    }

    @Test
    public void testRemoveSectWithMissingId() {
        ResponseDTO response = sectService.removeSect(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
        verify(sectRepository, times(0)).findById(any());
    }

    @Test
    public void testRemoveSectWithNotExistingId() {
        ResponseDTO response = sectService.removeSect(454L);
        assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
        assertNull(response.getBody());
        verify(sectRepository, times(1)).findById(454L);
    }

    @Test
    public void testCreateSectOk() {
        ResponseDTO response = sectService.createSect(JsonMock.getSect());
        assertEquals(HttpStatus.CREATED, response.getHttpStatus());
        assertNotNull(response.getBody());
        verify(sectRepository, times(1)).save(any());
    }

    @Test
    public void testCreateSectWithMissingBody() {
        ResponseDTO response = sectService.createSect(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
        verify(sectRepository, times(0)).save(any());
    }

    @Test
    public void testCreateSectWithBadBody() {
        ResponseDTO response = sectService.createSect(JsonMock.getBadSect());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
        verify(sectRepository, times(0)).save(any());
    }

    @Test
    public void testUpdateSectOk() {
        ResponseDTO response = sectService.updateSect(JsonMock.getSect());
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertNotNull(response.getBody());
        verify(sectRepository, times(1)).save(any());
    }

    @Test
    public void testUpdateSectWithMissingBody() {
        ResponseDTO response = sectService.updateSect(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
        verify(sectRepository, times(0)).save(any());
    }

    @Test
    public void testUpdateSectWithEmptyBody() {
        ResponseDTO response = sectService.updateSect("{}");
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
        verify(sectRepository, times(0)).save(any());
    }

    @Test
    public void testUpdateSectWithBadBody() {
        ResponseDTO response = sectService.updateSect(JsonMock.getBadSect());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
        verify(sectRepository, times(0)).save(any());
    }

    @Test
    public void testUpdateSectWithNotExistingId() {
        ResponseDTO response = sectService.updateSect(JsonMock.getNotExistingSect());
        assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
        assertNull(response.getBody());
        verify(sectRepository, times(0)).save(any());
    }
}