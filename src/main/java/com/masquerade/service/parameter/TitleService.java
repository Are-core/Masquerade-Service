package com.masquerade.service.parameter;

import com.google.gson.Gson;
import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.DTO.parameter.TitleDTO;
import com.masquerade.model.entity.parameter.SectEntity;
import com.masquerade.model.entity.parameter.TitleEntity;
import com.masquerade.repository.parameter.SectRepository;
import com.masquerade.repository.parameter.TitleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TitleService {

    private final TitleRepository titleRepository;
    private final SectRepository sectRepository;

    public TitleService(TitleRepository titleRepository, SectRepository sectRepository) {
        this.titleRepository = titleRepository;
        this.sectRepository = sectRepository;
    }

    @Transactional(readOnly = true)
    public List<TitleDTO> getTitles() {
        List<TitleEntity> rawTitles = titleRepository.findAll();
        List<TitleDTO> titles = new ArrayList<>();
        for(TitleEntity title : rawTitles) {
            TitleDTO dtoObject = new TitleDTO(title);
            setSect(title, dtoObject);
            titles.add(dtoObject);
        }
        return titles;
    }

    @Transactional(readOnly = true)
    public TitleDTO getTitle(Long id) throws BadRequestException {
        if(id == null){
            throw BadRequestException.missingParameter();
        }
        TitleEntity titleRaw = titleRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        if(titleRaw != null) {
            TitleDTO titleDto = new TitleDTO(titleRaw);
            setSect(titleRaw, titleDto);
            return titleDto;
        }
        return null;
    }

    public ResponseEntity<HttpStatus> removeTitle(Long id) throws BadRequestException {
        if(id == null) {
            throw BadRequestException.missingParameter();
        }
        titleRepository.delete(titleRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> createTitle(String rawTitle) throws BadRequestException {
        if(rawTitle == null) {
            throw BadRequestException.missingParameter();
        }
        Gson gson = new Gson();
        TitleEntity title = gson.fromJson(rawTitle, TitleEntity.class);
        if(title.emptyObjectCheck()) {
            throw BadRequestException.missingBody();
        }
        titleRepository.save(title);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<HttpStatus> updateTitle(final String rawTitle) throws EntityRequestException {
        if(rawTitle == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Gson gson = new Gson();
        final TitleEntity title = gson.fromJson(rawTitle, TitleEntity.class);
        if(title.emptyObjectCheck()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        updateTitleData(title);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> updateTitleSect(Long id, Long sect_id) throws BadRequestException {
        if(id == null || sect_id == null) {
            throw BadRequestException.missingParameter();
        }
        titleRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new)
                .setSect_id(sect_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void updateTitleData(TitleEntity archetype) throws EntityRequestException {
        if(!titleRepository.existsById(archetype.getId())) {
            throw EntityRequestException.doesntExists(archetype.getId());
        }
        titleRepository.save(archetype);
    }

    private void setSect(TitleEntity title, TitleDTO dtoObject) {
        if(title.getSect_id() != null) {
            SectEntity sectRaw = sectRepository.findById(title.getSect_id())
                    .orElseThrow(IllegalArgumentException::new);
            dtoObject.setSect(sectRaw);
        }
    }
}
