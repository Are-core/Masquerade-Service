package com.masquerade.service;

import com.masquerade.exception.BadRequestException;
import com.masquerade.model.CharacterEntity;
import com.masquerade.model.SimpleCharacterEntity;
import com.masquerade.repository.CharacterRepository;
import com.masquerade.repository.SimpleCharacterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CharacterService {
    private final CharacterRepository characterRepository;
    private final SimpleCharacterRepository simpleCharacterRepository;

    public CharacterService(CharacterRepository characterRepository, SimpleCharacterRepository simpleCharacterRepository) {
        this.characterRepository = characterRepository;
        this.simpleCharacterRepository = simpleCharacterRepository;
    }

    @Transactional(readOnly = true)
    public List<SimpleCharacterEntity> getList() {
        return simpleCharacterRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CharacterEntity getById(Long id) throws BadRequestException {
        if(id == null){
            throw BadRequestException.missingParameter();
        }
        return characterRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }
}
