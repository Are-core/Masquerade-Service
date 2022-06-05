package com.masquerade.service.parameter;

import com.masquerade.exception.BadRequestException;
import com.masquerade.model.parameter.ArchetypeEntity;
import com.masquerade.repository.parameter.ArchetypeRepository;
import com.masquerade.tools.Util;
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
    public List<ArchetypeEntity> getArchetype(String searchText){
        return archetypeRepository.findByDescription(searchText);
    }

    public void addArchetype(String description, String note, Util.SettingLanguage language){
        ArchetypeEntity archetypeEntity;
        if (language.equals(Util.SettingLanguage.FR)) {
            archetypeEntity = new ArchetypeEntity(null, description, null, note);
        } else {
            archetypeEntity = new ArchetypeEntity(description, null, note, null);
        }
        archetypeRepository.save(archetypeEntity);
    }

    public void removeArchetype(Long id) throws BadRequestException {
        if(id == null) {
            throw BadRequestException.missingParameter();
        }
        archetypeRepository.delete(archetypeRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new));
    }

    public void modifyArchetype(Long id, String description, String note, Util.SettingLanguage language) throws BadRequestException {
        if(id == null) {
            throw BadRequestException.missingParameter();
        }
        if(description != null) {
            modifyArchetypeDescription(id, description, language);
        }
        if(note != null) {
            modifyArchetypeNote(id, note, language);
        }
    }

    private void modifyArchetypeDescription(Long id, String value, Util.SettingLanguage language) {
        if (language == Util.SettingLanguage.FR) {
            archetypeRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new)
                .setDescriptionFR(value);
        } else {
            archetypeRepository.findById(id)
                    .orElseThrow(IllegalArgumentException::new)
                    .setDescriptionEN(value);
        }
    }

    private void modifyArchetypeNote(Long id, String value, Util.SettingLanguage language) {
        if (language == Util.SettingLanguage.FR) {
            archetypeRepository.findById(id)
                    .orElseThrow(IllegalArgumentException::new)
                    .setNoteFR(value);
        } else {
            archetypeRepository.findById(id)
                    .orElseThrow(IllegalArgumentException::new)
                    .setNoteEN(value);
        }
    }
}
