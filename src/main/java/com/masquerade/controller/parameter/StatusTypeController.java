package com.masquerade.controller.parameter;

import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.parameter.StatusTypeEntity;
import com.masquerade.service.parameter.StatusTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class StatusTypeController {
    private final String ServicePrefix = "/parameter";
    private final StatusTypeService statusTypeService;

    public StatusTypeController(StatusTypeService statusTypeService) {
        this.statusTypeService = statusTypeService;
    }

    @RequestMapping(value = ServicePrefix + "/getStatusTypes",method = RequestMethod.GET)
    public ResponseEntity<List<StatusTypeEntity>> getStatusTypes() {
        return new ResponseEntity<>(statusTypeService.getStatusTypes(), HttpStatus.OK);
    }

    @RequestMapping(value = ServicePrefix + "/getStatusTypeById",method = RequestMethod.GET)
    public ResponseEntity<StatusTypeEntity> getStatusType(Long id) throws BadRequestException {
        return new ResponseEntity<>(statusTypeService.getStatusType(id), HttpStatus.OK);
    }

    @RequestMapping(value = ServicePrefix + "/removeStatusType",method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> removeStatusType(Long id) throws BadRequestException {
        return statusTypeService.removeStatusType(id);
    }

    @PostMapping(value = ServicePrefix + "/createStatusType", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> createStatusType(@RequestBody String rawClan) throws BadRequestException {
        return statusTypeService.createStatusType(rawClan);
    }

    @PostMapping(value = ServicePrefix + "/updateStatusType", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> updateStatusType(@RequestBody String rawClan) throws EntityRequestException {
        return statusTypeService.updateStatusType(rawClan);
    }
}
