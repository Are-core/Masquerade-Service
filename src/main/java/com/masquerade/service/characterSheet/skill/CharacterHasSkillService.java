package com.masquerade.service.characterSheet.skill;

import com.google.gson.Gson;
import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.dto.skill.CharacterSkillsDTO;
import com.masquerade.model.dto.skill.DeclaredSkillDTO;
import com.masquerade.model.entity.characterSheet.CharacterEntity;
import com.masquerade.model.entity.characterSheet.skill.CharacterHasSkillEntity;
import com.masquerade.repository.characterSheet.skill.CharacterHasSkillRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Transactional
public class CharacterHasSkillService {
    private final CharacterHasSkillRepository characterHasSkillRepository;

    public CharacterHasSkillService(CharacterHasSkillRepository characterHasSkillRepository) {
        this.characterHasSkillRepository = characterHasSkillRepository;
    }

    @Transactional(readOnly = true)
    public List<CharacterSkillsDTO> getDeclaredSkills() {
        return getDTO(characterHasSkillRepository.findAll());
    }

    @Transactional(readOnly = true)
    public List<CharacterSkillsDTO> getCharacterSkills(Long characterId) throws BadRequestException {
        if(characterId == null){
            throw BadRequestException.missingParameter();
        }
        try {
            return getDTO(characterHasSkillRepository.findByCharacterId(characterId));
        }
        catch (Exception e) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
    }

    public ResponseEntity<HttpStatus> setSkillForCharacter(String rawBody) throws BadRequestException {
        if(rawBody == null) {
            throw BadRequestException.missingParameter();
        }
        Gson gson = new Gson();
        CharacterHasSkillEntity skill = gson.fromJson(rawBody, CharacterHasSkillEntity.class);
        if(skill.generateId()) {
            throw BadRequestException.missingBody();
        }
        characterHasSkillRepository.save(skill);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<HttpStatus> removeSkillForCharacter(Long characterId, Long skillId) throws BadRequestException {
        if(characterId == null || skillId == null) {
            throw BadRequestException.missingParameter();
        }
        try {
            characterHasSkillRepository.delete(
                    characterHasSkillRepository.findByCharacterIdAndSkillId(characterId, skillId)
            );
        }
        catch (Exception e) {
            return new ResponseEntity<>(NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> updateSkillForCharacter (String rawBody) throws EntityRequestException {
        if(rawBody == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Gson gson = new Gson();
        final CharacterHasSkillEntity skill = gson.fromJson(rawBody, CharacterHasSkillEntity.class);
        if(skill.generateId()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        updateSkillData(skill);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void updateSkillData(CharacterHasSkillEntity skill) throws EntityRequestException {
        if(!characterHasSkillRepository.existsByCharacterIdAndSkillId(skill.getCharacter().getId(), skill.getSkill().getId())) {
            throw EntityRequestException.doesntExists(skill.getCharacter().getId(), skill.getSkill().getId());
        }
        characterHasSkillRepository.save(skill);
    }

    private List<CharacterSkillsDTO> getDTO(List<CharacterHasSkillEntity> entities) {
        Map<CharacterEntity, List<CharacterHasSkillEntity>> skillsGrouped =
                entities.stream().collect(Collectors.groupingBy(CharacterHasSkillEntity::getCharacter));
        List<CharacterSkillsDTO> skillMap = new ArrayList<>();
        for (Map.Entry<CharacterEntity, List<CharacterHasSkillEntity>> entry : skillsGrouped.entrySet()) {
            CharacterSkillsDTO character = new CharacterSkillsDTO(entry.getKey().getId(), entry.getKey().getName());
            List<DeclaredSkillDTO> skillList = new ArrayList<>();
            for(CharacterHasSkillEntity skill : entry.getValue()) {
                skillList.add(new DeclaredSkillDTO(skill.getSkill(), skill.getLevel(), skill.getSkillSpecialization()));
            }
            character.setSkills(skillList);
            skillMap.add(character);
        }
        return skillMap;
    }

    @Transactional(readOnly = true)
    public List<DeclaredSkillDTO> getCharacterSkillList(Long characterId) throws BadRequestException {
        if(characterId == null){
            throw BadRequestException.missingParameter();
        }
        try {
            return getSkillList(characterHasSkillRepository.findByCharacterId(characterId), characterId);
        }
        catch (Exception e) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
    }

    private List<DeclaredSkillDTO> getSkillList(List<CharacterHasSkillEntity> entities, Long characterId) {
        Map<CharacterEntity, List<CharacterHasSkillEntity>> skillsGrouped =
                entities.stream().collect(Collectors.groupingBy(CharacterHasSkillEntity::getCharacter));
        for (Map.Entry<CharacterEntity, List<CharacterHasSkillEntity>> entry : skillsGrouped.entrySet()) {
            if(Objects.equals(entry.getKey().getId(), characterId)) {
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
