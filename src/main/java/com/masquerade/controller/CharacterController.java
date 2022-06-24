package com.masquerade.controller;

import com.masquerade.exception.BadRequestException;
import com.masquerade.model.entity.CharacterEntity;
import com.masquerade.model.entity.SimpleCharacterEntity;
import com.masquerade.service.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CharacterController {
    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @RequestMapping(value ="/getEntities",method = RequestMethod.GET)
    public ResponseEntity<List<SimpleCharacterEntity>> getEntities() {
        return new ResponseEntity<>(characterService.getList(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "character/{id}")
    public ResponseEntity<CharacterEntity> get(@PathVariable Long id) throws BadRequestException {
        return new ResponseEntity<>(characterService.getById(id), HttpStatus.OK);
    }
}
