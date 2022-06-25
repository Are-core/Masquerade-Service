package com.masquerade.service.characterSheet.parameter;

import com.google.gson.Gson;
import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.entity.characterSheet.global.SectEntity;
import com.masquerade.model.entity.characterSheet.parameter.StatusEntity;
import com.masquerade.model.entity.characterSheet.parameter.StatusTypeEntity;
import com.masquerade.repository.characterSheet.parameter.SectRepository;
import com.masquerade.repository.characterSheet.parameter.StatusRepository;
import com.masquerade.repository.characterSheet.parameter.StatusTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StatusService {
    private final StatusRepository statusRepository;
    private final StatusTypeRepository statusTypeRepository;
    private final SectRepository sectRepository;

    public StatusService(StatusRepository statusRepository, StatusTypeRepository statusTypeRepository, SectRepository sectRepository) {
        this.statusRepository = statusRepository;
        this.statusTypeRepository = statusTypeRepository;
        this.sectRepository = sectRepository;
    }

    @Transactional(readOnly = true)
    public List<StatusEntity> getStatus() {
        return statusRepository.findAll();
    }

    @Transactional(readOnly = true)
    public StatusEntity getStatus(Long id) throws BadRequestException {
        if(id == null){
            throw BadRequestException.missingParameter();
        }
        return statusRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    public ResponseEntity<HttpStatus> createStatus(String rawBody) throws BadRequestException {
        if(rawBody == null) {
            throw BadRequestException.missingParameter();
        }
        Gson gson = new Gson();
        StatusEntity status = gson.fromJson(rawBody, StatusEntity.class);
        if(status.emptyObjectCheck()) {
            throw BadRequestException.missingBody();
        }
        status.setId(null);
        statusRepository.save(status);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<HttpStatus> updateStatus(final String rawBody) throws EntityRequestException {
        if(rawBody == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Gson gson = new Gson();
        final StatusEntity status = gson.fromJson(rawBody, StatusEntity.class);
        if(status.emptyObjectCheck()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        updateStatusData(status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void updateStatusData(StatusEntity status) throws EntityRequestException {
        if(!statusRepository.existsById(status.getId())) {
            throw EntityRequestException.doesntExists(status.getId());
        }
        statusRepository.save(status);
    }

    public ResponseEntity<HttpStatus> removeStatus(Long id) throws BadRequestException {
        if(id == null) {
            throw BadRequestException.missingParameter();
        }
        statusRepository.delete(statusRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> updateStatusSect(Long id, Long sect_id) throws BadRequestException {
        if(id == null || sect_id == null) {
            throw BadRequestException.missingParameter();
        }
        SectEntity sect = sectRepository.findById(sect_id)
                .orElseThrow(IllegalArgumentException::new);
        statusRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new)
                .setSect_id(sect);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> updateStatusType(Long id, Long typeId) throws BadRequestException {
        if(id == null || typeId == null) {
            throw BadRequestException.missingParameter();
        }
        StatusTypeEntity type = statusTypeRepository.findById(typeId)
                .orElseThrow(IllegalArgumentException::new);
        statusRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new)
                .setStatusType(type);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
