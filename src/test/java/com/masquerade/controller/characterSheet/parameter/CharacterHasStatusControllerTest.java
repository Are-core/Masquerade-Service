package com.masquerade.controller.characterSheet.parameter;

import com.masquerade.mocks.json.JsonMock;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.dto.controller.ResponseEntityDTO;
import com.masquerade.model.entity.characterSheet.CharacterEntity;
import com.masquerade.model.entity.characterSheet.parameter.CharacterHasStatusEntity;
import com.masquerade.model.entity.characterSheet.parameter.CharacterHasStatusKey;
import com.masquerade.model.entity.characterSheet.parameter.StatusEntity;
import com.masquerade.service.characterSheet.parameter.CharacterHasStatusService;
import com.masquerade.service.repository.characterSheet.parameter.CharacterHasStatusRepository;
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

class CharacterHasStatusControllerTest {
    @Mock
    CharacterHasStatusService characterHasStatusService;

    CharacterHasStatusRepository characterHasStatusRepository;

    CharacterHasStatusController characterHasStatusController;

    @BeforeEach
    public void setUp() {
        characterHasStatusRepository = spy(CharacterHasStatusRepository.class);
        setUpMocks();
        characterHasStatusService = new CharacterHasStatusService(characterHasStatusRepository);
        characterHasStatusController = new CharacterHasStatusController(characterHasStatusService);
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
        Mockito.when(characterHasStatusRepository.findByCharacterId(1L)).thenReturn(secondList);
        Mockito.when(characterHasStatusRepository.findByCharacterId(5L)).thenReturn(badList);
        Mockito.when(characterHasStatusRepository.findAll()).thenReturn(fullList);
        Mockito.when(characterHasStatusRepository.save(any())).thenReturn(entity);
    }

    @Test
    public void testGetSkills() {
        ResponseEntityDTO<ResponseDTO> response = characterHasStatusController.getDeclaredStatus();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetSkillOk() {
        ResponseEntityDTO<ResponseDTO> response = characterHasStatusController.getCharacterStatus(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetSkillWithMissingId() {
        ResponseEntityDTO<ResponseDTO> response = characterHasStatusController.getCharacterStatus(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testGetSkillWithNotExistingId() {
        ResponseEntityDTO<ResponseDTO> response = characterHasStatusController.getCharacterStatus(85654L);
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }


}