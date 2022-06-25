package com.masquerade.controller.characterSheet.parameter;


import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.entity.characterSheet.parameter.JurisdictionEntity;
import com.masquerade.service.characterSheet.parameter.JurisdictionService;
import com.masquerade.tools.Section;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = Section.CrossOriginUrl)
@RestController
public class JurisdictionController {
    private final JurisdictionService jurisdictionService;

    public JurisdictionController(JurisdictionService jurisdictionService) {
        this.jurisdictionService = jurisdictionService;
    }

    @RequestMapping(value = Section.JurisdictionPrefix + "/getJurisdictions",method = RequestMethod.GET)
    public ResponseEntity<List<JurisdictionEntity>> getJurisdictions() {
        return new ResponseEntity<>(jurisdictionService.getJurisdictions(), HttpStatus.OK);
    }

    @RequestMapping(value = Section.JurisdictionPrefix + "/getJurisdictionById",method = RequestMethod.GET)
    public ResponseEntity<JurisdictionEntity> getJurisdiction(Long id) throws BadRequestException {
        return new ResponseEntity<>(jurisdictionService.getJurisdiction(id), HttpStatus.OK);
    }

    @RequestMapping(value = Section.JurisdictionPrefix + "/removeJurisdiction",method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> removeJurisdiction(Long id) throws BadRequestException {
        return jurisdictionService.removeJurisdiction(id);
    }

    @PostMapping(value = Section.JurisdictionPrefix + "/createJurisdiction", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> createJurisdiction(@RequestBody String rawJurisdiction) throws BadRequestException {
        return jurisdictionService.createJurisdiction(rawJurisdiction);
    }

    @PostMapping(value = Section.JurisdictionPrefix + "/updateJurisdiction", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> updateJurisdiction(@RequestBody String rawJurisdiction) throws EntityRequestException {
        return jurisdictionService.updateJurisdiction(rawJurisdiction);
    }
}
