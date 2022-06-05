package com.masquerade.service;

import com.masquerade.exception.BadRequestException;
import com.masquerade.model.EntityEntity;
import com.masquerade.model.SmallEntity;
import com.masquerade.repository.EntityRepository;
import com.masquerade.repository.SmallEntityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EntityService {
    private final EntityRepository entityRepository;
    private final SmallEntityRepository smallEntityRepository;

    public EntityService(EntityRepository entityRepository, SmallEntityRepository smallEntityRepository) {
        this.entityRepository = entityRepository;
        this.smallEntityRepository = smallEntityRepository;
    }

    @Transactional(readOnly = true)
    public List<SmallEntity> getList() {
        return smallEntityRepository.findAll();
    }

    @Transactional(readOnly = true)
    public EntityEntity getEntity(Long id) throws BadRequestException {
        if(id == null){
            throw BadRequestException.missingParameter();
        }
        return entityRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }
}
