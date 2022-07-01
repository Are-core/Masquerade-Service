package com.masquerade.service.characterSheet.skill;

import com.google.gson.Gson;
import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.entity.characterSheet.skill.SkillEntity;
import com.masquerade.repository.characterSheet.skill.SkillRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Transactional
public class SkillService {
    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Transactional(readOnly = true)
    public List<SkillEntity> getSkills() {
        return skillRepository.findAll();
    }

    @Transactional(readOnly = true)
    public SkillEntity getSkill(Long id) throws BadRequestException {
        if(id == null){
            throw BadRequestException.missingParameter();
        }
        try {
            return skillRepository.findById(id)
                    .orElseThrow(IllegalArgumentException::new);
        }
        catch (Exception e) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
    }

    public ResponseEntity<HttpStatus> createSkill(String rawBody) throws BadRequestException {
        if(rawBody == null) {
            throw BadRequestException.missingParameter();
        }
        Gson gson = new Gson();
        SkillEntity skill = gson.fromJson(rawBody, SkillEntity.class);
        if(skill.emptyObjectCheck()) {
            throw BadRequestException.missingBody();
        }
        skillRepository.save(skill);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<HttpStatus> removeSkill(Long id) throws BadRequestException {
        if(id == null) {
            throw BadRequestException.missingParameter();
        }
        try {
            skillRepository.delete(skillRepository.findById(id)
                    .orElseThrow(IllegalArgumentException::new));
        }
        catch (Exception e) {
            return new ResponseEntity<>(NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> updateSkill (String rawBody) throws EntityRequestException {
        if(rawBody == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Gson gson = new Gson();
        final SkillEntity skill = gson.fromJson(rawBody, SkillEntity.class);
        if(skill.emptyObjectCheck()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        updateSkillData(skill);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void updateSkillData(SkillEntity skill) throws EntityRequestException {
        if(!skillRepository.existsById(skill.getId())) {
            throw EntityRequestException.doesntExists(skill.getId());
        }
        skillRepository.save(skill);
    }
}
