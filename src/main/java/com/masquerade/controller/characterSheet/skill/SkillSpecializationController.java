package com.masquerade.controller.characterSheet.skill;

import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.entity.characterSheet.skill.SkillSpecializationEntity;
import com.masquerade.service.characterSheet.skill.SkillSpecializationService;
import com.masquerade.tools.Section;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = Section.CrossOriginUrl)
@RestController
public class SkillSpecializationController {
    private final SkillSpecializationService skillSpecializationService;

    public SkillSpecializationController(SkillSpecializationService skillSpecializationService) {
        this.skillSpecializationService = skillSpecializationService;
    }

    @RequestMapping(value = Section.SkillSpecializationPrefix + "/getSkillSpecializations",method = RequestMethod.GET)
    public ResponseEntity<List<SkillSpecializationEntity>> getSkillSpecializations() {
        return new ResponseEntity<>(skillSpecializationService.getSkillSpecializations(), HttpStatus.OK);
    }

    @RequestMapping(value = Section.SkillSpecializationPrefix + "/getSkillSpecializationById",method = RequestMethod.GET)
    public ResponseEntity<SkillSpecializationEntity> getSkillSpecialization(Long id) throws BadRequestException {
        return new ResponseEntity<>(skillSpecializationService.getSkillSpecialization(id), HttpStatus.OK);
    }

    @RequestMapping(value = Section.SkillSpecializationPrefix + "/removeSkillSpecialization",method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> removeSkillSpecialization(Long id) throws BadRequestException {
        return skillSpecializationService.removeSkillSpecialization(id);
    }

    @PostMapping(value = Section.SkillSpecializationPrefix + "/createSkillSpecialization", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> createSkillSpecialization(@RequestBody String rawArchetype) throws BadRequestException {
        return skillSpecializationService.createSkillSpecialization(rawArchetype);
    }

    @PostMapping(value = Section.SkillSpecializationPrefix + "/updateSkillSpecialization", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> updateSkillSpecialization(@RequestBody String rawArchetype) throws EntityRequestException {
        return skillSpecializationService.updateSkillSpecialization(rawArchetype);
    }
}
