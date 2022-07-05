package com.masquerade.service.characterSheet;

import com.masquerade.model.dto.characterSheet.CharacterListItemDTO;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.entity.characterSheet.CharacterEntity;
import com.masquerade.service.repository.characterSheet.CharacterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

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
        ResponseDTO response = characterService.getList();
        if (response.getBody() instanceof List<?>) {
            final List<?> listValue = (List<?>) response.getBody();
            assertNotNull(listValue);
            assertEquals(2, listValue.size());
            if (listValue.get(0) instanceof CharacterListItemDTO) {
                CharacterListItemDTO characterSheet = (CharacterListItemDTO) listValue.get(0);
                assertNotNull(characterSheet);
                assertNotNull(characterSheet.getName());
                assertNotNull(characterSheet.getPlayer());
                verify(characterRepository, times(1)).findAll();
            } else {
                fail();
            }
        } else {
            fail();
        }
    }


    /* getById */

    @Test
    void getByIdOk()  {
        ResponseDTO response = characterService.getById(5L);
        if (response.getBody() instanceof Optional) {
            final Optional<?> optionalValue = (Optional<?>) response.getBody();
            if (optionalValue.isPresent() && optionalValue.get() instanceof CharacterEntity) {
                CharacterEntity characterSheet = (CharacterEntity) optionalValue.get();
                assertNotNull(characterSheet);
                assertNotNull(characterSheet.getName());
                assertNotNull(characterSheet.getPlayer());
                verify(characterRepository, times(1)).findById(5L);
            } else {
                fail();
            }
        } else {
            fail();
        }
    }

    @Test
    void getByIdWithIncorrectId()  {
        ResponseDTO response = characterService.getById(8L);
        if (response.getBody() instanceof Optional) {
            final Optional<?> optionalValue = (Optional<?>) response.getBody();
            if (optionalValue.isEmpty()) {
                verify(characterRepository, times(1)).findById(8L);
                assertSame(response.getHttpStatus(), HttpStatus.OK);
                assertSame(response.getBody(), Optional.empty());
            } else {
                fail();
            }
        }
    }

    @Test
    void getByIdMissingParameter() {
        ResponseDTO response = characterService.getById(null);
        assertSame(response.getHttpStatus(), HttpStatus.BAD_REQUEST);
        verify(characterRepository, times(0)).findById(any());
    }
}