package com.masquerade.controller.characterSheet.parameter;


import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.dto.controller.ResponseEntityDTO;
import com.masquerade.service.characterSheet.parameter.JurisdictionService;
import com.masquerade.tools.controller.Section;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = Section.CrossOriginUrl)
@RestController
public class JurisdictionController {
    private final JurisdictionService jurisdictionService;

    public JurisdictionController(JurisdictionService jurisdictionService) {
        this.jurisdictionService = jurisdictionService;
    }

    @RequestMapping(value = Section.JurisdictionPrefix + "/getJurisdictions",method = RequestMethod.GET)
    public ResponseEntityDTO<ResponseDTO> getJurisdictions() {
        return new ResponseEntityDTO<>(jurisdictionService.getJurisdictions());
    }

    @RequestMapping(value = Section.JurisdictionPrefix + "/getJurisdictionById",method = RequestMethod.GET)
    public ResponseEntityDTO<ResponseDTO> getJurisdiction(Long id) {
        return new ResponseEntityDTO<>(jurisdictionService.getJurisdiction(id));
    }

    @RequestMapping(value = Section.JurisdictionPrefix + "/removeJurisdiction",method = RequestMethod.DELETE)
    public ResponseEntityDTO<ResponseDTO> removeJurisdiction(Long id) {
        return new ResponseEntityDTO<>(jurisdictionService.removeJurisdiction(id));
    }

    @PostMapping(value = Section.JurisdictionPrefix + "/createJurisdiction", consumes = "application/json", produces = "application/json")
    public ResponseEntityDTO<ResponseDTO> createJurisdiction(@RequestBody String rawJurisdiction) {
        return new ResponseEntityDTO<>(jurisdictionService.createJurisdiction(rawJurisdiction));
    }

    @PostMapping(value = Section.JurisdictionPrefix + "/updateJurisdiction", consumes = "application/json", produces = "application/json")
    public ResponseEntityDTO<ResponseDTO> updateJurisdiction(@RequestBody String rawJurisdiction) {
        return new ResponseEntityDTO<>(jurisdictionService.updateJurisdiction(rawJurisdiction));
    }
}
