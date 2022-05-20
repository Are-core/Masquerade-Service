package com.masquerade.controller;

import com.masquerade.exception.BadRequestException;
import com.masquerade.model.ArchetypeEntity;
import com.masquerade.service.ArchetypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ParameterController {
    private final ArchetypeService archetypeService;

    public ParameterController(ArchetypeService archetypeService) {
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

    @RequestMapping(value ="/parameter/addArchetype",method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> addArchetype(String name) {
        archetypeService.addArchetype(name);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value ="/parameter/removeArchetype",method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> removeArchetype(Long id) throws BadRequestException {
        archetypeService.removeArchetype(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value ="/parameter/modifyArchetype",method = RequestMethod.PUT)
    public ResponseEntity<HttpStatus> modifyArchetype(Long id, String name) throws BadRequestException {
        archetypeService.modifyArchetype(id, name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
