package com.masquerade.controller.parameter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.parameter.ArchetypeEntity;
import com.masquerade.service.parameter.ArchetypeService;
import com.masquerade.tools.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ArchetypeController {
    private final ArchetypeService archetypeService;

    public ArchetypeController(ArchetypeService archetypeService) {
        this.archetypeService = archetypeService;
    }

    @RequestMapping(value ="/parameter/getArchetypes",method = RequestMethod.GET)
    public ResponseEntity<List<ArchetypeEntity>> getArchetypes() {
        return new ResponseEntity<>(archetypeService.getArchetypes(), HttpStatus.OK);
    }

    @RequestMapping(value ="/parameter/getArchetypeById",method = RequestMethod.GET)
    public ResponseEntity<ArchetypeEntity> getArchetype(Long id) throws BadRequestException {
        return new ResponseEntity<>(archetypeService.getArchetype(id), HttpStatus.OK);
    }

    @RequestMapping(value ="/parameter/getArchetypeByName",method = RequestMethod.GET)
    public ResponseEntity<List<ArchetypeEntity>> getArchetype(String name) {
        return new ResponseEntity<>(archetypeService.getArchetype(name), HttpStatus.OK);
    }

    @RequestMapping(value ="/parameter/removeArchetype",method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> removeArchetype(Long id) throws BadRequestException {
        archetypeService.removeArchetype(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value ="/parameter/createArchetype", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> createArchetype(@RequestBody String rawArchetype) throws BadRequestException {
        archetypeService.saveUpdateArchetype(rawArchetype);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value ="/parameter/updateArchetype", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> updateArchetype(@RequestBody String rawArchetype) throws BadRequestException, EntityRequestException {
        archetypeService.updateArchetype(rawArchetype);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value ="/parameter/updateArchetypes", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> updateArchetypes(@RequestBody String rawArchetype) throws BadRequestException, EntityRequestException {
        archetypeService.updateArchetypes(rawArchetype);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
