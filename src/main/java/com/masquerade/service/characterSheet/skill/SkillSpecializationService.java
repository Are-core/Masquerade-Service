package com.masquerade.service.characterSheet.skill;

import com.google.gson.Gson;
import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.entity.characterSheet.skill.SkillSpecializationEntity;
import com.masquerade.repository.characterSheet.skill.SkillSpecializationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SkillSpecializationService {
    private final SkillSpecializationRepository skillSpecializationRepository;

    public SkillSpecializationService(SkillSpecializationRepository skillSpecializationRepository) {
        this.skillSpecializationRepository = skillSpecializationRepository;
    }

    @Transactional(readOnly = true)
    public List<SkillSpecializationEntity> getSkillSpecializations() {
        return skillSpecializationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public SkillSpecializationEntity getSkillSpecialization(Long id) throws BadRequestException {
        if(id == null){
            throw BadRequestException.missingParameter();
        }
        return skillSpecializationRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    public ResponseEntity<HttpStatus> createSkillSpecialization(String rawBody) throws BadRequestException {
        if(rawBody == null) {
            throw BadRequestException.missingParameter();
        }
        Gson gson = new Gson();
        SkillSpecializationEntity specialization;
        try {
            specialization = gson.fromJson(rawBody, SkillSpecializationEntity.class);
        } catch (Exception e) {
            throw BadRequestException.missingBody();
        }
        skillSpecializationRepository.save(specialization);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<HttpStatus> removeSkillSpecialization(Long id) throws BadRequestException {
        if(id == null) {
            throw BadRequestException.missingParameter();
        }
        try {
            skillSpecializationRepository.delete(skillSpecializationRepository.findById(id)
                    .orElseThrow(IllegalArgumentException::new));
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> updateSkillSpecialization(final String rawBody) throws EntityRequestException {
        if(rawBody == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Gson gson = new Gson();
        final SkillSpecializationEntity specialization = gson.fromJson(rawBody, SkillSpecializationEntity.class);
        if(specialization.emptyObjectCheck()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        if(!skillSpecializationRepository.existsById(specialization.getId())) {
            throw EntityRequestException.doesntExists(specialization.getId());
        }
        skillSpecializationRepository.save(specialization);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
