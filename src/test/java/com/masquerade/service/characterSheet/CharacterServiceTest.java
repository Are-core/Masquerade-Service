package com.masquerade.service.characterSheet;

import com.masquerade.exception.BadRequestException;
import com.masquerade.model.dto.CharacterListItemDTO;
import com.masquerade.model.entity.characterSheet.CharacterEntity;
import com.masquerade.repository.characterSheet.CharacterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class CharacterServiceTest {

    @Mock
    CharacterRepository characterRepository;

    CharacterService characterService;

    @BeforeEach
    public void setUp() {
        characterRepository = spy(CharacterRepository.class);
        setUpMocks();
        characterService = new CharacterService(characterRepository);
    }

    private void setUpMocks() {
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

    /* getList */

    @Test
    void characterListOk() {
        List<CharacterListItemDTO> list = characterService.getList();
        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(2, list.size());
        assertNotNull(list.get(0).getName());
        assertNotNull(list.get(0).getPlayer());
        verify(characterRepository, times(1)).findAll();
    }


    /* getById */

    @Test
    void getByIdOk()  {
        try {
            CharacterEntity entity = characterService.getById(5L);
            assertNotNull(entity);
            assertNotNull(entity.getName());
            assertNotNull(entity.getPlayer());
            verify(characterRepository, times(1)).findById(5L);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getByIdWithIncorrectId()  {
        try {
            CharacterEntity entity = characterService.getById(8L);
            fail();
        }
        catch (IllegalArgumentException i) {
            verify(characterRepository, times(1)).findById(8L);
            assertTrue(true);
        }
        catch (Exception e) {
            fail();
        }
    }

    @Test
    void getByIdMissingParameter() {
        try {
            characterService.getById(null);
            fail();
        } catch (BadRequestException e) {
            verify(characterRepository, times(0)).findById(any());
            assertTrue(true);
        }
    }
}