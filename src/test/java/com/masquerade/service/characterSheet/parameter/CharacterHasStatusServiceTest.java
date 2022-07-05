package com.masquerade.service.characterSheet.parameter;

import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.entity.characterSheet.CharacterEntity;
import com.masquerade.model.entity.characterSheet.parameter.CharacterHasStatusEntity;
import com.masquerade.model.entity.characterSheet.parameter.CharacterHasStatusKey;
import com.masquerade.model.entity.characterSheet.parameter.StatusEntity;
import com.masquerade.repository.characterSheet.parameter.CharacterHasStatusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;

class CharacterHasStatusServiceTest {

    @Mock
    CharacterHasStatusService characterHasStatusService;

    CharacterHasStatusRepository characterHasStatusRepository;

    @BeforeEach
    public void setUp() {
        characterHasStatusRepository = spy(CharacterHasStatusRepository.class);
        setUpMocks();
        characterHasStatusService = new CharacterHasStatusService(characterHasStatusRepository);
    }

    private void setUpMocks(){
        List<CharacterHasStatusEntity> fullList = new ArrayList<>();
        List<CharacterHasStatusEntity> secondList = new ArrayList<>();
        CharacterHasStatusEntity entity = new CharacterHasStatusEntity();
        CharacterEntity char1 = new CharacterEntity();
        char1.setId(1L);
        CharacterEntity char2 = new CharacterEntity();
        char2.setId(2L);
        StatusEntity status = new StatusEntity();
        status.setId(4L);
        entity.setId(new CharacterHasStatusKey(1L, 4L));
        entity.setCharacter(char1);
        entity.setStatus(status);
        fullList.add(entity);
        entity.setId(new CharacterHasStatusKey(2L, 8L));
        entity.setCharacter(char2);
        status.setId(8L);
        entity.setStatus(status);
        fullList.add(entity);
        secondList.add(entity);
        entity.setId(new CharacterHasStatusKey(2L, 1L));
        entity.setCharacter(char2);
        status.setId(1L);
        entity.setStatus(status);
        fullList.add(entity);
        secondList.add(entity);
        List<CharacterHasStatusEntity> badList = new ArrayList<>();
        badList.add(new CharacterHasStatusEntity());
        Mockito.when(characterHasStatusRepository.existsById(1L)).thenReturn(true);
        Mockito.when(characterHasStatusRepository.findByCharacterId(2L)).thenReturn(secondList);
        Mockito.when(characterHasStatusRepository.findByCharacterId(5L)).thenReturn(badList);
        Mockito.when(characterHasStatusRepository.findAll()).thenReturn(fullList);
        Mockito.when(characterHasStatusRepository.save(any())).thenReturn(entity);
    }

    @Test
    public void testGetStatusTypes(){
        ResponseDTO response = characterHasStatusService.getDeclaredStatus();
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetStatusTypesByCharacter(){
        ResponseDTO response = characterHasStatusService.getCharacterStatus(2L);
        assertEquals(HttpStatus.OK, response.getHttpStatus());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetStatusTypesByCharacterWithMissingId(){
        ResponseDTO response = characterHasStatusService.getCharacterStatus(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testGetStatusTypesByCharacterWithNotExistingId(){
        ResponseDTO response = characterHasStatusService.getCharacterStatus(454L);
        assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
        assertNull(response.getBody());
    }

    @Test
    public void testGetStatusTypesByCharacterWithBadRecord(){
        ResponseDTO response = characterHasStatusService.getCharacterStatus(5L);
        assertEquals(HttpStatus.NOT_FOUND, response.getHttpStatus());
        assertNull(response.getBody());
    }
}