package com.masquerade.controller.characterSheet.parameter;

import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.entity.characterSheet.parameter.StatusTypeEntity;
import com.masquerade.service.characterSheet.parameter.StatusTypeService;
import com.masquerade.tools.controller.Section;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = Section.CrossOriginUrl)
@RestController
public class StatusTypeController {
    private final StatusTypeService statusTypeService;

    public StatusTypeController(StatusTypeService statusTypeService) {
        this.statusTypeService = statusTypeService;
    }

    @RequestMapping(value = Section.StatusTypePrefix + "/getStatusTypes",method = RequestMethod.GET)
    public ResponseEntity<List<StatusTypeEntity>> getStatusTypes() {
        return new ResponseEntity<>(statusTypeService.getStatusTypes(), HttpStatus.OK);
    }

    @RequestMapping(value = Section.StatusTypePrefix + "/getStatusTypeById",method = RequestMethod.GET)
    public ResponseEntity<StatusTypeEntity> getStatusType(Long id) throws BadRequestException {
        return new ResponseEntity<>(statusTypeService.getStatusType(id), HttpStatus.OK);
    }

    @RequestMapping(value = Section.StatusTypePrefix + "/removeStatusType",method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> removeStatusType(Long id) throws BadRequestException {
        return statusTypeService.removeStatusType(id);
    }

    @PostMapping(value = Section.StatusTypePrefix + "/createStatusType", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> createStatusType(@RequestBody String rawClan) throws BadRequestException {
        return statusTypeService.createStatusType(rawClan);
    }

    @PostMapping(value = Section.StatusTypePrefix + "/updateStatusType", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> updateStatusType(@RequestBody String rawClan) throws EntityRequestException {
        return statusTypeService.updateStatusType(rawClan);
    }
}
