package com.masquerade.service.parameter;

import com.google.gson.Gson;
import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.parameter.StatusTypeEntity;
import com.masquerade.repository.parameter.StatusTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StatusTypeService {

    private final StatusTypeRepository statusTypeRepository;

    public StatusTypeService(StatusTypeRepository statusTypeRepository) {
        this.statusTypeRepository = statusTypeRepository;
    }

    @Transactional(readOnly = true)
    public List<StatusTypeEntity> getStatusTypes() {
        return statusTypeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public StatusTypeEntity getStatusType(Long id) throws BadRequestException {
        if(id == null){
            throw BadRequestException.missingParameter();
        }
        return statusTypeRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    public ResponseEntity<HttpStatus> removeStatusType(Long id) throws BadRequestException {
        if(id == null) {
            throw BadRequestException.missingParameter();
        }
        statusTypeRepository.delete(statusTypeRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> createStatusType(String rawClan) throws BadRequestException {
        if(rawClan == null) {
            throw BadRequestException.missingParameter();
        }
        Gson gson = new Gson();
        StatusTypeEntity statusType = gson.fromJson(rawClan, StatusTypeEntity.class);
        if(statusType.isNull()) {
            throw BadRequestException.missingBody();
        }
        statusTypeRepository.save(statusType);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<HttpStatus> updateStatusType(final String rawClan) throws EntityRequestException {
        if(rawClan == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Gson gson = new Gson();
        final StatusTypeEntity statusType = gson.fromJson(rawClan, StatusTypeEntity.class);
        if(statusType.isNull()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        updateStatusTypeData(statusType);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void updateStatusTypeData(StatusTypeEntity statusType) throws EntityRequestException {
        if(!statusTypeRepository.existsById(statusType.getId())) {
            throw EntityRequestException.doesntExists(statusType.getId());
        }
        statusTypeRepository.save(statusType);
    }
}
