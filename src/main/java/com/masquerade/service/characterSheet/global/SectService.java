package com.masquerade.service.characterSheet.global;

import com.google.gson.Gson;
import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.entity.characterSheet.global.SectEntity;
import com.masquerade.model.entity.characterSheet.global.TitleEntity;
import com.masquerade.repository.characterSheet.global.SectRepository;
import com.masquerade.tools.controller.Responses;
import com.masquerade.tools.entity.EntityArguments;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SectService {
    private final SectRepository sectRepository;

    public SectService(SectRepository sectRepository) { this.sectRepository = sectRepository; }

    @Transactional(readOnly = true)
    public ResponseDTO getSects() {
        return new ResponseDTO(HttpStatus.OK, sectRepository.findAll() );
    }

    @Transactional(readOnly = true)
    public ResponseDTO getSect(Long id) {
        if(id == null){
            return Responses.MissingArgument(EntityArguments.idArgument);
        }
        Optional<SectEntity> entries = sectRepository.findById(id);
        if(entries.isEmpty()) {
            return Responses.ResponseNoContent;
        }
        return new ResponseDTO(HttpStatus.OK, entries.get());
    }

    public ResponseDTO removeSect(Long id) {
        if(id == null) {
            return Responses.MissingArgument(EntityArguments.idArgument);
        }
        Optional<SectEntity> entity = sectRepository.findById(id);
        if(entity.isEmpty()) {
            return Responses.ResponseNoContent;
        }
        sectRepository.delete(entity.get());
        return new ResponseDTO(HttpStatus.OK, entity.get());
    }

    public ResponseDTO createSect(String rawBody) {
        if(rawBody == null) {
            return Responses.MissingArgument(EntityArguments.JsonArgument);
        }
        SectEntity sect;
        try {
            Gson gson = new Gson();
            sect = gson.fromJson(rawBody, SectEntity.class);
            sect.setId(null);
            sect = sectRepository.save(sect);
        } catch (Exception e) {
            return Responses.ResponseBadRequest;
        }
        return new ResponseDTO(HttpStatus.CREATED, sect);
    }

    public ResponseDTO updateSect(String rawBody) {
        if(rawBody == null) {
            return Responses.MissingArgument(EntityArguments.JsonArgument);
        }
        try {
            Gson gson = new Gson();
            SectEntity sect = gson.fromJson(rawBody, SectEntity.class);
            if(sect == null || sect.emptyObjectCheck()) {
                return Responses.ResponseBadRequest;
            }
            if(!sectRepository.existsById(sect.getId())) {
                return Responses.ResponseNoContent;
            }
            sect = sectRepository.save(sect);
            return new ResponseDTO(HttpStatus.OK, sect);
        } catch (Exception e) {
            return Responses.ResponseBadRequest;
        }
    }
}
