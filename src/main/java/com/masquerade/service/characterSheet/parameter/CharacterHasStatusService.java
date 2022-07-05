package com.masquerade.service.characterSheet.parameter;

import com.masquerade.model.dto.characterSheet.parameter.CharacterStatusDTO;
import com.masquerade.model.dto.characterSheet.parameter.DeclaredStatusDTO;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.entity.characterSheet.CharacterEntity;
import com.masquerade.model.entity.characterSheet.parameter.CharacterHasStatusEntity;
import com.masquerade.service.repository.characterSheet.parameter.CharacterHasStatusRepository;
import com.masquerade.tools.controller.Responses;
import com.masquerade.tools.entity.EntityArguments;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class CharacterHasStatusService {
    private final CharacterHasStatusRepository statusRepository;

    public CharacterHasStatusService(CharacterHasStatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Transactional(readOnly = true)
    public ResponseDTO getDeclaredStatus() {
        return getDTO(statusRepository.findAll());
    }

    @Transactional(readOnly = true)
    public ResponseDTO getCharacterStatus(Long id) {
        if(id == null){
            return Responses.MissingArgument(EntityArguments.idArgument);
        }
        List<CharacterHasStatusEntity> entries = statusRepository.findByCharacterId(id);
        if(entries.isEmpty()) {
            return Responses.ResponseNoContent;
        }
        return getDTO(entries);
    }

    private ResponseDTO getDTO(List<CharacterHasStatusEntity> entities) {
        try {
            Map<CharacterEntity, List<CharacterHasStatusEntity>> statusGrouped =
                    entities.stream().collect(Collectors.groupingBy(CharacterHasStatusEntity::getCharacter));
            List<CharacterStatusDTO> skillMap = new ArrayList<>();
            for (Map.Entry<CharacterEntity, List<CharacterHasStatusEntity>> entry : statusGrouped.entrySet()) {
                CharacterStatusDTO character = new CharacterStatusDTO(entry.getKey().getId(), entry.getKey().getName());
                List<DeclaredStatusDTO> skillList = new ArrayList<>();
                for (CharacterHasStatusEntity status : entry.getValue()) {
                    skillList.add(new DeclaredStatusDTO(status.getStatus(), status.getEntityId()));
                }
                character.setStatus(skillList);
                skillMap.add(character);
            }
            return new ResponseDTO(HttpStatus.OK, skillMap);
        } catch (Exception e) {
            return Responses.ResponseNotFound;
        }
    }
}
