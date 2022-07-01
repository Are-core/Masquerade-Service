package com.masquerade.controller.characterSheet.parameter;

import com.masquerade.exception.BadRequestException;
import com.masquerade.model.dto.characterSheet.parameter.CharacterStatusDTO;
import com.masquerade.service.characterSheet.parameter.CharacterHasStatusService;
import com.masquerade.tools.Section;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = Section.CrossOriginUrl)
@RestController
public class CharacterHasStatusController {
    private final CharacterHasStatusService characterHasStatusService;

    public CharacterHasStatusController(CharacterHasStatusService characterHasStatusService) {
        this.characterHasStatusService = characterHasStatusService;
    }

    @RequestMapping(value = Section.CharacterHasStatusPrefix + "/getDeclaredStatus",method = RequestMethod.GET)
    public ResponseEntity<List<CharacterStatusDTO>> getDeclaredSkills() {
        return new ResponseEntity<>(characterHasStatusService.getDeclaredStatus(), HttpStatus.OK);
    }

    @RequestMapping(value = Section.CharacterHasStatusPrefix + "/getCharacterStatus",method = RequestMethod.GET)
    public ResponseEntity<List<CharacterStatusDTO>> getCharacterSkills(Long id) throws BadRequestException {
        return new ResponseEntity<>(characterHasStatusService.getCharacterStatus(id), HttpStatus.OK);
    }

}
