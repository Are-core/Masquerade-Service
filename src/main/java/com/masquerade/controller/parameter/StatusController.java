package com.masquerade.controller.parameter;

import com.masquerade.model.DTO.parameter.StatusDTO;
import com.masquerade.service.parameter.StatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<StatusDTO>> getStatus() {
        return new ResponseEntity<>(statusService.getStatus(), HttpStatus.OK);
    }
}
