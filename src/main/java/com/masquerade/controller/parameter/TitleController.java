package com.masquerade.controller.parameter;

import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.parameter.TitleEntity;
import com.masquerade.service.parameter.TitleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TitleController {
    private final String ServicePrefix = "/parameter";

    private final TitleService titleService;

    public TitleController(TitleService titleService) {
        this.titleService = titleService;
    }

    @RequestMapping(value = ServicePrefix + "/getTitles",method = RequestMethod.GET)
    public ResponseEntity<List<TitleEntity>> getTitles() {
        return new ResponseEntity<>(titleService.getTitles(), HttpStatus.OK);
    }

    @RequestMapping(value = ServicePrefix + "/getTitleById",method = RequestMethod.GET)
    public ResponseEntity<TitleEntity> getArchetype(Long id) throws BadRequestException {
        return new ResponseEntity<>(titleService.getTitle(id), HttpStatus.OK);
    }

    @RequestMapping(value = ServicePrefix + "/removeTitle",method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> removeTitle(Long id) throws BadRequestException {
        return titleService.removeTitle(id);
    }

    @PostMapping(value = ServicePrefix + "/createTitle", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> createTitle(@RequestBody String rawTitle) throws BadRequestException {
        return titleService.createTitle(rawTitle);
    }

    @PostMapping(value = ServicePrefix + "/updateTitle", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> updateTitle(@RequestBody String rawTitle) throws EntityRequestException {
        return titleService.updateTitle(rawTitle);
    }
}
