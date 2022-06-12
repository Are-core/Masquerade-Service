package com.masquerade.controller.parameter;

import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.parameter.ClanEntity;
import com.masquerade.service.parameter.ClanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ClanController {
    private final String ServicePrefix = "/parameter";
    private final ClanService clanService;

    public ClanController(ClanService clanService) {
        this.clanService = clanService;
    }

    @RequestMapping(value = ServicePrefix + "/getClans",method = RequestMethod.GET)
    public ResponseEntity<List<ClanEntity>> getClans() {
        return new ResponseEntity<>(clanService.getClans(), HttpStatus.OK);
    }

    @RequestMapping(value = ServicePrefix + "/getClanById",method = RequestMethod.GET)
    public ResponseEntity<ClanEntity> getClan(Long id) throws BadRequestException {
        return new ResponseEntity<>(clanService.getClan(id), HttpStatus.OK);
    }

    @RequestMapping(value = ServicePrefix + "/removeClan",method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> removeClan(Long id) throws BadRequestException {
        return clanService.removeClan(id);
    }

    @PostMapping(value = ServicePrefix + "/createClan", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> createClan(@RequestBody String rawClan) throws BadRequestException {
        return clanService.createClan(rawClan);
    }

    @PostMapping(value = ServicePrefix + "/updateClan", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> updateClan(@RequestBody String rawClan) throws EntityRequestException {
        return clanService.updateClan(rawClan);
    }

}
