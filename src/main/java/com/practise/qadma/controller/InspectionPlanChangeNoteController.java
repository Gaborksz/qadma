package com.practise.qadma.controller;

import com.practise.qadma.conversion.InspectionPlanChangeNoteConversionService;
import com.practise.qadma.payload.view.InspectionPlanChangeNoteViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/inspection-plan-change-note")
public class InspectionPlanChangeNoteController {

    private InspectionPlanChangeNoteConversionService inspectionPlanChangeNoteConversionService;

    @Autowired
    public InspectionPlanChangeNoteController(InspectionPlanChangeNoteConversionService inspectionPlanChangeNoteConversionService) {
        this.inspectionPlanChangeNoteConversionService = inspectionPlanChangeNoteConversionService;
    }

    @GetMapping("/{id}")
    public InspectionPlanChangeNoteViewDTO findById(@PathVariable long id) {
        return inspectionPlanChangeNoteConversionService.findById(id);
    }
}
