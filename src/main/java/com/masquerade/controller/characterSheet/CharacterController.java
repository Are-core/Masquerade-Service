package com.masquerade.controller.characterSheet;

import com.masquerade.model.dto.characterSheet.CharacterSheetDTO;
import com.masquerade.model.dto.characterSheet.skill.DeclaredSkillDTO;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.dto.controller.ResponseEntityDTO;
import com.masquerade.model.entity.characterSheet.CharacterEntity;
import com.masquerade.service.characterSheet.CharacterService;
import com.masquerade.service.characterSheet.skill.CharacterHasSkillService;
import com.masquerade.tools.controller.Section;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = Section.CrossOriginUrl)
@RestController
public class CharacterController {
    private final CharacterService characterService;
    private final CharacterHasSkillService characterHasSkillService;

    public CharacterController(CharacterService characterService, CharacterHasSkillService characterHasSkillService) {
        this.characterService = characterService;
        this.characterHasSkillService = characterHasSkillService;
    }

    @RequestMapping(value = Section.CharacterPrefix + "/characters", method = RequestMethod.GET)
    public ResponseEntityDTO<ResponseDTO> getEntities() {
        return new ResponseEntityDTO<>(characterService.getList());
    }

    @RequestMapping(method = RequestMethod.GET, path = Section.CharacterPrefix + "character/{id}")
    public ResponseEntityDTO<ResponseDTO> get(@PathVariable Long id) {
        return new ResponseEntityDTO<>(setRelations(characterService.getById(id), id));
    }

    private ResponseDTO setRelations(ResponseDTO character, Long id) {
        if (character.getBody() instanceof Optional) {
            final Optional<?> optionalValue = (Optional<?>) character.getBody();
            if(optionalValue.isPresent() && optionalValue.get() instanceof CharacterEntity) {
                CharacterSheetDTO characterSheet = new CharacterSheetDTO((CharacterEntity) optionalValue.get());
                characterSheet.setSkills(getSkills(id));
                character.setBody(characterSheet);
                return character;
            }
        }
        return character;
    }

    private List<DeclaredSkillDTO> getSkills(Long characterId) {
        return characterHasSkillService.getCharacterSkillList(characterId);
    }
}
