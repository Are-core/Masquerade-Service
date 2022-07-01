package com.masquerade.service.characterSheet.global;

import com.google.gson.Gson;
import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.entity.characterSheet.global.SectEntity;
import com.masquerade.model.entity.characterSheet.global.TitleEntity;
import com.masquerade.repository.characterSheet.global.SectRepository;
import com.masquerade.repository.characterSheet.global.TitleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public ResponseEntity<HttpStatus> updateTitleSect(Long id, Long sect_id) throws BadRequestException {
        if(id == null || sect_id == null) {
            throw BadRequestException.missingParameter();
        }
        SectEntity sect = sectRepository.findById(sect_id)
                .orElseThrow(IllegalArgumentException::new);
        titleRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new)
                .setSect(sect);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void updateTitleData(TitleEntity archetype) throws EntityRequestException {
        if(!titleRepository.existsById(archetype.getId())) {
            throw EntityRequestException.doesntExists(archetype.getId());
        }
        titleRepository.save(archetype);
    }
}
