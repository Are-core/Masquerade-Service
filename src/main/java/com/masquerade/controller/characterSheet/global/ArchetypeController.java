package com.masquerade.controller.characterSheet.global;

import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.dto.controller.ResponseEntityDTO;
import com.masquerade.service.characterSheet.global.ArchetypeService;
import com.masquerade.tools.controller.Section;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = Section.CrossOriginUrl)
@RestController
public class ArchetypeController {
    private final ArchetypeService archetypeService;

    public ArchetypeController(ArchetypeService archetypeService) {
        this.archetypeService = archetypeService;
    }

    @RequestMapping(value = Section.ArchetypePrefix + "/getArchetypes",method = RequestMethod.GET)
    public ResponseEntityDTO<ResponseDTO> getArchetypes() {
        return new ResponseEntityDTO<>(archetypeService.getArchetypes());
    }

    @RequestMapping(value = Section.ArchetypePrefix + "/getArchetypeById",method = RequestMethod.GET)
    public ResponseEntityDTO<ResponseDTO> getArchetype(Long id) {
        return new ResponseEntityDTO<>(archetypeService.getArchetype(id));
    }

    @RequestMapping(value = Section.ArchetypePrefix + "/removeArchetype",method = RequestMethod.DELETE)
    public ResponseEntityDTO<ResponseDTO> removeArchetype(Long id) {
        return new ResponseEntityDTO<>(archetypeService.removeArchetype(id));
    }

    @PostMapping(value = Section.ArchetypePrefix + "/createArchetype", consumes = "application/json", produces = "application/json")
    public ResponseEntityDTO<ResponseDTO> createArchetype(@RequestBody String rawArchetype) {
        return new ResponseEntityDTO<>(archetypeService.createArchetype(rawArchetype));
    }

    @PostMapping(value = Section.ArchetypePrefix + "/updateArchetype", consumes = "application/json", produces = "application/json")
    public ResponseEntityDTO<ResponseDTO> updateArchetype(@RequestBody String rawArchetype) {
        return new ResponseEntityDTO<>(archetypeService.updateArchetype(rawArchetype));
    }
}
