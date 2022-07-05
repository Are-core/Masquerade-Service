package com.masquerade.service.characterSheet.skill;

import com.google.gson.Gson;
import com.masquerade.model.dto.characterSheet.skill.CharacterSkillsDTO;
import com.masquerade.model.dto.characterSheet.skill.DeclaredSkillDTO;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.entity.characterSheet.CharacterEntity;
import com.masquerade.model.entity.characterSheet.skill.CharacterHasSkillEntity;
import com.masquerade.service.repository.characterSheet.skill.CharacterHasSkillRepository;
import com.masquerade.tools.controller.Responses;
import com.masquerade.tools.entity.EntityArguments;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CharacterHasSkillService {
    private final CharacterHasSkillRepository characterHasSkillRepository;

    public CharacterHasSkillService(CharacterHasSkillRepository characterHasSkillRepository) {
        this.characterHasSkillRepository = characterHasSkillRepository;
    }

    @Transactional(readOnly = true)
    public ResponseDTO getDeclaredSkills() {
        return new ResponseDTO(HttpStatus.OK, getDTO(characterHasSkillRepository.findAll()));
    }

    @Transactional(readOnly = true)
    public ResponseDTO getCharacterSkills(Long id) {
        if (id == null) {
            return Responses.MissingArgument(EntityArguments.idArgument);
        }
        List<CharacterHasSkillEntity> entries = characterHasSkillRepository.findByCharacterId(id);
        if (entries.isEmpty()) {
            return Responses.ResponseNoContent;
        }
        return new ResponseDTO(HttpStatus.OK, getDTO(entries));
    }

    public ResponseDTO setSkillForCharacter(String rawBody) {
        if (rawBody == null) {
            return Responses.MissingArgument(EntityArguments.JsonArgument);
        }
        Gson gson = new Gson();
        CharacterHasSkillEntity characterSkill;
        try {
            characterSkill = gson.fromJson(rawBody, CharacterHasSkillEntity.class);
            if (characterSkill.generateId()) {
                return Responses.ResponseBadRequest;
            }
            characterSkill = characterHasSkillRepository.save(characterSkill);
            return new ResponseDTO(HttpStatus.CREATED, characterSkill);
        } catch (Exception e) {
            return Responses.ResponseBadRequest;
        }
    }

    public ResponseDTO removeSkillForCharacter(Long characterId, Long skillId) {
        List<String> missingParameters = new ArrayList<>();
        if (characterId == null) {
            missingParameters.add(EntityArguments.characterIdArgument);
        }
        if (skillId == null) {
            missingParameters.add(EntityArguments.skillIdArgument);
        }
        if (!missingParameters.isEmpty()) {
            return Responses.MissingArguments(missingParameters);
        }
        CharacterHasSkillEntity entity = characterHasSkillRepository.findByCharacterIdAndSkillId(characterId, skillId);
        if (entity == null) {
            return Responses.ResponseNoContent;
        }
        characterHasSkillRepository.delete(entity);
        return new ResponseDTO(HttpStatus.OK, entity);
    }

    public ResponseDTO updateSkillForCharacter(String rawBody) {
        if (rawBody == null) {
            return Responses.MissingArgument(EntityArguments.JsonArgument);
        }
        try {
            Gson gson = new Gson();
            CharacterHasSkillEntity skill = gson.fromJson(rawBody, CharacterHasSkillEntity.class);
            if (skill == null || skill.emptyObjectCheck() || skill.generateId()) {
                return Responses.ResponseBadRequest;
            }
            if (!characterHasSkillRepository.existsByCharacterIdAndSkillId(skill.getId().characterId, skill.getId().skillId)) {
                return Responses.ResponseNoContent;
            }
            return new ResponseDTO(HttpStatus.OK, characterHasSkillRepository.save(skill));
        } catch (Exception e) {
            return Responses.ResponseBadRequest;
        }
    }

    private List<CharacterSkillsDTO> getDTO(List<CharacterHasSkillEntity> entities) {
        Map<CharacterEntity, List<CharacterHasSkillEntity>> skillsGrouped =
                entities.stream().collect(Collectors.groupingBy(CharacterHasSkillEntity::getCharacter));
        List<CharacterSkillsDTO> skillMap = new ArrayList<>();
        for (Map.Entry<CharacterEntity, List<CharacterHasSkillEntity>> entry : skillsGrouped.entrySet()) {
            CharacterSkillsDTO character = new CharacterSkillsDTO(entry.getKey().getId(), entry.getKey().getName());
            List<DeclaredSkillDTO> skillList = new ArrayList<>();
            for (CharacterHasSkillEntity skill : entry.getValue()) {
                skillList.add(new DeclaredSkillDTO(skill.getSkill(), skill.getLevel(), skill.getSkillSpecialization()));
            }
            character.setSkills(skillList);
            skillMap.add(character);
        }
        return skillMap;
    }

    @Transactional(readOnly = true)
    public List<DeclaredSkillDTO> getCharacterSkillList(Long characterId) {
        if (characterId == null) {
            return null;
        }
        List<CharacterHasSkillEntity> entities = characterHasSkillRepository.findByCharacterId(characterId);
        Map<CharacterEntity, List<CharacterHasSkillEntity>> skillsGrouped =
                entities.stream().collect(Collectors.groupingBy(CharacterHasSkillEntity::getCharacter));
        for (Map.Entry<CharacterEntity, List<CharacterHasSkillEntity>> entry : skillsGrouped.entrySet()) {
            if (Objects.equals(entry.getKey().getId(), characterId)) {
                List<DeclaredSkillDTO> skillList = new ArrayList<>();
                for (CharacterHasSkillEntity skill : entry.getValue()) {
                    skillList.add(new DeclaredSkillDTO(skill.getSkill(), skill.getLevel(), skill.getSkillSpecialization()));
                }
                return skillList;
            }
        }
        return null;
    }
}
