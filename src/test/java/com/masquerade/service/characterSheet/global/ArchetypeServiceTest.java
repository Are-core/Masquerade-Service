package com.masquerade.service.characterSheet.global;

import com.masquerade.mocks.json.JsonMock;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.entity.characterSheet.global.ArchetypeEntity;
import com.masquerade.repository.characterSheet.global.ArchetypeRepository;
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

class ArchetypeServiceTest {
    @Mock
    ArchetypeRepository archetypeRepository;

    ArchetypeService archetypeService;

    @BeforeEach
    public void setUp() {
        archetypeRepository = spy(ArchetypeRepository.class);
        setUpMocks();
        archetypeService = new ArchetypeService(archetypeRepository);
    }

    private void setUpMocks(){
        List<ArchetypeEntity> titleList = new ArrayList<>();

        titleList.add(new ArchetypeEntity(1L,"", "", "", ""));
        titleList.add(new ArchetypeEntity(2L,"", "", "", ""));
        titleList.add(new ArchetypeEntity(3L,"", "", "", ""));

        Mockito.when(archetypeRepository.findAll()).thenReturn(titleList);
        Mockito.when(archetypeRepository.existsById(1L)).thenReturn(true);
        Mockito.when(archetypeRepository.findById(1L)).thenReturn(Optional.of(new ArchetypeEntity(1L, "Test", "", "", "")));
        Mockito.when(archetypeRepository.save(any())).thenReturn(new ArchetypeEntity(2L, "", "", "", ""));
    }

    @Test
    public void testGetClans() {
        ResponseDTO response = archetypeService.getArchetypes();
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertNotNull(response.getBody());
        if (response.getBody() instanceof List<?>) {
            final List<?> listValue = (List<?>) response.getBody();
            assertNotNull(listValue);
            assertEquals(3, listValue.size());
            if (listValue.get(0) instanceof ArchetypeEntity) {
                ArchetypeEntity clan = (ArchetypeEntity) listValue.get(0);
                assertNotNull(clan.getDescriptionEN());
                verify(archetypeRepository, times(1)).findAll();
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
    public void testGetArchetypeOk() {
        ResponseDTO response = archetypeService.getArchetype(1L);
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertNotNull(response.getBody());
        ArchetypeEntity entity = (ArchetypeEntity) response.getBody();
        assertNotNull(entity.getDescriptionEN());
        verify(archetypeRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetArchetypeWithMissingId() {
        ResponseDTO response = archetypeService.getArchetype(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
        verify(archetypeRepository, times(0)).findById(any());
    }

    @Test
    public void testGetArchetypeWithNotExistingId() {
        ResponseDTO response = archetypeService.getArchetype(5646L);
        assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
        assertNull(response.getBody());
        verify(archetypeRepository, times(1)).findById(5646L);
    }

    @Test
    public void testRemoveArchetypeOk() {
        ResponseDTO response = archetypeService.removeArchetype(1L);
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertNotNull(response.getBody());
        verify(archetypeRepository, times(1)).findById(1L);
    }

    @Test
    public void testRemoveArchetypeWithMissingId() {
        ResponseDTO response = archetypeService.removeArchetype(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
        verify(archetypeRepository, times(0)).findById(any());
    }

    @Test
    public void testRemoveArchetypeWithNotExistingId() {
        ResponseDTO response = archetypeService.removeArchetype(454L);
        assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
        assertNull(response.getBody());
        verify(archetypeRepository, times(1)).findById(454L);
    }

    @Test
    public void testCreateClanOk() {
        ResponseDTO response = archetypeService.createArchetype(JsonMock.getArchetype());
        assertEquals(HttpStatus.CREATED, response.getHttpStatus());
        assertNotNull(response.getBody());
        verify(archetypeRepository, times(1)).save(any());
    }

    @Test
    public void testCreateClanWithMissingBody() {
        ResponseDTO response = archetypeService.createArchetype(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
        verify(archetypeRepository, times(0)).save(any());
    }

    @Test
    public void testCreateClanWithBadBody() {
        ResponseDTO response = archetypeService.createArchetype(JsonMock.getBadArchetype());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
        verify(archetypeRepository, times(0)).save(any());
    }

    @Test
    public void testUpdateClanOk() {
        ResponseDTO response = archetypeService.updateArchetype(JsonMock.getArchetype());
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertNotNull(response.getBody());
        verify(archetypeRepository, times(1)).save(any());
    }

    @Test
    public void testUpdateClanWithMissingBody() {
        ResponseDTO response = archetypeService.updateArchetype(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
        verify(archetypeRepository, times(0)).save(any());
    }

    @Test
    public void testUpdateClanWithEmptyBody() {
        ResponseDTO response = archetypeService.updateArchetype("{}");
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
        verify(archetypeRepository, times(0)).save(any());
    }

    @Test
    public void testUpdateClanWithBadBody() {
        ResponseDTO response = archetypeService.updateArchetype(JsonMock.getBadArchetype());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
        verify(archetypeRepository, times(0)).save(any());
    }

    @Test
    public void testUpdateClanWithNotExistingId() {
        ResponseDTO response = archetypeService.updateArchetype(JsonMock.getNotExistingArchetype());
        assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
        assertNull(response.getBody());
        verify(archetypeRepository, times(0)).save(any());
    }
}