package com.masquerade.controller.characterSheet;

import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.dto.controller.ResponseEntityDTO;
import com.masquerade.model.entity.characterSheet.CharacterEntity;
import com.masquerade.model.entity.characterSheet.global.ClanEntity;
import com.masquerade.service.characterSheet.CharacterService;
import com.masquerade.service.characterSheet.skill.CharacterHasSkillService;
import com.masquerade.service.repository.characterSheet.CharacterRepository;
import com.masquerade.service.repository.characterSheet.skill.CharacterHasSkillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;

class CharacterControllerTest {
    @Mock
    CharacterRepository characterRepository;

    @Mock
    CharacterHasSkillRepository characterHasSkillRepository;

    CharacterService characterService;

    CharacterHasSkillService characterHasSkillService;

    CharacterController characterController;

    @BeforeEach
    public void setUp() {
        characterRepository = spy(CharacterRepository.class);
        characterHasSkillRepository = spy(CharacterHasSkillRepository.class);
        characterService = new CharacterService(characterRepository);
        characterHasSkillService = new CharacterHasSkillService(characterHasSkillRepository);
        setUpMocks();
        characterController = new CharacterController(characterService, characterHasSkillService);
    }

    private void setUpMocks(){
        List<CharacterEntity> characterEntities = new ArrayList<>();
        CharacterEntity character = new CharacterEntity();
        character.setId(1L);
        character.setNpc(false);
        character.setArchived(false);
        character.setPlayer("Player 1");
        character.setName("Paul");
        characterEntities.add(character);
        character.setId(2L);
        character.setNpc(false);
        character.setArchived(false);
        character.setPlayer("Player 2");
        character.setName("Robert");
        characterEntities.add(character);
        Mockito.when(characterRepository.findAll()).thenReturn(characterEntities);

        character.setId(1L);
        character.setNpc(false);
        character.setArchived(false);
        character.setPlayer("Player 1");
        character.setName("Paul");
        Mockito.when(characterRepository.findById(1L)).thenReturn(Optional.of(character));

        character.setId(5L);
        character.setNpc(false);
        character.setArchived(false);
        character.setPlayer("Player 1");
        character.setName("Paul");
        Mockito.when(characterRepository.findById(5L)).thenReturn(Optional.of(character));
    }

    @Test
    public void testGetEntities() {
        ResponseEntityDTO<ResponseDTO> response = characterController.getEntities();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGet() {
        ResponseEntityDTO<ResponseDTO> response = characterController.get(1L);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetWithNotExistingId() {
        ResponseEntityDTO<ResponseDTO> response = characterController.get(8L);
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void testGetWithMissingId() {
        ResponseEntityDTO<ResponseDTO> response = characterController.get(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}