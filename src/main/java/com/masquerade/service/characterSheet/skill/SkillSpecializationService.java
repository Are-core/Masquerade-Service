package com.masquerade.service.characterSheet.skill;

import com.google.gson.Gson;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.entity.characterSheet.skill.SkillSpecializationEntity;
import com.masquerade.repository.characterSheet.skill.SkillSpecializationRepository;
import com.masquerade.tools.controller.Responses;
import com.masquerade.tools.entity.EntityArguments;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class SkillSpecializationService {
    private final SkillSpecializationRepository skillSpecializationRepository;

    public SkillSpecializationService(SkillSpecializationRepository skillSpecializationRepository) {
        this.skillSpecializationRepository = skillSpecializationRepository;
    }

    @Transactional(readOnly = true)
    public ResponseDTO getSkillSpecializations() {
        return new ResponseDTO(HttpStatus.OK, skillSpecializationRepository.findAll());
    }

    @Transactional(readOnly = true)
    public ResponseDTO getSkillSpecialization(Long id) {
        if(id == null){
            return Responses.MissingArgument(EntityArguments.idArgument);
        }
        Optional<SkillSpecializationEntity> entries = skillSpecializationRepository.findById(id);
        if(entries.isEmpty()) {
            return Responses.ResponseNoContent;
        }
        return new ResponseDTO(HttpStatus.OK, entries.get());
    }

    public ResponseDTO createSkillSpecialization(String rawBody) {
        Gson gson = new Gson();
        SkillSpecializationEntity specialization;
        try {
            specialization = gson.fromJson(rawBody, SkillSpecializationEntity.class);
            //Prevent Updating if id is not null
            specialization.setId(null);
            specialization = skillSpecializationRepository.save(specialization);
        } catch (Exception e) {
            return Responses.ResponseBadRequest;
        }
        return new ResponseDTO(HttpStatus.CREATED, specialization);
    }

    public ResponseDTO removeSkillSpecialization(Long id) {
        if(id == null) {
            return Responses.MissingArgument(EntityArguments.idArgument);
        }
        Optional<SkillSpecializationEntity> entity = skillSpecializationRepository.findById(id);
        if(entity.isEmpty()) {
            return Responses.ResponseNoContent;
        }
        skillSpecializationRepository.delete(entity.get());
        return new ResponseDTO(HttpStatus.OK, entity.get());
    }

    public ResponseDTO updateSkillSpecialization(final String rawBody) {
        try {
            Gson gson = new Gson();
            SkillSpecializationEntity specialization = gson.fromJson(rawBody, SkillSpecializationEntity.class);
            if(specialization == null || specialization.emptyObjectCheck() || !skillSpecializationRepository.existsById(specialization.getId())) {
                return Responses.ResponseBadRequest;
            }
            specialization = skillSpecializationRepository.save(specialization);
            return new ResponseDTO(HttpStatus.OK, specialization);
        } catch (Exception e) {
            return Responses.ResponseBadRequest;
        }
    }
}
