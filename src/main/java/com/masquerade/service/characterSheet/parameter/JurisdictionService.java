package com.masquerade.service.characterSheet.parameter;

import com.google.gson.Gson;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.entity.characterSheet.parameter.JurisdictionEntity;
import com.masquerade.repository.characterSheet.parameter.JurisdictionRepository;
import com.masquerade.tools.controller.Responses;
import com.masquerade.tools.entity.EntityArguments;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class JurisdictionService {

    private final JurisdictionRepository jurisdictionRepository;

    public JurisdictionService(JurisdictionRepository jurisdictionRepository) { this.jurisdictionRepository = jurisdictionRepository; }

    @Transactional(readOnly = true)
    public ResponseDTO getJurisdictions() {
        return new ResponseDTO(HttpStatus.OK, jurisdictionRepository.findAll());
    }

    @Transactional(readOnly = true)
    public ResponseDTO getJurisdiction(Long id) {
        if(id == null){
            return Responses.MissingArgument(EntityArguments.idArgument);
        }
        Optional<JurisdictionEntity> entries = jurisdictionRepository.findById(id);
        if(entries.isEmpty()) {
            return Responses.ResponseNoContent;
        }
        return new ResponseDTO(HttpStatus.OK, entries.get());
    }


    public ResponseDTO removeJurisdiction(Long id) {
        if(id == null){
            return Responses.MissingArgument(EntityArguments.idArgument);
        }
        Optional<JurisdictionEntity> entity = jurisdictionRepository.findById(id);
        if(entity.isEmpty()) {
            return Responses.ResponseNoContent;
        }
        jurisdictionRepository.delete(entity.get());
        return new ResponseDTO(HttpStatus.OK, entity.get());
    }

    public ResponseDTO createJurisdiction(String rawBody) {
        if(rawBody == null) {
            return Responses.MissingArgument(EntityArguments.JsonArgument);
        }
        JurisdictionEntity jurisdiction;
        try {
            Gson gson = new Gson();
            jurisdiction = gson.fromJson(rawBody, JurisdictionEntity.class);
            jurisdiction.setId(null);
            jurisdiction = jurisdictionRepository.save(jurisdiction);
        } catch (Exception e) {
            return Responses.ResponseBadRequest;
        }
        return new ResponseDTO(HttpStatus.CREATED, jurisdiction);
    }

    public ResponseDTO updateJurisdiction(final String rawBody) {
        if(rawBody == null) {
            return Responses.MissingArgument(EntityArguments.JsonArgument);
        }
        try {
            Gson gson = new Gson();
            JurisdictionEntity jurisdiction = gson.fromJson(rawBody, JurisdictionEntity.class);
            if(jurisdiction == null || jurisdiction.emptyObjectCheck()) {
                return Responses.ResponseBadRequest;
            }
            if(!jurisdictionRepository.existsById(jurisdiction.getId())) {
                return Responses.ResponseNoContent;
            }
            jurisdiction = jurisdictionRepository.save(jurisdiction);
            return new ResponseDTO(HttpStatus.OK, jurisdiction);
        } catch (Exception e) {
            return Responses.ResponseBadRequest;
        }
    }
}
