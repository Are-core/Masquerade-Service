package com.masquerade.controller.characterSheet;

import com.masquerade.exception.BadRequestException;
import com.masquerade.model.dto.CharacterListItemDTO;
import com.masquerade.model.entity.characterSheet.CharacterEntity;
import com.masquerade.service.characterSheet.CharacterService;
import com.masquerade.tools.Section;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = Section.CrossOriginUrl)
@RestController
public class CharacterController {
    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @RequestMapping(value = Section.CharacterPrefix + "/characters",method = RequestMethod.GET)
    public ResponseEntity<List<CharacterListItemDTO>> getEntities() {
        return new ResponseEntity<>(characterService.getList(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = Section.CharacterPrefix + "character/{id}")
    public ResponseEntity<CharacterEntity> get(@PathVariable Long id) throws BadRequestException {
        return new ResponseEntity<>(characterService.getById(id), HttpStatus.OK);
    }
}
