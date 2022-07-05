package com.masquerade.service.characterSheet;

import com.masquerade.model.dto.characterSheet.CharacterListItemDTO;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.entity.characterSheet.CharacterEntity;
import com.masquerade.repository.characterSheet.CharacterRepository;
import com.masquerade.tools.controller.Responses;
import com.masquerade.tools.entity.EntityArguments;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CharacterService {
    private final CharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Transactional(readOnly = true)
    public ResponseDTO getList() {
        return new ResponseDTO(HttpStatus.OK, characterRepository.findAll().stream().
                map(CharacterListItemDTO::new).collect(Collectors.toList()));
    }

    @Transactional(readOnly = true)
    public ResponseDTO getById(Long id) {
        if (id == null) {
            return Responses.MissingArgument(EntityArguments.idArgument);
        }
        Optional<CharacterEntity> entries = characterRepository.findById(id);
        if (entries.isEmpty()) {
            return Responses.ResponseNoContent;
        }
        return new ResponseDTO(HttpStatus.OK, entries);
    }
}
