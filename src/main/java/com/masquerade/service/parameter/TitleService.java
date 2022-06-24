package com.masquerade.service.parameter;

import com.google.gson.Gson;
import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.parameter.TitleEntity;
import com.masquerade.repository.parameter.TitleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TitleService {

    private final TitleRepository titleRepository;

    public TitleService(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    @Transactional(readOnly = true)
    public List<TitleEntity> getTitles() {
        return titleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public TitleEntity getTitle(Long id) throws BadRequestException {
        if(id == null){
            throw BadRequestException.missingParameter();
        }
        return titleRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
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

    private void updateTitleData(TitleEntity archetype) throws EntityRequestException {
        if(!titleRepository.existsById(archetype.getId())) {
            throw EntityRequestException.doesntExists(archetype.getId());
        }
        titleRepository.save(archetype);
    }
}
