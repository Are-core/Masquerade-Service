package com.masquerade.service.parameter;

import com.google.gson.Gson;
import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.DTO.parameter.StatusDTO;
import com.masquerade.model.entity.parameter.SectEntity;
import com.masquerade.model.entity.parameter.StatusEntity;
import com.masquerade.model.entity.parameter.StatusTypeEntity;
import com.masquerade.repository.parameter.SectRepository;
import com.masquerade.repository.parameter.StatusRepository;
import com.masquerade.repository.parameter.StatusTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public List<StatusDTO> getStatus() {
        List<StatusEntity> rawStatus = statusRepository.findAll();
        List<StatusDTO> statusDto = new ArrayList<>();
        for(StatusEntity status : rawStatus) {
            StatusDTO dtoObject = new StatusDTO(status);
            getExternalObjects(status, dtoObject);
            statusDto.add(dtoObject);
        }
        return statusDto;
    }

    @Transactional(readOnly = true)
    public StatusDTO getStatus(Long id) throws BadRequestException {
        if(id == null){
            throw BadRequestException.missingParameter();
        }
        StatusEntity rawStatus = statusRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        if(rawStatus != null) {
            StatusDTO dtoObject = new StatusDTO(rawStatus);
            getExternalObjects(rawStatus, dtoObject);
            return dtoObject;
        }
        return null;
    }

    private void getExternalObjects(StatusEntity status, StatusDTO dtoObject) {
        setSect(status, dtoObject);
        setType(status, dtoObject);
    }

    private void setSect(StatusEntity status, StatusDTO dtoObject) {
        try {
            if (status.getSect_id() != null) {
                SectEntity sectRaw = sectRepository.findById(status.getSect_id())
                        .orElseThrow(IllegalArgumentException::new);
                dtoObject.setSect(sectRaw);
            }
        } catch(Exception ignored) {

        }
    }

    private void setType(StatusEntity status, StatusDTO dtoObject) {
        try {
            if(status.getType_id() != null) {
                StatusTypeEntity sectRaw = statusTypeRepository.findById(status.getType_id())
                        .orElseThrow(IllegalArgumentException::new);
                dtoObject.setType(sectRaw);
            }
        } catch(Exception ignored) {

        }
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
        statusRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new)
                .setSect_id(sect_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> updateStatusType(Long id, Long type_id) throws BadRequestException {
        if(id == null || type_id == null) {
            throw BadRequestException.missingParameter();
        }
        statusRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new)
                .setType_id(type_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
