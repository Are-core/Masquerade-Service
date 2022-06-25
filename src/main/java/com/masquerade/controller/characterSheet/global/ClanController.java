package com.masquerade.controller.characterSheet.global;

import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.entity.characterSheet.global.ClanEntity;
import com.masquerade.service.characterSheet.global.ClanService;
import com.masquerade.tools.Section;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = Section.CrossOriginUrl)
@RestController
public class ClanController {
    private final ClanService clanService;

    public ClanController(ClanService clanService) {
        this.clanService = clanService;
    }

    @RequestMapping(value = Section.ClanPrefix + "/getClans",method = RequestMethod.GET)
    public ResponseEntity<List<ClanEntity>> getClans() {
        return new ResponseEntity<>(clanService.getClans(), HttpStatus.OK);
    }

    @RequestMapping(value = Section.ClanPrefix + "/getClanById",method = RequestMethod.GET)
    public ResponseEntity<ClanEntity> getClan(Long id) throws BadRequestException {
        return new ResponseEntity<>(clanService.getClan(id), HttpStatus.OK);
    }

    @RequestMapping(value = Section.ClanPrefix + "/removeClan",method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> removeClan(Long id) throws BadRequestException {
        return clanService.removeClan(id);
    }

    @PostMapping(value = Section.ClanPrefix + "/createClan", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> createClan(@RequestBody String rawClan) throws BadRequestException {
        return clanService.createClan(rawClan);
    }

    @PostMapping(value = Section.ClanPrefix + "/updateClan", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> updateClan(@RequestBody String rawClan) throws EntityRequestException {
        return clanService.updateClan(rawClan);
    }

}
