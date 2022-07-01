package com.masquerade.controller.characterSheet.skill;

import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.dto.controller.ResponseEntityDTO;
import com.masquerade.service.characterSheet.skill.SkillSpecializationService;
import com.masquerade.tools.controller.Section;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = Section.CrossOriginUrl)
@RestController
public class SkillSpecializationController {
    private final SkillSpecializationService skillSpecializationService;

    public SkillSpecializationController(SkillSpecializationService skillSpecializationService) {
        this.skillSpecializationService = skillSpecializationService;
    }

    @RequestMapping(value = Section.SkillSpecializationPrefix + "/getSkillSpecializations",method = RequestMethod.GET)
    public ResponseEntityDTO<ResponseDTO> getSkillSpecializations() {
        return new ResponseEntityDTO<>(skillSpecializationService.getSkillSpecializations());
    }

    @RequestMapping(value = Section.SkillSpecializationPrefix + "/getSkillSpecializationById",method = RequestMethod.GET)
    public ResponseEntityDTO<ResponseDTO> getSkillSpecialization(Long id) {
        return new ResponseEntityDTO<>(skillSpecializationService.getSkillSpecialization(id));
    }

    @RequestMapping(value = Section.SkillSpecializationPrefix + "/removeSkillSpecialization",method = RequestMethod.DELETE)
    public ResponseEntityDTO<ResponseDTO> removeSkillSpecialization(Long id) {
        return new ResponseEntityDTO<>(skillSpecializationService.removeSkillSpecialization(id));
    }

    @PostMapping(value = Section.SkillSpecializationPrefix + "/createSkillSpecialization", consumes = "application/json", produces = "application/json")
    public ResponseEntityDTO<ResponseDTO> createSkillSpecialization(@RequestBody String rawArchetype) {
        return new ResponseEntityDTO<>(skillSpecializationService.createSkillSpecialization(rawArchetype));
    }

    @PostMapping(value = Section.SkillSpecializationPrefix + "/updateSkillSpecialization", consumes = "application/json", produces = "application/json")
    public ResponseEntityDTO<ResponseDTO> updateSkillSpecialization(@RequestBody String rawArchetype) {
        return new ResponseEntityDTO<>(skillSpecializationService.updateSkillSpecialization(rawArchetype));
    }
}
