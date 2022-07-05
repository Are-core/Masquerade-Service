package com.masquerade.service.characterSheet.global;

import com.masquerade.mocks.json.JsonMock;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.entity.characterSheet.global.ClanEntity;
import com.masquerade.repository.characterSheet.global.ClanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClanServiceTest {
    @Mock
    ClanRepository clanRepository;

    ClanService clanService;

    @BeforeEach
    public void setUp() {
        clanRepository = spy(ClanRepository.class);
        setUpMocks();
        clanService = new ClanService(clanRepository);
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
    public void testGetClans() {
        ResponseDTO response = clanService.getClans();
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertNotNull(response.getBody());
        if (response.getBody() instanceof List<?>) {
            final List<?> listValue = (List<?>) response.getBody();
            assertNotNull(listValue);
            assertEquals(3, listValue.size());
            if (listValue.get(0) instanceof ClanEntity) {
                ClanEntity clan = (ClanEntity) listValue.get(0);
                assertNotNull(clan.getDescriptionEN());
                verify(clanRepository, times(1)).findAll();
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
    public void testGetClanOk() {
        ResponseDTO response = clanService.getClan(1L);
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertNotNull(response.getBody());
        ClanEntity entity = (ClanEntity) response.getBody();
        assertNotNull(entity.getDescriptionEN());
        verify(clanRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetClanWithMissingId() {
        ResponseDTO response = clanService.getClan(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
        verify(clanRepository, times(0)).findById(any());
    }

    @Test
    public void testGetClanWithNotExistingId() {
        ResponseDTO response = clanService.getClan(5646L);
        assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
        assertNull(response.getBody());
        verify(clanRepository, times(1)).findById(5646L);
    }

    @Test
    public void testRemoveClanOk() {
        ResponseDTO response = clanService.removeClan(1L);
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertNotNull(response.getBody());
        verify(clanRepository, times(1)).findById(1L);
    }

    @Test
    public void testRemoveClanWithMissingId() {
        ResponseDTO response = clanService.removeClan(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
        verify(clanRepository, times(0)).findById(any());
    }

    @Test
    public void testRemoveClanWithNotExistingId() {
        ResponseDTO response = clanService.removeClan(454L);
        assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
        assertNull(response.getBody());
        verify(clanRepository, times(1)).findById(454L);
    }

    @Test
    public void testCreateClanOk() {
        ResponseDTO response = clanService.createClan(JsonMock.getClan());
        assertEquals(HttpStatus.CREATED, response.getHttpStatus());
        assertNotNull(response.getBody());
        verify(clanRepository, times(1)).save(any());
    }

    @Test
    public void testCreateClanWithMissingBody() {
        ResponseDTO response = clanService.createClan(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
        verify(clanRepository, times(0)).save(any());
    }

    @Test
    public void testCreateClanWithBadBody() {
        ResponseDTO response = clanService.createClan(JsonMock.getBadClan());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
        verify(clanRepository, times(0)).save(any());
    }

    @Test
    public void testUpdateClanOk() {
        ResponseDTO response = clanService.updateClan(JsonMock.getClan());
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertNotNull(response.getBody());
        verify(clanRepository, times(1)).save(any());
    }

    @Test
    public void testUpdateClanWithMissingBody() {
        ResponseDTO response = clanService.updateClan(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
        verify(clanRepository, times(0)).save(any());
    }

    @Test
    public void testUpdateClanWithEmptyBody() {
        ResponseDTO response = clanService.updateClan("{}");
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
        verify(clanRepository, times(0)).save(any());
    }

    @Test
    public void testUpdateClanWithBadBody() {
        ResponseDTO response = clanService.updateClan(JsonMock.getBadClan());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
        verify(clanRepository, times(0)).save(any());
    }

    @Test
    public void testUpdateClanWithNotExistingId() {
        ResponseDTO response = clanService.updateClan(JsonMock.getNotExistingClan());
        assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
        assertNull(response.getBody());
        verify(clanRepository, times(0)).save(any());
    }
}