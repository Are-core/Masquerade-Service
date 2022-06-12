package com.masquerade.controller.parameter;


import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.parameter.JurisdictionEntity;
import com.masquerade.service.parameter.JurisdictionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class JurisdictionController {
    private final String ServicePrefix = "/parameter";
    private final JurisdictionService jurisdictionService;

    public JurisdictionController(JurisdictionService jurisdictionService) {
        this.jurisdictionService = jurisdictionService;
    }

    @RequestMapping(value = ServicePrefix + "/getJurisdictions",method = RequestMethod.GET)
    public ResponseEntity<List<JurisdictionEntity>> getJurisdictions() {
        return new ResponseEntity<>(jurisdictionService.getJurisdictions(), HttpStatus.OK);
    }

    @RequestMapping(value = ServicePrefix + "/getJurisdictionById",method = RequestMethod.GET)
    public ResponseEntity<JurisdictionEntity> getJurisdiction(Long id) throws BadRequestException {
        return new ResponseEntity<>(jurisdictionService.getJurisdiction(id), HttpStatus.OK);
    }

    @RequestMapping(value = ServicePrefix + "/getJurisdictionByName",method = RequestMethod.GET)
    public ResponseEntity<List<JurisdictionEntity>> getJurisdiction(String name) {
        return new ResponseEntity<>(jurisdictionService.getJurisdiction(name), HttpStatus.OK);
    }

    @RequestMapping(value = ServicePrefix + "/removeJurisdiction",method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> removeJurisdiction(Long id) throws BadRequestException {
        return jurisdictionService.removeJurisdiction(id);
    }

    @PostMapping(value = ServicePrefix + "/createJurisdiction", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> createJurisdiction(@RequestBody String rawJurisdiction) throws BadRequestException {
        return jurisdictionService.createJurisdiction(rawJurisdiction);
    }

    @PostMapping(value = ServicePrefix + "/updateJurisdiction", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> updateJurisdiction(@RequestBody String rawJurisdiction) throws EntityRequestException {
        return jurisdictionService.updateJurisdiction(rawJurisdiction);
    }
}
