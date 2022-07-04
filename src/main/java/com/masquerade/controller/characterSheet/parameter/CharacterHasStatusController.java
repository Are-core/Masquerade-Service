package com.masquerade.controller.characterSheet.parameter;

import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.dto.controller.ResponseEntityDTO;
import com.masquerade.service.characterSheet.parameter.CharacterHasStatusService;
import com.masquerade.tools.controller.Section;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = Section.CrossOriginUrl)
@RestController
public class CharacterHasStatusController {
    private final CharacterHasStatusService characterHasStatusService;

    public CharacterHasStatusController(CharacterHasStatusService characterHasStatusService) {
        this.characterHasStatusService = characterHasStatusService;
    }

    @RequestMapping(value = Section.CharacterHasStatusPrefix + "/getDeclaredStatus",method = RequestMethod.GET)
    public ResponseEntityDTO<ResponseDTO> getDeclaredSkills() {
        return new ResponseEntityDTO<>(characterHasStatusService.getDeclaredStatus());
    }

    @RequestMapping(value = Section.CharacterHasStatusPrefix + "/getCharacterStatus",method = RequestMethod.GET)
    public ResponseEntityDTO<ResponseDTO> getCharacterSkills(Long id) {
        return new ResponseEntityDTO<>(characterHasStatusService.getCharacterStatus(id));
    }

    //TODO Add remove, create (Update?) services
}
