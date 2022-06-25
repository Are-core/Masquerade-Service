package com.masquerade.service;

import com.masquerade.exception.BadRequestException;
import com.masquerade.model.dto.CharacterListItemDTO;
import com.masquerade.model.entity.CharacterEntity;
import com.masquerade.repository.CharacterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CharacterService {
    private final CharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Transactional(readOnly = true)
    public List<CharacterListItemDTO> getList() {
        List<CharacterEntity> list = characterRepository.findAll();
        List<CharacterListItemDTO> simpleList = new ArrayList<>();
        for (CharacterEntity character: list)
        {
            simpleList.add(new CharacterListItemDTO(character));
        }
        return simpleList;
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
