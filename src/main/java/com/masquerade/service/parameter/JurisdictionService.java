package com.masquerade.service.parameter;


import com.google.gson.Gson;
import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.parameter.JurisdictionEntity;
import com.masquerade.repository.parameter.JurisdictionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JurisdictionService {

    private final JurisdictionRepository jurisdictionRepository;

    public JurisdictionService(JurisdictionRepository jurisdictionRepository) { this.jurisdictionRepository = jurisdictionRepository; }

    @Transactional(readOnly = true)
    public List<JurisdictionEntity> getJurisdictions() {
        return jurisdictionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public JurisdictionEntity getJurisdiction(Long id) throws BadRequestException {
        if(id == null){
            throw BadRequestException.missingParameter();
        }
        return jurisdictionRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }


    public ResponseEntity<HttpStatus> removeJurisdiction(Long id) throws BadRequestException {
        if(id == null) {
            throw BadRequestException.missingParameter();
        }
        jurisdictionRepository.delete(jurisdictionRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> createJurisdiction(String rawJurisdiction) throws BadRequestException {
        if(rawJurisdiction == null) {
            throw BadRequestException.missingParameter();
        }
        Gson gson = new Gson();
        JurisdictionEntity jurisdiction = gson.fromJson(rawJurisdiction, JurisdictionEntity.class);
        if(jurisdiction.emptyObjectCheck()) {
            throw BadRequestException.missingBody();
        }
        jurisdictionRepository.save(jurisdiction);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<HttpStatus> updateJurisdiction(final String rawJurisdiction) throws EntityRequestException {
        if(rawJurisdiction == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Gson gson = new Gson();
        final JurisdictionEntity jurisdiction = gson.fromJson(rawJurisdiction, JurisdictionEntity.class);
        if(jurisdiction.emptyObjectCheck()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        updateJurisdictionData(jurisdiction);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void updateJurisdictionData(JurisdictionEntity jurisdiction) throws EntityRequestException {
        if(!jurisdictionRepository.existsById(jurisdiction.getId())) {
            throw EntityRequestException.doesntExists(jurisdiction.getId());
        }
        jurisdictionRepository.save(jurisdiction);
    }
}
