package com.masquerade.controller.parameter;

import com.masquerade.exception.BadRequestException;
import com.masquerade.model.parameter.SectEntity;
import com.masquerade.service.parameter.SectService;
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
public class SectController {
    private final SectService sectService;

    public SectController(SectService sectService) { this.sectService = sectService; }

    @RequestMapping(value ="/parameter/getSects",method = RequestMethod.GET)
    public ResponseEntity<List<SectEntity>> getArchetypes() {
        return new ResponseEntity<>(sectService.getSects(), HttpStatus.OK);
    }

    @RequestMapping(value ="/parameter/getSectById",method = RequestMethod.GET)
    public ResponseEntity<SectEntity> getArchetype(Long id) throws BadRequestException {
        return new ResponseEntity<>(sectService.getSect(id), HttpStatus.OK);
    }

    @RequestMapping(value ="/parameter/addSect",method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> addArchetype(String name) {
        sectService.addSect(name);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value ="/parameter/removeSect",method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> removeArchetype(Long id) throws BadRequestException {
        sectService.removeSect(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value ="/parameter/modifySect",method = RequestMethod.PUT)
    public ResponseEntity<HttpStatus> modifyArchetype(Long id, String description) throws BadRequestException {
        sectService.modifySect(id, description);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
