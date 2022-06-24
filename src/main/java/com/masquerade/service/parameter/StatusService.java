package com.masquerade.service.parameter;

import com.masquerade.model.DTO.parameter.StatusDTO;
import com.masquerade.model.entity.parameter.SectEntity;
import com.masquerade.model.entity.parameter.StatusEntity;
import com.masquerade.model.entity.parameter.StatusTypeEntity;
import com.masquerade.repository.parameter.SectRepository;
import com.masquerade.repository.parameter.StatusRepository;
import com.masquerade.repository.parameter.StatusTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StatusService {
    private final StatusRepository statusRepository;
    private final StatusTypeRepository statusTypeRepository;
    private final SectRepository sectRepository;

    public StatusService(StatusRepository statusRepository, StatusTypeRepository statusTypeRepository, SectRepository sectRepository) {
        this.statusRepository = statusRepository;
        this.statusTypeRepository = statusTypeRepository;
        this.sectRepository = sectRepository;
    }

    @Transactional(readOnly = true)
    public List<StatusDTO> getStatus() {
        List<StatusEntity> rawStatus = statusRepository.findAll();
        List<StatusDTO> statusDto = new ArrayList<>();
        for(StatusEntity status : rawStatus) {
            StatusDTO dtoObject = new StatusDTO(status);
            getExternalObjects(status, dtoObject);
            statusDto.add(dtoObject);
        }
        return statusDto;
    }

    private void getExternalObjects(StatusEntity status, StatusDTO dtoObject) {
        setSect(status, dtoObject);
        setType(status, dtoObject);
    }

    private void setSect(StatusEntity status, StatusDTO dtoObject) {
        try {
            if (status.getSect_id() != null) {
                SectEntity sectRaw = sectRepository.findById(status.getSect_id())
                        .orElseThrow(IllegalArgumentException::new);
                dtoObject.setSect(sectRaw);
            }
        } catch(Exception ignored) {

        }
    }

    private void setType(StatusEntity status, StatusDTO dtoObject) {
        try {
            if(status.getType_id() != null) {
                StatusTypeEntity sectRaw = statusTypeRepository.findById(status.getType_id())
                        .orElseThrow(IllegalArgumentException::new);
                dtoObject.setType(sectRaw);
            }
        } catch(Exception ignored) {

        }
    }
}
