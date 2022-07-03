package com.masquerade.controller.characterSheet.skill;

import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.dto.controller.ResponseEntityDTO;
import com.masquerade.service.characterSheet.skill.CharacterHasSkillService;
import com.masquerade.tools.controller.Section;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = Section.CrossOriginUrl)
@RestController
public class CharacterHasSkillController {
    private final CharacterHasSkillService characterHasSkillService;

    public CharacterHasSkillController(CharacterHasSkillService characterHasSkillService) {
        this.characterHasSkillService = characterHasSkillService;
    }

    @RequestMapping(value = Section.CharacterHasSkillPrefix + "/getDeclaredSkills",method = RequestMethod.GET)
    public ResponseEntityDTO<ResponseDTO> getDeclaredSkills() {
        return new ResponseEntityDTO<>(characterHasSkillService.getDeclaredSkills());
    }

    @RequestMapping(value = Section.CharacterHasSkillPrefix + "/getCharacterSkills",method = RequestMethod.GET)
    public ResponseEntityDTO<ResponseDTO> getCharacterSkills(Long id) {
        return new ResponseEntityDTO<>(characterHasSkillService.getCharacterSkills(id));
    }

    @PostMapping(value = Section.CharacterHasSkillPrefix + "/setSkillForCharacter", consumes = "application/json", produces = "application/json")
    public ResponseEntityDTO<ResponseDTO> setSkillForCharacter(@RequestBody String rawArchetype) {
        return new ResponseEntityDTO<>(characterHasSkillService.setSkillForCharacter(rawArchetype));
    }

    @PostMapping(value = Section.CharacterHasSkillPrefix + "/updateSkillForCharacter", consumes = "application/json", produces = "application/json")
    public ResponseEntityDTO<ResponseDTO> updateSkillForCharacter(@RequestBody String rawArchetype) {
        return new ResponseEntityDTO<>(characterHasSkillService.updateSkillForCharacter(rawArchetype));
    }

    @RequestMapping(value = Section.CharacterHasSkillPrefix + "/removeSkillForCharacter",method = RequestMethod.DELETE)
    public ResponseEntityDTO<ResponseDTO> removeSkillForCharacter(Long characterId, Long skillId) {
        return new ResponseEntityDTO<>(characterHasSkillService.removeSkillForCharacter(characterId, skillId));
    }

}
