package com.masquerade.service.characterSheet.skill;

import com.masquerade.mocks.json.JsonMock;
import com.masquerade.model.dto.characterSheet.skill.CharacterSkillsDTO;
import com.masquerade.model.dto.characterSheet.skill.DeclaredSkillDTO;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.entity.characterSheet.CharacterEntity;
import com.masquerade.model.entity.characterSheet.skill.CharacterHasSkillEntity;
import com.masquerade.model.entity.characterSheet.skill.CharacterHasSkillKey;
import com.masquerade.model.entity.characterSheet.skill.SkillEntity;
import com.masquerade.repository.characterSheet.skill.CharacterHasSkillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

class CharacterHasSkillServiceTest {
    @Mock
    CharacterHasSkillRepository characterHasSkillRepository;

    CharacterHasSkillService skillSpecializationService;

    @BeforeEach
    public void setUp() {
        characterHasSkillRepository = spy(CharacterHasSkillRepository.class);
        setUpMocks();
        skillSpecializationService = new CharacterHasSkillService(characterHasSkillRepository);
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
    public void getAllDeclaredSkills() {
        ResponseDTO response = skillSpecializationService.getDeclaredSkills();
        if (response.getBody() instanceof List<?>) {
            final List<?> listValue = (List<?>) response.getBody();
            assertNotNull(listValue);
            assertFalse(listValue.isEmpty());
            if (listValue.get(0) instanceof CharacterSkillsDTO) {
                CharacterSkillsDTO characterSkill = (CharacterSkillsDTO) listValue.get(0);
                assertNotNull(characterSkill);
            }
        }
    }

    @Test
    public void getDeclaredSkillByCharacter() {
        ResponseDTO response = skillSpecializationService.getCharacterSkills(1L);
        if (response.getBody() instanceof List<?>) {
            final List<?> listValue = (List<?>) response.getBody();
            assertNotNull(listValue);
            assertFalse(listValue.isEmpty());
            if (listValue.get(0) instanceof CharacterSkillsDTO) {
                CharacterSkillsDTO characterSkill = (CharacterSkillsDTO) listValue.get(0);
                assertNotNull(characterSkill);
            }
        }
    }

    @Test
    public void getDeclaredSkillByCharacterWithMissingId() {
        ResponseDTO response = skillSpecializationService.getCharacterSkills(null);
        assertNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }

    @Test
    public void getDeclaredSkillByCharacterEmptyList() {
        ResponseDTO response = skillSpecializationService.getCharacterSkills(9L);
        assertNull(response.getBody());
        assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
    }

    @Test
    public void testSetSkill() {
        ResponseDTO response = skillSpecializationService.setSkillForCharacter(JsonMock.getCharacterSkill());
        assertEquals(HttpStatus.CREATED, response.getHttpStatus());
    }

    @Test
    public void testSetSkillWithMissingBody() {
        ResponseDTO response = skillSpecializationService.setSkillForCharacter(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }

    @Test
    public void testSetSkillWithBadBody() {
        ResponseDTO response = skillSpecializationService.setSkillForCharacter(JsonMock.getBadCharacterSkill());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }

    @Test
    public void testSetSkillWithMissingIds() {
        ResponseDTO response = skillSpecializationService.setSkillForCharacter(JsonMock.getMissingIdCharacterSkill());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }

    @Test
    public void testRemoveSkill() {
        ResponseDTO response = skillSpecializationService.removeSkillForCharacter(1L, 1L);
        assertEquals(HttpStatus.OK, response.getHttpStatus());
    }

    @Test
    public void testRemoveSkillBadIds() {
        ResponseDTO response = skillSpecializationService.removeSkillForCharacter(1L, 2L);
        assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
    }

    @Test
    public void testRemoveSkillMissingCharacterId() {
        ResponseDTO response = skillSpecializationService.removeSkillForCharacter(null, 1L);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }

    @Test
    public void testRemoveSkillMissingSkillId() {
        ResponseDTO response = skillSpecializationService.removeSkillForCharacter(1L, null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }

    @Test
    public void testUpdateSkill() {
        ResponseDTO response = skillSpecializationService.updateSkillForCharacter(JsonMock.getSkillCharacter());
        assertEquals(HttpStatus.OK, response.getHttpStatus());
    }

    @Test
    public void testUpdateSkillWithMissingBody() {
        ResponseDTO response = skillSpecializationService.updateSkillForCharacter(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }

    @Test
    public void testUpdateSkillWithMissingIds() {
        ResponseDTO response = skillSpecializationService.updateSkillForCharacter(JsonMock.getSkillCharacterMissingId());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }

    @Test
    public void testUpdateSkillWithNoMatch() {
        ResponseDTO response = skillSpecializationService.updateSkillForCharacter(JsonMock.getNoMatchSkillCharacter());
        assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
    }

    @Test
    public void testUpdateSkillWithBadBody() {
        ResponseDTO response = skillSpecializationService.updateSkillForCharacter(JsonMock.getBadSkillCharacter());
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }

    @Test
    public void testGetCharacterSkillList() {
        List<DeclaredSkillDTO> list = skillSpecializationService.getCharacterSkillList(1L);
        assertNotNull(list);
        assertFalse(list.isEmpty());
    }

    @Test
    public void testGetCharacterSkillListBadId() {
        List<DeclaredSkillDTO> list = skillSpecializationService.getCharacterSkillList(15L);
        assertNull(list);
    }

    @Test
    public void testGetCharacterSkillListMissingId() {
        List<DeclaredSkillDTO> list = skillSpecializationService.getCharacterSkillList(null);
        assertNull(list);
    }

    @Test
    public void testGetCharacterSkillListBadList() {
        List<DeclaredSkillDTO> list = skillSpecializationService.getCharacterSkillList(91L);
        assertNull(list);
    }
}