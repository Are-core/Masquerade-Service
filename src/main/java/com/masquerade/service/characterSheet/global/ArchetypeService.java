package com.masquerade.service.characterSheet.global;

import com.google.gson.Gson;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.entity.characterSheet.global.ArchetypeEntity;
import com.masquerade.service.repository.characterSheet.global.ArchetypeRepository;
import com.masquerade.tools.controller.Responses;
import com.masquerade.tools.entity.EntityArguments;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ArchetypeService {
    private final ArchetypeRepository archetypeRepository;

    public ArchetypeService(ArchetypeRepository archetypeRepository) {
        this.archetypeRepository = archetypeRepository;
    }

    @Transactional(readOnly = true)
    public ResponseDTO getArchetypes() {
        return new ResponseDTO(HttpStatus.OK, archetypeRepository.findAll());
    }

    @Transactional(readOnly = true)
    public ResponseDTO getArchetype(Long id) {
        if(id == null){
            return Responses.MissingArgument(EntityArguments.idArgument);
        }
        Optional<ArchetypeEntity> entries = archetypeRepository.findById(id);
        if(entries.isEmpty()) {
            return Responses.ResponseNoContent;
        }
        return new ResponseDTO(HttpStatus.OK, entries.get());
    }

    public ResponseDTO removeArchetype(Long id) {
        if(id == null) {
            return Responses.MissingArgument(EntityArguments.idArgument);
        }
        Optional<ArchetypeEntity> entity = archetypeRepository.findById(id);
        if(entity.isEmpty()) {
            return Responses.ResponseNoContent;
        }
        archetypeRepository.delete(entity.get());
        return new ResponseDTO(HttpStatus.OK, entity.get());
    }

    public ResponseDTO createArchetype(String rawBody) {
        if(rawBody == null) {
            return Responses.MissingArgument(EntityArguments.JsonArgument);
        }
        ArchetypeEntity archetype;
        try {
            Gson gson = new Gson();
            archetype = gson.fromJson(rawBody, ArchetypeEntity.class);
            archetype.setId(null);
            archetype = archetypeRepository.save(archetype);
        } catch (Exception e) {
            return Responses.ResponseBadRequest;
        }
        return new ResponseDTO(HttpStatus.CREATED, archetype);
    }

    public ResponseDTO updateArchetype(final String rawBody) {
        if(rawBody == null) {
            return Responses.MissingArgument(EntityArguments.JsonArgument);
        }
        try {
            Gson gson = new Gson();
            ArchetypeEntity archetype = gson.fromJson(rawBody, ArchetypeEntity.class);
            if(archetype == null || archetype.emptyObjectCheck()) {
                return Responses.ResponseBadRequest;
            }
            if(!archetypeRepository.existsById(archetype.getId())) {
                return Responses.ResponseNoContent;
            }
            archetype = archetypeRepository.save(archetype);
            return new ResponseDTO(HttpStatus.OK, archetype);
        } catch (Exception e) {
            return Responses.ResponseBadRequest;
        }
    }
}
