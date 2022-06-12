package com.masquerade.service.parameter;

import com.google.gson.Gson;
import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.parameter.ArchetypeEntity;
import com.masquerade.model.parameter.SectEntity;
import com.masquerade.repository.parameter.SectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SectService {
    private final SectRepository sectRepository;

    public SectService(SectRepository sectRepository) { this.sectRepository = sectRepository; }

    @Transactional(readOnly = true)
    public List<SectEntity> getSects() {
        return sectRepository.findAll();
    }

    @Transactional(readOnly = true)
    public SectEntity getSect(Long id) throws BadRequestException {
        if(id == null){
            throw BadRequestException.missingParameter();
        }
        return sectRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    public void addSect(String description){
        SectEntity sectEntity = new SectEntity(description);
        sectRepository.save(sectEntity);
    }

    public void modifySect(Long id, String description) throws BadRequestException {
        if(id == null) {
            throw BadRequestException.missingParameter();
        }
        sectRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new)
                .setDescription(description);
    }

    public void removeSect(Long id) throws BadRequestException {
        if(id == null) {
            throw BadRequestException.missingParameter();
        }
        sectRepository.delete(sectRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new));
    }

    public void createSect(String rawSect) throws BadRequestException {
        if(rawSect == null) {
            throw BadRequestException.missingParameter();
        }
        Gson gson = new Gson();
        SectEntity sect = gson.fromJson(rawSect, SectEntity.class);
        if(sect.isNull()) {
            throw BadRequestException.missingBody();
        }
        sectRepository.save(sect);
    }

    public void updateSect(String rawSect) throws BadRequestException, EntityRequestException {
        if(rawSect == null) {
            throw BadRequestException.missingParameter();
        }
        Gson gson = new Gson();
        final SectEntity sect = gson.fromJson(rawSect, SectEntity.class);
        if(sect.isNull()) {
            throw BadRequestException.missingBody();
        }
        updateSectData(sect);
    }

    private void updateSectData(SectEntity sect) throws EntityRequestException {
        if(!sectRepository.existsById(sect.getId())) {
            throw EntityRequestException.doesntExists(sect.getId());
        }
        sectRepository.save(sect);
    }
}
