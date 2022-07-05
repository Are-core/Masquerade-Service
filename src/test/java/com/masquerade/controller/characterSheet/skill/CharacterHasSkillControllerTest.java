package com.masquerade.controller.characterSheet.skill;

import com.masquerade.mocks.json.JsonMock;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.dto.controller.ResponseEntityDTO;
import com.masquerade.model.entity.characterSheet.CharacterEntity;
import com.masquerade.model.entity.characterSheet.skill.CharacterHasSkillEntity;
import com.masquerade.model.entity.characterSheet.skill.CharacterHasSkillKey;
import com.masquerade.model.entity.characterSheet.skill.SkillEntity;
import com.masquerade.service.characterSheet.skill.CharacterHasSkillService;
import com.masquerade.repository.characterSheet.skill.CharacterHasSkillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;

class CharacterHasSkillControllerTest {
    @Mock
    CharacterHasSkillRepository characterHasSkillRepository;

    CharacterHasSkillService skillSpecializationService;

    CharacterHasSkillController characterHasSkillController;

    @BeforeEach
    public void setUp() {
        characterHasSkillRepository = spy(CharacterHasSkillRepository.class);
        setUpMocks();
        skillSpecializationService = new CharacterHasSkillService(characterHasSkillRepository);
        characterHasSkillController = new CharacterHasSkillController(skillSpecializationService);
    }

    private void setUpMocks(){
        List<CharacterHasSkillEntity> list = new ArrayList<>();
        CharacterHasSkillEntity characterSkill = new CharacterHasSkillEntity();
        CharacterEntity character = new CharacterEntity();
        SkillEntity skill = new SkillEntity();
        skill.setId(1L);
        character.setId(1L);
        characterSkill.setId(new CharacterHasSkillKey(1L, 1L));
        characterSkill.setCharacter(character);
        characterSkill.setLevel(5);
        characterSkill.setSkill(skill);
        Mockito.when(characterHasSkillRepository.findByCharacterIdAndSkillId(1L, 1L)).thenReturn(characterSkill);
        Mockito.when(characterHasSkillRepository.existsByCharacterIdAndSkillId(2L, 1L)).thenReturn(true);
        list.add(characterSkill);
        characterSkill.setId(new CharacterHasSkillKey(1L, 5L));
        skill.setId(5L);
        characterSkill.setSkill(skill);
        characterSkill.setLevel(3);
        list.add(characterSkill);
        Mockito.when(characterHasSkillRepository.findAll()).thenReturn(list);
        Mockito.when(characterHasSkillRepository.findById(1L)).thenReturn(Optional.of(characterSkill));
        Mockito.when(characterHasSkillRepository.findByCharacterId(1L)).thenReturn(list);
        Mockito.when(characterHasSkillRepository.existsById(1L)).thenReturn(true);
        Mockito.when(characterHasSkillRepository.existsById(5L)).thenReturn(false);
        characterSkill.setId(null);
        Mockito.when(characterHasSkillRepository.save(characterSkill)).thenReturn(characterSkill);

        characterSkill.setId(new CharacterHasSkillKey(null, null));
        list.add(characterSkill);
        Mockito.when(characterHasSkillRepository.findByCharacterId(91L)).thenReturn(list);
    }

    @Test
    public void testGetSkills() {
        ResponseEntityDTO<ResponseDTO> response = characterHasSkillController.getDeclaredSkills();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetSkillOk() {
        ResponseEntityDTO<ResponseDTO> response = characterHasSkillController.getCharacterSkills(1L);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetSkillWithMissingId() {
        ResponseEntityDTO<ResponseDTO> response = characterHasSkillController.getCharacterSkills(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testGetSkillWithNotExistingId() {
        ResponseEntityDTO<ResponseDTO> response = characterHasSkillController.getCharacterSkills(85654L);
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void testRemoveSkillOk() {
        ResponseEntityDTO<ResponseDTO> response = characterHasSkillController.removeSkillForCharacter(1L, 1L);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testRemoveSkillWithMissingId() {
        ResponseEntityDTO<ResponseDTO> response = characterHasSkillController.removeSkillForCharacter(null, null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testRemoveSkillWithNotExistingId() {
        ResponseEntityDTO<ResponseDTO> response = characterHasSkillController.removeSkillForCharacter(564561L, 5L);
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void testCreateSkillOk() {
        ResponseEntityDTO<ResponseDTO> response = characterHasSkillController.setSkillForCharacter(JsonMock.getSkillCharacter());
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testCreateSkillWithMissingBody() {
        ResponseEntityDTO<ResponseDTO> response = characterHasSkillController.setSkillForCharacter(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testCreateSkillWithBadBody() {
        ResponseEntityDTO<ResponseDTO> response = characterHasSkillController.setSkillForCharacter(JsonMock.getBadSkillSpecializationJson());
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateSkillOk() {
        ResponseEntityDTO<ResponseDTO> response = characterHasSkillController.updateSkillForCharacter(JsonMock.getCharacterSkill());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testUpdateSkillWithMissingBody() {
        ResponseEntityDTO<ResponseDTO> response = characterHasSkillController.updateSkillForCharacter(null);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateSkillWithEmptyBody() {
        ResponseEntityDTO<ResponseDTO> response = characterHasSkillController.updateSkillForCharacter("{}");
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateSkillWithBadBody() {
        ResponseEntityDTO<ResponseDTO> response = characterHasSkillController.updateSkillForCharacter(JsonMock.getBadCharacterSkill());
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateSkillWithNotExistingBody() {
        ResponseEntityDTO<ResponseDTO> response = characterHasSkillController.updateSkillForCharacter(JsonMock.getNoMatchSkillCharacter());
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }
}