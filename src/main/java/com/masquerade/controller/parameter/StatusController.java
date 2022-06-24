package com.masquerade.controller.parameter;

import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.entity.parameter.StatusEntity;
import com.masquerade.service.parameter.StatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class StatusController {
    private final String ServicePrefix = "/parameter";

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @RequestMapping(value = ServicePrefix + "/getStatus",method = RequestMethod.GET)
    public ResponseEntity<List<StatusEntity>> getStatus() {
        return new ResponseEntity<>(statusService.getStatus(), HttpStatus.OK);
    }

    @RequestMapping(value = ServicePrefix + "/getStatusById",method = RequestMethod.GET)
    public ResponseEntity<StatusEntity> getStatusById(Long id) throws BadRequestException {
        return new ResponseEntity<>(statusService.getStatus(id), HttpStatus.OK);
    }

    @PostMapping(value = ServicePrefix + "/createStatus", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> createStatus(@RequestBody String rawBody) throws BadRequestException {
        return statusService.createStatus(rawBody);
    }

    @PostMapping(value = ServicePrefix + "/updateStatus", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> updateStatus(@RequestBody String rawBody) throws EntityRequestException {
        return statusService.updateStatus(rawBody);
    }

    @RequestMapping(value = ServicePrefix + "/removeStatus",method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> removeStatus(Long id) throws BadRequestException {
        return statusService.removeStatus(id);
    }

    @RequestMapping(value = ServicePrefix + "/updateStatusSect",method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> updateStatusSect(Long id, Long sectId) throws BadRequestException {
        return statusService.updateStatusSect(id, sectId);
    }

    @RequestMapping(value = ServicePrefix + "/updateStatusType",method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> updateStatusType(Long id, Long typeId) throws BadRequestException {
        return statusService.updateStatusType(id, typeId);
    }
}
