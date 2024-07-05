package com.practise.qadma.controller;

import com.practise.qadma.payload.changenote.InspectionPlanChangeNoteDTO;
import com.practise.qadma.service.InspectionPlanChangeNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/ipcn")
public class InspectionPlanChangeNoteController {

    private InspectionPlanChangeNoteService inspectionPlanChangeNoteService;

    @Autowired
    public InspectionPlanChangeNoteController(InspectionPlanChangeNoteService inspectionPlanChangeNoteService) {
        this.inspectionPlanChangeNoteService = inspectionPlanChangeNoteService;
    }

    @PostMapping
    public List<InspectionPlanChangeNoteDTO> save(@RequestBody InspectionPlanChangeNoteDTO inspectionPlanChangeNoteDTO) {
        return inspectionPlanChangeNoteService.save(inspectionPlanChangeNoteDTO);
    }
}
