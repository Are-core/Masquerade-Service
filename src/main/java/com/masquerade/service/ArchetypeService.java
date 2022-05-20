package com.masquerade.service;

import com.masquerade.exception.BadRequestException;
import com.masquerade.model.ArchetypeEntity;
import com.masquerade.repository.ArchetypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<ArchetypeEntity> getArchetype(String name){
        return archetypeRepository.findByDescription(name);
    }

    public void addArchetype(String name){
        ArchetypeEntity archetypeEntity = new ArchetypeEntity(name);
        archetypeRepository.save(archetypeEntity);
    }

    public void removeArchetype(Long id) throws BadRequestException {
        if(id == null) {
            throw BadRequestException.missingParameter();
        }
        archetypeRepository.delete(archetypeRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new));
    }

    public void modifyArchetype(Long id, String description) throws BadRequestException {
        if(id == null) {
            throw BadRequestException.missingParameter();
        }
        archetypeRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new)
                .setDescription(description);
    }
}
