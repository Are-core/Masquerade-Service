package com.masquerade.service.parameter;

import com.google.gson.Gson;
import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.parameter.ClanEntity;
import com.masquerade.repository.parameter.ClanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClanService {
    private final ClanRepository clanRepository;

    public ClanService(ClanRepository clanRepository) {
        this.clanRepository = clanRepository;
    }

    @Transactional(readOnly = true)
    public List<ClanEntity> getClans() {
        return clanRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ClanEntity getClan(Long id) throws BadRequestException {
        if(id == null){
            throw BadRequestException.missingParameter();
        }
        return clanRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    public ResponseEntity<HttpStatus> removeClan(Long id) throws BadRequestException {
        if(id == null) {
            throw BadRequestException.missingParameter();
        }
        clanRepository.delete(clanRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> createClan(String rawClan) throws BadRequestException {
        if(rawClan == null) {
            throw BadRequestException.missingParameter();
        }
        Gson gson = new Gson();
        ClanEntity clan = gson.fromJson(rawClan, ClanEntity.class);
        if(clan.emptyObjectCheck()) {
            throw BadRequestException.missingBody();
        }
        clanRepository.save(clan);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<HttpStatus> updateClan(final String rawClan) throws EntityRequestException {
        if(rawClan == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Gson gson = new Gson();
        final ClanEntity clan = gson.fromJson(rawClan, ClanEntity.class);
        if(clan.emptyObjectCheck()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        updateClanData(clan);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void updateClanData(ClanEntity clan) throws EntityRequestException {
        if(!clanRepository.existsById(clan.getId())) {
            throw EntityRequestException.doesntExists(clan.getId());
        }
        clanRepository.save(clan);
    }
}
