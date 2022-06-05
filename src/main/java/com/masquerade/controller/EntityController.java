package com.masquerade.controller;

import com.masquerade.exception.BadRequestException;
import com.masquerade.model.EntityEntity;
import com.masquerade.model.SmallEntity;
import com.masquerade.service.EntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EntityController {
    private final EntityService entityService;

    public EntityController(EntityService entityService) {
        this.entityService = entityService;
    }

    @RequestMapping(value ="/getEntities",method = RequestMethod.GET)
    public ResponseEntity<List<SmallEntity>> getEntities() {
        return new ResponseEntity<>(entityService.getList(), HttpStatus.OK);
    }

    @RequestMapping(value ="/getEntity",method = RequestMethod.GET)
    public ResponseEntity<EntityEntity> getEntity(Long id) throws BadRequestException {
        return new ResponseEntity<>(entityService.getEntity(id), HttpStatus.OK);
    }
}
