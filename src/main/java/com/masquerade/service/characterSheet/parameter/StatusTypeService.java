package com.masquerade.service.characterSheet.parameter;

import com.google.gson.Gson;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.entity.characterSheet.parameter.StatusTypeEntity;
import com.masquerade.repository.characterSheet.parameter.StatusTypeRepository;
import com.masquerade.tools.controller.Responses;
import com.masquerade.tools.entity.EntityArguments;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class StatusTypeService {

    private final StatusTypeRepository statusTypeRepository;

    public StatusTypeService(StatusTypeRepository statusTypeRepository) {
        this.statusTypeRepository = statusTypeRepository;
    }

    @Transactional(readOnly = true)
    public ResponseDTO getStatusTypes() {
        return new ResponseDTO(HttpStatus.OK, statusTypeRepository.findAll());
    }

    @Transactional(readOnly = true)
    public ResponseDTO getStatusType(Long id) {
        if (id == null) {
            return Responses.MissingArgument(EntityArguments.idArgument);
        }
        Optional<StatusTypeEntity> entries = statusTypeRepository.findById(id);
        if (entries.isEmpty()) {
            return Responses.ResponseNoContent;
        }
        return new ResponseDTO(HttpStatus.OK, entries.get());
    }

    public ResponseDTO removeStatusType(Long id) {
        if (id == null) {
            return Responses.MissingArgument(EntityArguments.idArgument);
        }
        Optional<StatusTypeEntity> entity = statusTypeRepository.findById(id);
        if (entity.isEmpty()) {
            return Responses.ResponseNoContent;
        }
        statusTypeRepository.delete(entity.get());
        return new ResponseDTO(HttpStatus.OK, entity);
    }

    public ResponseDTO createStatusType(String rawBody) {
        if (rawBody == null) {
            return Responses.MissingArgument(EntityArguments.JsonArgument);
        }
        StatusTypeEntity statusTypeEntity;
        try {
            Gson gson = new Gson();
            statusTypeEntity = gson.fromJson(rawBody, StatusTypeEntity.class);
            statusTypeEntity.setId(null);
            return new ResponseDTO(HttpStatus.CREATED, statusTypeRepository.save(statusTypeEntity));
        } catch (Exception e) {
            return Responses.ResponseBadRequest;
        }
    }

    public ResponseDTO updateStatusType(String rawBody) {
        if(rawBody == null) {
            return Responses.MissingArgument(EntityArguments.JsonArgument);
        }
        try {
            Gson gson = new Gson();
            StatusTypeEntity statusTypeEntity = gson.fromJson(rawBody, StatusTypeEntity.class);
            if(statusTypeEntity == null || statusTypeEntity.emptyObjectCheck()) {
                return Responses.ResponseBadRequest;
            }
            if(!statusTypeRepository.existsById(statusTypeEntity.getId())) {
                return Responses.ResponseNoContent;
            }
            statusTypeEntity = statusTypeRepository.save(statusTypeEntity);
            return new ResponseDTO(HttpStatus.OK, statusTypeEntity);
        } catch (Exception e) {
            return Responses.ResponseBadRequest;
        }
    }
}
