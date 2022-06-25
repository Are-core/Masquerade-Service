package com.masquerade.controller.parameter;

import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.entity.parameter.ArchetypeEntity;
import com.masquerade.service.parameter.ArchetypeService;
import com.masquerade.tools.Section;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = Section.CrossOriginUrl)
@RestController
public class ArchetypeController {
    private final ArchetypeService archetypeService;

    public ArchetypeController(ArchetypeService archetypeService) {
        this.archetypeService = archetypeService;
    }

    @RequestMapping(value = Section.ArchetypePrefix + "/getArchetypes",method = RequestMethod.GET)
    public ResponseEntity<List<ArchetypeEntity>> getArchetypes() {
        return new ResponseEntity<>(archetypeService.getArchetypes(), HttpStatus.OK);
    }

    @RequestMapping(value = Section.ArchetypePrefix + "/getArchetypeById",method = RequestMethod.GET)
    public ResponseEntity<ArchetypeEntity> getArchetype(Long id) throws BadRequestException {
        return new ResponseEntity<>(archetypeService.getArchetype(id), HttpStatus.OK);
    }

    @RequestMapping(value = Section.ArchetypePrefix + "/removeArchetype",method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> removeArchetype(Long id) throws BadRequestException {
        return archetypeService.removeArchetype(id);
    }

    @PostMapping(value = Section.ArchetypePrefix + "/createArchetype", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> createArchetype(@RequestBody String rawArchetype) throws BadRequestException {
        return archetypeService.createArchetype(rawArchetype);
    }

    @PostMapping(value = Section.ArchetypePrefix + "/updateArchetype", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> updateArchetype(@RequestBody String rawArchetype) throws EntityRequestException {
        return archetypeService.updateArchetype(rawArchetype);
    }

    @PostMapping(value = Section.ArchetypePrefix + "/updateArchetypes", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> updateArchetypes(@RequestBody String rawArchetype) throws EntityRequestException {
        return archetypeService.updateArchetypes(rawArchetype);
    }
}
