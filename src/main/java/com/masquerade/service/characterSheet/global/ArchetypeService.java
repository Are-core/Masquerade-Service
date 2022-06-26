package com.masquerade.service.characterSheet.global;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.entity.characterSheet.global.ArchetypeEntity;
import com.masquerade.repository.characterSheet.global.ArchetypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Service
@Transactional
public class ArchetypeService {
    private final ArchetypeRepository archetypeRepository;

    public ArchetypeService(ArchetypeRepository archetypeRepository) {
        this.archetypeRepository = archetypeRepository;
    }

    @Transactional(readOnly = true)
    public List<ArchetypeEntity> getArchetypes() {
        return archetypeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ArchetypeEntity getArchetype(Long id) throws BadRequestException {
        if(id == null){
            throw BadRequestException.missingParameter();
        }
        return archetypeRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    public ResponseEntity<HttpStatus> removeArchetype(Long id) throws BadRequestException {
        if(id == null) {
            throw BadRequestException.missingParameter();
        }
        archetypeRepository.delete(archetypeRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> createArchetype(String rawArchetype) throws BadRequestException {
        if(rawArchetype == null) {
            throw BadRequestException.missingParameter();
        }
        Gson gson = new Gson();
        ArchetypeEntity archetype = gson.fromJson(rawArchetype, ArchetypeEntity.class);
        if(archetype.emptyObjectCheck()) {
            throw BadRequestException.missingBody();
        }
        archetypeRepository.save(archetype);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<HttpStatus> updateArchetype(final String rawArchetype) throws  EntityRequestException {
        if(rawArchetype == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Gson gson = new Gson();
        final ArchetypeEntity archetype = gson.fromJson(rawArchetype, ArchetypeEntity.class);
        if(archetype.emptyObjectCheck()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        updateArchetypeData(archetype);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> updateArchetypes(final String rawArchetype) throws EntityRequestException {
        if(rawArchetype == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Type listType = new TypeToken<List<ArchetypeEntity>>() {}.getType();
        final List<ArchetypeEntity> archetypes = new Gson().fromJson(rawArchetype, listType);
        for(ArchetypeEntity archetype : archetypes) {
            if(archetype.emptyObjectCheck()) {
                return new ResponseEntity<>(HttpStatus.PARTIAL_CONTENT);
            }
            updateArchetypeData(archetype);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void updateArchetypeData(ArchetypeEntity archetype) throws EntityRequestException {
        if(!archetypeRepository.existsById(archetype.getId())) {
            throw EntityRequestException.doesntExists(archetype.getId());
        }
        archetypeRepository.save(archetype);
    }
}
