package com.masquerade.controller.parameter;

import com.masquerade.exception.BadRequestException;
import com.masquerade.model.parameter.ArchetypeEntity;
import com.masquerade.service.parameter.ArchetypeService;
import com.masquerade.tools.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value ="/parameter/addArchetype",method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> addArchetype(String name, String note, String language) {
        archetypeService.addArchetype(name, note, Util.getLanguage(language));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value ="/parameter/removeArchetype",method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> removeArchetype(Long id) throws BadRequestException {
        archetypeService.removeArchetype(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value ="/parameter/modifyArchetype",method = RequestMethod.PUT)
    public ResponseEntity<HttpStatus> modifyArchetype(Long id, String name, String note, String language) throws BadRequestException {
        archetypeService.modifyArchetype(id, name, note, Util.getLanguage(language));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
