package com.masquerade.controller.characterSheet.skill;

import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.entity.characterSheet.skill.SkillEntity;
import com.masquerade.service.characterSheet.skill.SkillService;
import com.masquerade.tools.Section;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = Section.CrossOriginUrl)
@RestController
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @RequestMapping(value = Section.SkillPrefix + "/getSkills",method = RequestMethod.GET)
    public ResponseEntity<List<SkillEntity>> getSkills() {
        return new ResponseEntity<>(skillService.getSkills(), HttpStatus.OK);
    }

    @RequestMapping(value = Section.SkillPrefix + "/getSkillById",method = RequestMethod.GET)
    public ResponseEntity<SkillEntity> getSkill(Long id) throws BadRequestException {
        return new ResponseEntity<>(skillService.getSkill(id), HttpStatus.OK);
    }

    @RequestMapping(value = Section.SkillPrefix + "/removeSkill",method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> removeSkill(Long id) throws BadRequestException {
        return skillService.removeSkill(id);
    }

    @PostMapping(value = Section.SkillPrefix + "/createSkill", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> createSkill(@RequestBody String rawArchetype) throws BadRequestException {
        return skillService.createSkill(rawArchetype);
    }

    @PostMapping(value = Section.SkillPrefix + "/updateSkill", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> updateSkill(@RequestBody String rawArchetype) throws EntityRequestException {
        return skillService.updateSkill(rawArchetype);
    }
}
