package com.masquerade.service.characterSheet.parameter;

import com.google.gson.Gson;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.entity.characterSheet.parameter.StatusEntity;
import com.masquerade.service.repository.characterSheet.parameter.StatusRepository;
import com.masquerade.tools.controller.Responses;
import com.masquerade.tools.entity.EntityArguments;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class StatusService {
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Transactional(readOnly = true)
    public ResponseDTO getStatus() {
        return new ResponseDTO(HttpStatus.OK, statusRepository.findAll());
    }

    @Transactional(readOnly = true)
    public ResponseDTO getStatus(Long id) {
        if(id == null){
            return Responses.MissingArgument(EntityArguments.idArgument);
        }
        Optional<StatusEntity> entries = statusRepository.findById(id);
        if(entries.isEmpty()) {
            return Responses.ResponseNoContent;
        }
        return new ResponseDTO(HttpStatus.OK, entries.get());
    }

    public ResponseDTO createStatus(String rawBody) {
        if(rawBody == null) {
            return Responses.MissingArgument(EntityArguments.JsonArgument);
        }
        StatusEntity status;
        try {
            Gson gson = new Gson();
            status = gson.fromJson(rawBody, StatusEntity.class);
            status.setId(null);
            return new ResponseDTO(HttpStatus.CREATED, statusRepository.save(status));
        } catch(Exception e) {
            return Responses.ResponseBadRequest;
        }
    }

    public ResponseDTO updateStatus(final String rawBody) {
        if(rawBody == null) {
            return Responses.MissingArgument(EntityArguments.JsonArgument);
        }
        try {
            Gson gson = new Gson();
            StatusEntity status = gson.fromJson(rawBody, StatusEntity.class);
            if(status == null || status.emptyObjectCheck()) {
                return Responses.ResponseBadRequest;
            }
            if(!statusRepository.existsById(status.getId())) {
                return Responses.ResponseNoContent;
            }
            return new ResponseDTO(HttpStatus.OK, statusRepository.save(status));
        } catch ( Exception e) {
            return Responses.ResponseBadRequest;
        }
    }

    public ResponseDTO removeStatus(Long id) {
        if(id == null) {
            return Responses.MissingArgument(EntityArguments.idArgument);
        }
        Optional<StatusEntity> entity = statusRepository.findById(id);
        if (entity.isEmpty()) {
            return Responses.ResponseNoContent;
        }
        statusRepository.delete(entity.get());
        return new ResponseDTO(HttpStatus.OK, entity);
    }
}
