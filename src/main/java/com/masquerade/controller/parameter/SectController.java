package com.masquerade.controller.parameter;

import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.entity.parameter.SectEntity;
import com.masquerade.service.parameter.SectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SectController {

    private final String ServicePrefix = "/parameter";
    private final SectService sectService;

    public SectController(SectService sectService) { this.sectService = sectService; }

    @RequestMapping(value = ServicePrefix + "/getSects",method = RequestMethod.GET)
    public ResponseEntity<List<SectEntity>> getArchetypes() {
        return new ResponseEntity<>(sectService.getSects(), HttpStatus.OK);
    }

    @RequestMapping(value = ServicePrefix + "/getSectById",method = RequestMethod.GET)
    public ResponseEntity<SectEntity> getArchetype(Long id) throws BadRequestException {
        return new ResponseEntity<>(sectService.getSect(id), HttpStatus.OK);
    }

    @RequestMapping(value = ServicePrefix + "/removeSect",method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> removeArchetype(Long id) throws BadRequestException {
        return sectService.removeSect(id);
    }

    @PostMapping(value = ServicePrefix + "/createSect", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> createSect(@RequestBody String rawSect) throws BadRequestException {
        return sectService.createSect(rawSect);
    }

    @PostMapping(value = ServicePrefix + "/updateSect", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> updateSect(@RequestBody String rawSect) throws EntityRequestException {
        return sectService.updateSect(rawSect);
    }
}
