package com.masquerade.controller.characterSheet.parameter;

import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.dto.controller.ResponseEntityDTO;
import com.masquerade.service.characterSheet.parameter.StatusService;
import com.masquerade.tools.controller.Section;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = Section.CrossOriginUrl)
@RestController
public class StatusController {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @RequestMapping(value = Section.StatusPrefix + "/getStatus",method = RequestMethod.GET)
    public ResponseEntityDTO<ResponseDTO> getStatus() {
        return new ResponseEntityDTO<>(statusService.getStatus());
    }

    @RequestMapping(value = Section.StatusPrefix + "/getStatusById",method = RequestMethod.GET)
    public ResponseEntityDTO<ResponseDTO> getStatusById(Long id) {
        return new ResponseEntityDTO<>(statusService.getStatus(id));
    }

    @PostMapping(value = Section.StatusPrefix + "/createStatus", consumes = "application/json", produces = "application/json")
    public ResponseEntityDTO<ResponseDTO> createStatus(@RequestBody String rawBody) {
        return new ResponseEntityDTO<>(statusService.createStatus(rawBody));
    }

    @PostMapping(value = Section.StatusPrefix + "/updateStatus", consumes = "application/json", produces = "application/json")
    public ResponseEntityDTO<ResponseDTO> updateStatus(@RequestBody String rawBody) {
        return new ResponseEntityDTO<>(statusService.updateStatus(rawBody));
    }

    @RequestMapping(value = Section.StatusPrefix + "/removeStatus",method = RequestMethod.DELETE)
    public ResponseEntityDTO<ResponseDTO> removeStatus(Long id) {
        return new ResponseEntityDTO<>(statusService.removeStatus(id));
    }
}
