package com.masquerade.controller.characterSheet.parameter;

import com.masquerade.model.dto.controller.ResponseDTO;
import com.masquerade.model.dto.controller.ResponseEntityDTO;
import com.masquerade.service.characterSheet.parameter.StatusTypeService;
import com.masquerade.tools.controller.Section;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = Section.CrossOriginUrl)
@RestController
public class StatusTypeController {
    private final StatusTypeService statusTypeService;

    public StatusTypeController(StatusTypeService statusTypeService) {
        this.statusTypeService = statusTypeService;
    }

    @RequestMapping(value = Section.StatusTypePrefix + "/getStatusTypes",method = RequestMethod.GET)
    public ResponseEntityDTO<ResponseDTO> getStatusTypes() {
        return new ResponseEntityDTO<>(statusTypeService.getStatusTypes());
    }

    @RequestMapping(value = Section.StatusTypePrefix + "/getStatusTypeById",method = RequestMethod.GET)
    public ResponseEntityDTO<ResponseDTO> getStatusType(Long id) {
        return new ResponseEntityDTO<>(statusTypeService.getStatusType(id));
    }

    @RequestMapping(value = Section.StatusTypePrefix + "/removeStatusType",method = RequestMethod.DELETE)
    public ResponseEntityDTO<ResponseDTO> removeStatusType(Long id) {
        return new ResponseEntityDTO<>(statusTypeService.removeStatusType(id));
    }

    @PostMapping(value = Section.StatusTypePrefix + "/createStatusType", consumes = "application/json", produces = "application/json")
    public ResponseEntityDTO<ResponseDTO> createStatusType(@RequestBody String rawClan) {
        return new ResponseEntityDTO<>(statusTypeService.createStatusType(rawClan));
    }

    @PostMapping(value = Section.StatusTypePrefix + "/updateStatusType", consumes = "application/json", produces = "application/json")
    public ResponseEntityDTO<ResponseDTO> updateStatusType(@RequestBody String rawClan) {
        return new ResponseEntityDTO<>(statusTypeService.updateStatusType(rawClan));
    }
}
