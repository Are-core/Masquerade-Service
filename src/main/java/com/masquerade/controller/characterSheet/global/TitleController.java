package com.masquerade.controller.characterSheet.global;

import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.dto.controller.ResponseEntityDTO;
import com.masquerade.service.characterSheet.global.TitleService;
import com.masquerade.tools.controller.Section;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = Section.CrossOriginUrl)
@RestController
public class TitleController {
    private final TitleService titleService;

    public TitleController(TitleService titleService) {
        this.titleService = titleService;
    }

    @RequestMapping(value = Section.TitlePrefix + "/getTitles",method = RequestMethod.GET)
    public ResponseEntityDTO<ResponseDTO> getTitles() {
        return new ResponseEntityDTO<>(titleService.getTitles());
    }

    @RequestMapping(value = Section.TitlePrefix + "/getTitleById",method = RequestMethod.GET)
    public ResponseEntityDTO<ResponseDTO> getArchetype(Long id) {
        return new ResponseEntityDTO<>(titleService.getTitle(id));
    }

    @RequestMapping(value = Section.TitlePrefix + "/removeTitle",method = RequestMethod.DELETE)
    public ResponseEntityDTO<ResponseDTO> removeTitle(Long id) {
        return new ResponseEntityDTO<>(titleService.removeTitle(id));
    }

    @PostMapping(value = Section.TitlePrefix + "/createTitle", consumes = "application/json", produces = "application/json")
    public ResponseEntityDTO<ResponseDTO> createTitle(@RequestBody String rawTitle) {
        return new ResponseEntityDTO<>(titleService.createTitle(rawTitle));
    }

    @PostMapping(value = Section.TitlePrefix + "/updateTitle", consumes = "application/json", produces = "application/json")
    public ResponseEntityDTO<ResponseDTO> updateTitle(@RequestBody String rawTitle) {
        return new ResponseEntityDTO<>(titleService.updateTitle(rawTitle));
    }
}
