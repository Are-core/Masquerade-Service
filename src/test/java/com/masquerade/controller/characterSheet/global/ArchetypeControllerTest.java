package com.masquerade.controller.characterSheet.global;

import com.masquerade.mocks.json.JsonMock;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.dto.controller.ResponseEntityDTO;
import com.masquerade.model.entity.characterSheet.global.ArchetypeEntity;
import com.masquerade.service.characterSheet.global.ArchetypeService;
import com.masquerade.service.repository.characterSheet.global.ArchetypeRepository;
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

class ArchetypeControllerTest {
    @Mock
    ArchetypeRepository archetypeRepository;

    ArchetypeService archetypeService;

    ArchetypeController archetypeController;

    @BeforeEach
    public void setUp() {
        archetypeRepository = spy(ArchetypeRepository.class);
        setUpMocks();
        archetypeService = new ArchetypeService(archetypeRepository);
        archetypeController = new ArchetypeController(archetypeService);
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
    public void testGetArchetypes() {
        ResponseEntityDTO<ResponseDTO> response = archetypeController.getArchetypes();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetArchetypeOk() {
        ResponseEntityDTO<ResponseDTO> response = archetypeController.getArchetype(1L);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetArchetypeWithMissingId() {
        ResponseEntityDTO<ResponseDTO> response = archetypeController.getArchetype(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testGetArchetypeWithNotExistingId() {
        ResponseEntityDTO<ResponseDTO> response = archetypeController.getArchetype(85654L);
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void testRemoveArchetypeOk() {
        ResponseEntityDTO<ResponseDTO> response = archetypeController.removeArchetype(1L);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testRemoveArchetypeWithMissingId() {
        ResponseEntityDTO<ResponseDTO> response = archetypeController.removeArchetype(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testRemoveArchetypeWithNotExistingId() {
        ResponseEntityDTO<ResponseDTO> response = archetypeController.removeArchetype(564561L);
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void testCreateArchetypeOk() {
        ResponseEntityDTO<ResponseDTO> response = archetypeController.createArchetype(JsonMock.getArchetype());
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testCreateArchetypeWithMissingBody() {
        ResponseEntityDTO<ResponseDTO> response = archetypeController.createArchetype(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testCreateArchetypeWithBadBody() {
        ResponseEntityDTO<ResponseDTO> response = archetypeController.createArchetype(JsonMock.getBadArchetype());
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateArchetypeOk() {
        ResponseEntityDTO<ResponseDTO> response = archetypeController.updateArchetype(JsonMock.getArchetype());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUpdateArchetypeWithMissingBody() {
        ResponseEntityDTO<ResponseDTO> response = archetypeController.updateArchetype(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateArchetypeWithEmptyBody() {
        ResponseEntityDTO<ResponseDTO> response = archetypeController.updateArchetype("{}");
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateArchetypeWithBadBody() {
        ResponseEntityDTO<ResponseDTO> response = archetypeController.updateArchetype(JsonMock.getBadArchetype());
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateArchetypeWithNotExistingBody() {
        ResponseEntityDTO<ResponseDTO> response = archetypeController.updateArchetype(JsonMock.getNotExistingArchetype());
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }
}