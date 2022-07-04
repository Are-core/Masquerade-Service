package com.masquerade.controller.characterSheet.global;

import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.dto.controller.ResponseEntityDTO;
import com.masquerade.service.characterSheet.global.SectService;
import com.masquerade.tools.controller.Section;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = Section.CrossOriginUrl)
@RestController
public class SectController {

    private final SectService sectService;

    public SectController(SectService sectService) { this.sectService = sectService; }

    @RequestMapping(value = Section.SectPrefix + "/getSects",method = RequestMethod.GET)
    public ResponseEntityDTO<ResponseDTO> getArchetypes() {
        return new ResponseEntityDTO<>(sectService.getSects());
    }

    @RequestMapping(value = Section.SectPrefix + "/getSectById",method = RequestMethod.GET)
    public ResponseEntityDTO<ResponseDTO> getArchetype(Long id) {
        return new ResponseEntityDTO<>(sectService.getSect(id));
    }

    @RequestMapping(value = Section.SectPrefix + "/removeSect",method = RequestMethod.DELETE)
    public ResponseEntityDTO<ResponseDTO> removeArchetype(Long id) {
        return new ResponseEntityDTO<>(sectService.removeSect(id));
    }

    @PostMapping(value = Section.SectPrefix + "/createSect", consumes = "application/json", produces = "application/json")
    public ResponseEntityDTO<ResponseDTO> createSect(@RequestBody String rawSect) {
        return new ResponseEntityDTO<>(sectService.createSect(rawSect));
    }

    @PostMapping(value = Section.SectPrefix + "/updateSect", consumes = "application/json", produces = "application/json")
    public ResponseEntityDTO<ResponseDTO> updateSect(@RequestBody String rawSect) {
        return new ResponseEntityDTO<>(sectService.updateSect(rawSect));
    }
}
