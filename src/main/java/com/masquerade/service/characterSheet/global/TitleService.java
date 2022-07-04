package com.masquerade.service.characterSheet.global;

import com.google.gson.Gson;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.entity.characterSheet.global.TitleEntity;
import com.masquerade.repository.characterSheet.global.TitleRepository;
import com.masquerade.tools.controller.Responses;
import com.masquerade.tools.entity.EntityArguments;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TitleService {

    private final TitleRepository titleRepository;

    public TitleService(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    @Transactional(readOnly = true)
    public ResponseDTO getTitles() {
        return new ResponseDTO(HttpStatus.OK,titleRepository.findAll());
    }

    @Transactional(readOnly = true)
    public ResponseDTO getTitle(Long id) {
        if(id == null){
            return Responses.MissingArgument(EntityArguments.idArgument);
        }
        Optional<TitleEntity> entries = titleRepository.findById(id);
        if(entries.isEmpty()) {
            return Responses.ResponseNoContent;
        }
        return new ResponseDTO(HttpStatus.OK, entries.get());
    }

    public ResponseDTO removeTitle(Long id)  {
        if(id == null) {
            return Responses.MissingArgument(EntityArguments.idArgument);
        }
        Optional<TitleEntity> entity = titleRepository.findById(id);
        if(entity.isEmpty()) {
            return Responses.ResponseNoContent;
        }
        titleRepository.delete(entity.get());
        return new ResponseDTO(HttpStatus.OK, entity.get());
    }

    public ResponseDTO createTitle(String rawBody) {
        if(rawBody == null) {
            return Responses.MissingArgument(EntityArguments.JsonArgument);
        }
        TitleEntity title;
        try {
            Gson gson = new Gson();
            title = gson.fromJson(rawBody, TitleEntity.class);
            title.setId(null);
            title = titleRepository.save(title);
        } catch (Exception e) {
            return Responses.ResponseBadRequest;
        }
        return new ResponseDTO(HttpStatus.CREATED, title);
    }

    public ResponseDTO updateTitle(final String rawBody) {
        if(rawBody == null) {
            return Responses.MissingArgument(EntityArguments.JsonArgument);
        }
        try {
            Gson gson = new Gson();
            TitleEntity title = gson.fromJson(rawBody, TitleEntity.class);
            if(title == null || title.emptyObjectCheck()) {
                return Responses.ResponseBadRequest;
            }
            if(!titleRepository.existsById(title.getId())) {
                return Responses.ResponseNoContent;
            }
            title = titleRepository.save(title);
            return new ResponseDTO(HttpStatus.OK, title);
        } catch (Exception e) {
            return Responses.ResponseBadRequest;
        }
    }
}
