package com.masquerade.controller.characterSheet.global;

import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.dto.controller.ResponseEntityDTO;
import com.masquerade.service.characterSheet.global.ClanService;
import com.masquerade.tools.controller.Section;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = Section.CrossOriginUrl)
@RestController
public class ClanController {
    private final ClanService clanService;

    public ClanController(ClanService clanService) {
        this.clanService = clanService;
    }

    @RequestMapping(value = Section.ClanPrefix + "/getClans",method = RequestMethod.GET)
    public ResponseEntityDTO<ResponseDTO> getClans() {
        return new ResponseEntityDTO<>(clanService.getClans());
    }

    @RequestMapping(value = Section.ClanPrefix + "/getClanById",method = RequestMethod.GET)
    public ResponseEntityDTO<ResponseDTO> getClan(Long id) {
        return new ResponseEntityDTO<>(clanService.getClan(id));
    }

    @RequestMapping(value = Section.ClanPrefix + "/removeClan",method = RequestMethod.DELETE)
    public ResponseEntityDTO<ResponseDTO> removeClan(Long id) {
        return new ResponseEntityDTO<>(clanService.removeClan(id));
    }

    @PostMapping(value = Section.ClanPrefix + "/createClan", consumes = "application/json", produces = "application/json")
    public ResponseEntityDTO<ResponseDTO> createClan(@RequestBody String rawClan) {
        return new ResponseEntityDTO<>(clanService.createClan(rawClan));
    }

    @PostMapping(value = Section.ClanPrefix + "/updateClan", consumes = "application/json", produces = "application/json")
    public ResponseEntityDTO<ResponseDTO> updateClan(@RequestBody String rawClan) {
        return new ResponseEntityDTO<>(clanService.updateClan(rawClan));
    }

}
