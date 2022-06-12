package com.masquerade.service.parameter;

import com.google.gson.Gson;
import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.parameter.SectEntity;
import com.masquerade.repository.parameter.SectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<HttpStatus> removeSect(Long id) throws BadRequestException {
        if(id == null) {
            throw BadRequestException.missingParameter();
        }
        sectRepository.delete(sectRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> createSect(String rawSect) throws BadRequestException {
        if(rawSect == null) {
            throw BadRequestException.missingParameter();
        }
        Gson gson = new Gson();
        SectEntity sect = gson.fromJson(rawSect, SectEntity.class);
        if(sect.emptyObjectCheck()) {
            throw BadRequestException.missingBody();
        }
        sectRepository.save(sect);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<HttpStatus> updateSect(String rawSect) throws EntityRequestException {
        if(rawSect == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Gson gson = new Gson();
        final SectEntity sect = gson.fromJson(rawSect, SectEntity.class);
        if(sect.emptyObjectCheck()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        updateSectData(sect);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void updateSectData(SectEntity sect) throws EntityRequestException {
        if(!sectRepository.existsById(sect.getId())) {
            throw EntityRequestException.doesntExists(sect.getId());
        }
        sectRepository.save(sect);
    }
}
