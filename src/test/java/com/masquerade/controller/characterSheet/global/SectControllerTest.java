package com.masquerade.controller.characterSheet.global;

import com.masquerade.mocks.json.JsonMock;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.dto.controller.ResponseEntityDTO;
import com.masquerade.model.entity.characterSheet.global.SectEntity;
import com.masquerade.service.characterSheet.global.SectService;
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
import static org.mockito.Mockito.spy;

class SectControllerTest {
    @Mock
    SectRepository sectRepository;

    SectService sectService;

    SectController sectController;

    @BeforeEach
    public void setUp() {
        sectRepository = spy(SectRepository.class);
        setUpMocks();
        sectService = new SectService(sectRepository);
        sectController = new SectController(sectService);
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
        ResponseEntityDTO<ResponseDTO> response = sectController.getSects();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetSectOk() {
        ResponseEntityDTO<ResponseDTO> response = sectController.getSect(1L);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetSectWithMissingId() {
        ResponseEntityDTO<ResponseDTO> response = sectController.getSect(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testGetSectWithNotExistingId() {
        ResponseEntityDTO<ResponseDTO> response = sectController.getSect(85654L);
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void testRemoveSectOk() {
        ResponseEntityDTO<ResponseDTO> response = sectController.removeSect(1L);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testRemoveSectWithMissingId() {
        ResponseEntityDTO<ResponseDTO> response = sectController.removeSect(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testRemoveSectWithNotExistingId() {
        ResponseEntityDTO<ResponseDTO> response = sectController.removeSect(564561L);
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void testCreateSectOk() {
        ResponseEntityDTO<ResponseDTO> response = sectController.createSect(JsonMock.getSect());
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testCreateSectWithMissingBody() {
        ResponseEntityDTO<ResponseDTO> response = sectController.createSect(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testCreateSectWithBadBody() {
        ResponseEntityDTO<ResponseDTO> response = sectController.createSect(JsonMock.getBadSect());
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateSectOk() {
        ResponseEntityDTO<ResponseDTO> response = sectController.updateSect(JsonMock.getSect());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUpdateSectWithMissingBody() {
        ResponseEntityDTO<ResponseDTO> response = sectController.updateSect(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateSectWithEmptyBody() {
        ResponseEntityDTO<ResponseDTO> response = sectController.updateSect("{}");
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateSectWithBadBody() {
        ResponseEntityDTO<ResponseDTO> response = sectController.updateSect(JsonMock.getBadSect());
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateSectWithNotExistingBody() {
        ResponseEntityDTO<ResponseDTO> response = sectController.updateSect(JsonMock.getNotExistingSect());
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }
}