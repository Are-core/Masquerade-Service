package com.masquerade.controller.characterSheet.skill;

import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.dto.controller.ResponseEntityDTO;
import com.masquerade.service.characterSheet.skill.SkillService;
import com.masquerade.tools.controller.Section;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = Section.CrossOriginUrl)
@RestController
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @RequestMapping(value = Section.SkillPrefix + "/getSkills",method = RequestMethod.GET)
    public ResponseEntityDTO<ResponseDTO> getSkills() {
        return new ResponseEntityDTO<>(skillService.getSkills());
    }

    @RequestMapping(value = Section.SkillPrefix + "/getSkillById",method = RequestMethod.GET)
    public ResponseEntityDTO<ResponseDTO> getSkill(Long id) {
        return new ResponseEntityDTO<>(skillService.getSkill(id));
    }

    @RequestMapping(value = Section.SkillPrefix + "/removeSkill",method = RequestMethod.DELETE)
    public ResponseEntityDTO<ResponseDTO> removeSkill(Long id) {
        return new ResponseEntityDTO<>(skillService.removeSkill(id));
    }

    @PostMapping(value = Section.SkillPrefix + "/createSkill", consumes = "application/json", produces = "application/json")
    public ResponseEntityDTO<ResponseDTO> createSkill(@RequestBody String rawArchetype) {
        return new ResponseEntityDTO<>(skillService.createSkill(rawArchetype));
    }

    @PostMapping(value = Section.SkillPrefix + "/updateSkill", consumes = "application/json", produces = "application/json")
    public ResponseEntityDTO<ResponseDTO> updateSkill(@RequestBody String rawArchetype) {
        return new ResponseEntityDTO<>(skillService.updateSkill(rawArchetype));
    }
}
