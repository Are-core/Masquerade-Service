package com.masquerade.controller.parameter;

import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.parameter.ArchetypeEntity;
import com.masquerade.service.parameter.ArchetypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ArchetypeController {
    private final String ServicePrefix = "/parameter";
    private final ArchetypeService archetypeService;

    public ArchetypeController(ArchetypeService archetypeService) {
        this.archetypeService = archetypeService;
    }

    @RequestMapping(value = ServicePrefix + "/getArchetypes",method = RequestMethod.GET)
    public ResponseEntity<List<ArchetypeEntity>> getArchetypes() {
        return new ResponseEntity<>(archetypeService.getArchetypes(), HttpStatus.OK);
    }

    @RequestMapping(value = ServicePrefix + "/getArchetypeById",method = RequestMethod.GET)
    public ResponseEntity<ArchetypeEntity> getArchetype(Long id) throws BadRequestException {
        return new ResponseEntity<>(archetypeService.getArchetype(id), HttpStatus.OK);
    }

    @RequestMapping(value = ServicePrefix + "/removeArchetype",method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> removeArchetype(Long id) throws BadRequestException {
        return archetypeService.removeArchetype(id);
    }

    @PostMapping(value = ServicePrefix + "/createArchetype", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> createArchetype(@RequestBody String rawArchetype) throws BadRequestException {
        return archetypeService.createArchetype(rawArchetype);
    }

    @PostMapping(value = ServicePrefix + "/updateArchetype", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> updateArchetype(@RequestBody String rawArchetype) throws EntityRequestException {
        return archetypeService.updateArchetype(rawArchetype);
    }

    @PostMapping(value = ServicePrefix + "/updateArchetypes", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> updateArchetypes(@RequestBody String rawArchetype) throws EntityRequestException {
        return archetypeService.updateArchetypes(rawArchetype);
    }
}
