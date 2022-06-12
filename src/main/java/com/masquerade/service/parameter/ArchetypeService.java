package com.masquerade.service.parameter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.parameter.ArchetypeEntity;
import com.masquerade.repository.parameter.ArchetypeRepository;
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

    @Transactional(readOnly = true)
    public List<ArchetypeEntity> getArchetype(String searchText){
        return archetypeRepository.findByDescription(searchText);
    }

    public void removeArchetype(Long id) throws BadRequestException {
        if(id == null) {
            throw BadRequestException.missingParameter();
        }
        archetypeRepository.delete(archetypeRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new));
    }

    public void createArchetype(String rawArchetype) throws BadRequestException {
        if(rawArchetype == null) {
            throw BadRequestException.missingParameter();
        }
        Gson gson = new Gson();
        ArchetypeEntity archetype = gson.fromJson(rawArchetype, ArchetypeEntity.class);
        if(archetype.isNull()) {
            throw BadRequestException.missingBody();
        }
        archetypeRepository.save(archetype);
    }

    public void updateArchetype(final String rawArchetype) throws BadRequestException, EntityRequestException {
        if(rawArchetype == null) {
            throw BadRequestException.missingParameter();
        }
        Gson gson = new Gson();
        final ArchetypeEntity archetype = gson.fromJson(rawArchetype, ArchetypeEntity.class);
        if(archetype.isNull()) {
            throw BadRequestException.missingBody();
        }
        updateArchetypeData(archetype);
    }

    public void updateArchetypes(final String rawArchetype) throws BadRequestException, EntityRequestException {
        if(rawArchetype == null) {
            throw BadRequestException.missingParameter();
        }
        Type listType = new TypeToken<List<ArchetypeEntity>>() {}.getType();
        final List<ArchetypeEntity> archetypes = new Gson().fromJson(rawArchetype, listType);
        for(ArchetypeEntity archetype : archetypes) {
            if(archetype.isNull()) {
                throw BadRequestException.missingBody();
            }
            updateArchetypeData(archetype);
        }
    }

    private void updateArchetypeData(ArchetypeEntity archetype) throws EntityRequestException {
        if(!archetypeRepository.existsById(archetype.getId())) {
            throw EntityRequestException.doesntExists(archetype.getId());
        }
        archetypeRepository.save(archetype);
    }
}
