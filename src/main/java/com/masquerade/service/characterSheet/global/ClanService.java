package com.masquerade.service.characterSheet.global;

import com.google.gson.Gson;
import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.entity.characterSheet.global.ClanEntity;
import com.masquerade.repository.characterSheet.global.ClanRepository;
import com.masquerade.tools.controller.Responses;
import com.masquerade.tools.entity.EntityArguments;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ClanService {
    private final ClanRepository clanRepository;

    public ClanService(ClanRepository clanRepository) {
        this.clanRepository = clanRepository;
    }

    @Transactional(readOnly = true)
    public ResponseDTO getClans() {
        return new ResponseDTO(HttpStatus.OK,clanRepository.findAll());
    }

    @Transactional(readOnly = true)
    public ResponseDTO getClan(Long id) {
        if(id == null){
            return Responses.MissingArgument(EntityArguments.idArgument);
        }
        Optional<ClanEntity> entries = clanRepository.findById(id);
        if(entries.isEmpty()) {
            return Responses.ResponseNoContent;
        }
        return new ResponseDTO(HttpStatus.OK, entries.get());
    }

    public ResponseDTO removeClan(Long id) {
        if(id == null) {
            return Responses.MissingArgument(EntityArguments.idArgument);
        }
        Optional<ClanEntity> entity = clanRepository.findById(id);
        if(entity.isEmpty()) {
            return Responses.ResponseNoContent;
        }
        clanRepository.delete(entity.get());
        return new ResponseDTO(HttpStatus.OK, entity.get());
    }

    public ResponseDTO createClan(String rawBody) {
        if(rawBody == null) {
            return Responses.MissingArgument(EntityArguments.JsonArgument);
        }
        ClanEntity clan;
        try {
            Gson gson = new Gson();
            clan = gson.fromJson(rawBody, ClanEntity.class);
            clan.setId(null);
            clan = clanRepository.save(clan);
            return new ResponseDTO(HttpStatus.CREATED, clan);
        } catch (Exception e) {
            return Responses.ResponseBadRequest;
        }
    }

    public ResponseDTO updateClan(final String rawBody) {
        if(rawBody == null) {
            return Responses.MissingArgument(EntityArguments.JsonArgument);
        }
        try {
            Gson gson = new Gson();
            ClanEntity clan = gson.fromJson(rawBody, ClanEntity.class);
            if(clan == null || clan.emptyObjectCheck()) {
                return Responses.ResponseBadRequest;
            }
            if(!clanRepository.existsById(clan.getId())) {
                return Responses.ResponseNoContent;
            }
            clan = clanRepository.save(clan);
            return new ResponseDTO(HttpStatus.OK, clan);
        } catch (Exception e) {
            return Responses.ResponseBadRequest;
        }
    }
}
