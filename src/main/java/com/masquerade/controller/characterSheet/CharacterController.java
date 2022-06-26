package com.masquerade.controller.characterSheet;

import com.masquerade.exception.BadRequestException;
import com.masquerade.model.dto.CharacterListItemDTO;
import com.masquerade.model.dto.CharacterSheetDTO;
import com.masquerade.model.dto.skill.DeclaredSkillDTO;
import com.masquerade.model.entity.characterSheet.CharacterEntity;
import com.masquerade.service.characterSheet.CharacterService;
import com.masquerade.service.characterSheet.skill.CharacterHasSkillService;
import com.masquerade.tools.Section;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = Section.CrossOriginUrl)
@RestController
public class CharacterController {
    private final CharacterService characterService;
    private final CharacterHasSkillService characterHasSkillService;

    public CharacterController(CharacterService characterService, CharacterHasSkillService characterHasSkillService) {
        this.characterService = characterService;
        this.characterHasSkillService = characterHasSkillService;
    }

    @RequestMapping(value = Section.CharacterPrefix + "/characters",method = RequestMethod.GET)
    public ResponseEntity<List<CharacterListItemDTO>> getEntities() {
        return new ResponseEntity<>(characterService.getList(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = Section.CharacterPrefix + "character/{id}")
    public ResponseEntity<CharacterSheetDTO> get(@PathVariable Long id) throws BadRequestException {
        return new ResponseEntity<>(setRelations(characterService.getById(id)), HttpStatus.OK);
    }

    private CharacterSheetDTO setRelations(CharacterEntity character) throws BadRequestException {
        CharacterSheetDTO characterSheet = new CharacterSheetDTO(character);
        characterSheet.setSkills(getSkills(character.getId()));
        return characterSheet;
    }

    private List<DeclaredSkillDTO> getSkills(Long characterId) throws BadRequestException {
        return characterHasSkillService.getCharacterSkillList(characterId);
    }
}
