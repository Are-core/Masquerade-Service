package com.masquerade.service.characterSheet.skill;

import com.google.gson.Gson;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.entity.characterSheet.skill.SkillEntity;
import com.masquerade.service.repository.characterSheet.skill.SkillRepository;
import com.masquerade.tools.controller.Responses;
import com.masquerade.tools.entity.EntityArguments;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class SkillService {
    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Transactional(readOnly = true)
    public ResponseDTO getSkills() {
        return new ResponseDTO(HttpStatus.OK,skillRepository.findAll());
    }

    @Transactional(readOnly = true)
    public ResponseDTO getSkill(Long id) {
        if(id == null){
            return Responses.MissingArgument(EntityArguments.idArgument);
        }
        Optional<SkillEntity> entries = skillRepository.findById(id);
        if(entries.isEmpty()) {
            return Responses.ResponseNoContent;
        }
        return new ResponseDTO(HttpStatus.OK, entries.get());
    }

    public ResponseDTO createSkill(String rawBody) {
        if(rawBody == null) {
            return Responses.MissingArgument(EntityArguments.JsonArgument);
        }
        SkillEntity skill;
        try {
            Gson gson = new Gson();
            skill = gson.fromJson(rawBody, SkillEntity.class);
            //TODO use isUpdatable() and manage exception
            skill.setId(null);
            skill = skillRepository.save(skill);
        } catch (Exception e) {
            return Responses.ResponseBadRequest;
        }
        return new ResponseDTO(HttpStatus.CREATED, skill);
    }

    public ResponseDTO removeSkill(Long id) {
        if(id == null) {
            return Responses.MissingArgument(EntityArguments.idArgument);
        }
        Optional<SkillEntity> entity = skillRepository.findById(id);
        if(entity.isEmpty()) {
            return Responses.ResponseNoContent;
        }
        skillRepository.delete(entity.get());
        return new ResponseDTO(HttpStatus.OK, entity.get());
    }

    public ResponseDTO updateSkill (String rawBody) {
        if(rawBody == null) {
            return Responses.MissingArgument(EntityArguments.JsonArgument);
        }
        try {
            Gson gson = new Gson();
            SkillEntity skill = gson.fromJson(rawBody, SkillEntity.class);
            if(skill == null || skill.emptyObjectCheck()) {
                return Responses.ResponseBadRequest;
            }
            if(!skillRepository.existsById(skill.getId())) {
                return Responses.ResponseNoContent;
            }
            skill = skillRepository.save(skill);
            return new ResponseDTO(HttpStatus.OK, skill);
        } catch (Exception e) {
            return Responses.ResponseBadRequest;
        }
    }
}
