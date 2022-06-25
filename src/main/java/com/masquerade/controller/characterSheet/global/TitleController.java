package com.masquerade.controller.characterSheet.global;

import com.masquerade.exception.BadRequestException;
import com.masquerade.exception.EntityRequestException;
import com.masquerade.model.entity.characterSheet.global.TitleEntity;
import com.masquerade.service.characterSheet.global.TitleService;
import com.masquerade.tools.Section;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = Section.CrossOriginUrl)
@RestController
public class TitleController {
    private final TitleService titleService;

    public TitleController(TitleService titleService) {
        this.titleService = titleService;
    }

    @RequestMapping(value = Section.TitlePrefix + "/getTitles",method = RequestMethod.GET)
    public ResponseEntity<List<TitleEntity>> getTitles() {
        return new ResponseEntity<>(titleService.getTitles(), HttpStatus.OK);
    }

    @RequestMapping(value = Section.TitlePrefix + "/getTitleById",method = RequestMethod.GET)
    public ResponseEntity<TitleEntity> getArchetype(Long id) throws BadRequestException {
        return new ResponseEntity<>(titleService.getTitle(id), HttpStatus.OK);
    }

    @RequestMapping(value = Section.TitlePrefix + "/removeTitle",method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> removeTitle(Long id) throws BadRequestException {
        return titleService.removeTitle(id);
    }

    @PostMapping(value = Section.TitlePrefix + "/createTitle", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> createTitle(@RequestBody String rawTitle) throws BadRequestException {
        return titleService.createTitle(rawTitle);
    }

    @PostMapping(value = Section.TitlePrefix + "/updateTitle", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> updateTitle(@RequestBody String rawTitle) throws EntityRequestException {
        return titleService.updateTitle(rawTitle);
    }

    @RequestMapping(value = Section.TitlePrefix + "/updateTitleSect",method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> updateTitle(Long id, Long sectId) throws BadRequestException {
        return titleService.updateTitleSect(id, sectId);
    }
}
