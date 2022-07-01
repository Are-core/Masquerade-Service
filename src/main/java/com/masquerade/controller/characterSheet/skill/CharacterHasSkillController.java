package com.masquerade.controller.characterSheet.skill;

import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.dto.characterSheet.skill.CharacterSkillsDTO;
import com.masquerade.service.characterSheet.skill.CharacterHasSkillService;
import com.masquerade.tools.controller.Section;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = Section.CrossOriginUrl)
@RestController
public class CharacterHasSkillController {
    private final CharacterHasSkillService characterHasSkillService;

    public CharacterHasSkillController(CharacterHasSkillService characterHasSkillService) {
        this.characterHasSkillService = characterHasSkillService;
    }

    @RequestMapping(value = Section.CharacterHasSkillPrefix + "/getDeclaredSkills",method = RequestMethod.GET)
    public ResponseEntity<List<CharacterSkillsDTO>> getDeclaredSkills() {
        return new ResponseEntity<>(characterHasSkillService.getDeclaredSkills(), HttpStatus.OK);
    }

    @RequestMapping(value = Section.CharacterHasSkillPrefix + "/getCharacterSkills",method = RequestMethod.GET)
    public ResponseEntity<List<CharacterSkillsDTO>> getCharacterSkills(Long id) throws BadRequestException {
        return new ResponseEntity<>(characterHasSkillService.getCharacterSkills(id), HttpStatus.OK);
    }

    @PostMapping(value = Section.CharacterHasSkillPrefix + "/setSkillForCharacter", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> setSkillForCharacter(@RequestBody String rawArchetype) throws BadRequestException {
        return characterHasSkillService.setSkillForCharacter(rawArchetype);
    }

    @PostMapping(value = Section.CharacterHasSkillPrefix + "/updateSkillForCharacter", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> updateSkillForCharacter(@RequestBody String rawArchetype) throws EntityRequestException {
        return characterHasSkillService.updateSkillForCharacter(rawArchetype);
    }

    @RequestMapping(value = Section.CharacterHasSkillPrefix + "/removeSkillForCharacter",method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> removeSkillForCharacter(Long characterId, Long skillId) throws BadRequestException {
        return characterHasSkillService.removeSkillForCharacter(characterId, skillId);
    }

}
