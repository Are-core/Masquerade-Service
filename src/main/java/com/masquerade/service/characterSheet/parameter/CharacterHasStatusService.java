package com.masquerade.service.characterSheet.parameter;

import com.masquerade.exception.BadRequestException;
import com.masquerade.model.dto.parameter.CharacterStatusDTO;
import com.masquerade.model.dto.parameter.DeclaredStatusDTO;
import com.masquerade.model.entity.characterSheet.CharacterEntity;
import com.masquerade.model.entity.characterSheet.parameter.CharacterHasStatusEntity;
import com.masquerade.repository.characterSheet.parameter.CharacterHasStatusRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Transactional
public class CharacterHasStatusService {
    private final CharacterHasStatusRepository statusRepository;

    public CharacterHasStatusService(CharacterHasStatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Transactional(readOnly = true)
    public List<CharacterStatusDTO> getDeclaredStatus() {
        return getDTO(statusRepository.findAll());
    }

    @Transactional(readOnly = true)
    public List<CharacterStatusDTO> getCharacterStatus(Long characterId) throws BadRequestException {
        if(characterId == null){
            throw BadRequestException.missingParameter();
        }
        try {
            return getDTO(statusRepository.findByCharacterId(characterId));
        }
        catch (Exception e) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
    }

    private List<CharacterStatusDTO> getDTO(List<CharacterHasStatusEntity> entities) {
        Map<CharacterEntity, List<CharacterHasStatusEntity>> statusGrouped =
                entities.stream().collect(Collectors.groupingBy(CharacterHasStatusEntity::getCharacter));
        List<CharacterStatusDTO> skillMap = new ArrayList<>();
        for (Map.Entry<CharacterEntity, List<CharacterHasStatusEntity>> entry : statusGrouped.entrySet()) {
            CharacterStatusDTO character = new CharacterStatusDTO(entry.getKey().getId(), entry.getKey().getName());
            List<DeclaredStatusDTO> skillList = new ArrayList<>();
            for(CharacterHasStatusEntity status : entry.getValue()) {
                skillList.add(new DeclaredStatusDTO(status.getStatus(), status.getEntityId()));
            }
            character.setStatus(skillList);
            skillMap.add(character);
        }
        return skillMap;
    }
}
