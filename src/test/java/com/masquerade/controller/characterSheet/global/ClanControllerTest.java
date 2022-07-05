package com.masquerade.controller.characterSheet.global;

import com.masquerade.mocks.json.JsonMock;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.dto.controller.ResponseEntityDTO;
import com.masquerade.model.entity.characterSheet.global.ClanEntity;
import com.masquerade.service.characterSheet.global.ClanService;
import com.masquerade.service.repository.characterSheet.global.ClanRepository;
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

class ClanControllerTest {
    @Mock
    ClanRepository clanRepository;

    ClanService clanService;

    ClanController clanController;

    @BeforeEach
    public void setUp() {
        clanRepository = spy(ClanRepository.class);
        setUpMocks();
        clanService = new ClanService(clanRepository);
        clanController = new ClanController(clanService);
    }

    private void setUpMocks(){
        List<ClanEntity> titleList = new ArrayList<>();

        titleList.add(new ClanEntity(1L,"", "", "", ""));
        titleList.add(new ClanEntity(2L,"", "", "", ""));
        titleList.add(new ClanEntity(3L,"", "", "", ""));

        Mockito.when(clanRepository.findAll()).thenReturn(titleList);
        Mockito.when(clanRepository.existsById(1L)).thenReturn(true);
        Mockito.when(clanRepository.findById(1L)).thenReturn(Optional.of(new ClanEntity(1L, "Test", "", "", "")));
        Mockito.when(clanRepository.save(any())).thenReturn(new ClanEntity(2L, "", "", "", ""));
    }

    @Test
    public void testGetSects() {
        ResponseEntityDTO<ResponseDTO> response = clanController.getClans();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetSectOk() {
        ResponseEntityDTO<ResponseDTO> response = clanController.getClan(1L);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetSectWithMissingId() {
        ResponseEntityDTO<ResponseDTO> response = clanController.getClan(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testGetSectWithNotExistingId() {
        ResponseEntityDTO<ResponseDTO> response = clanController.getClan(85654L);
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void testRemoveSectOk() {
        ResponseEntityDTO<ResponseDTO> response = clanController.removeClan(1L);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testRemoveSectWithMissingId() {
        ResponseEntityDTO<ResponseDTO> response = clanController.removeClan(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testRemoveSectWithNotExistingId() {
        ResponseEntityDTO<ResponseDTO> response = clanController.removeClan(564561L);
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void testCreateSectOk() {
        ResponseEntityDTO<ResponseDTO> response = clanController.createClan(JsonMock.getClan());
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testCreateSectWithMissingBody() {
        ResponseEntityDTO<ResponseDTO> response = clanController.createClan(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testCreateSectWithBadBody() {
        ResponseEntityDTO<ResponseDTO> response = clanController.createClan(JsonMock.getBadClan());
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateSectOk() {
        ResponseEntityDTO<ResponseDTO> response = clanController.updateClan(JsonMock.getClan());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUpdateSectWithMissingBody() {
        ResponseEntityDTO<ResponseDTO> response = clanController.updateClan(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateSectWithEmptyBody() {
        ResponseEntityDTO<ResponseDTO> response = clanController.updateClan("{}");
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateSectWithBadBody() {
        ResponseEntityDTO<ResponseDTO> response = clanController.updateClan(JsonMock.getBadClan());
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateSectWithNotExistingBody() {
        ResponseEntityDTO<ResponseDTO> response = clanController.updateClan(JsonMock.getNotExistingClan());
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }
}