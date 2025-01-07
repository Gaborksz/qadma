package com.practise.qadma.controller;

import com.practise.qadma.mappingservice.InspectionPlanChangeNoteMappingService;
import com.practise.qadma.payload.view.InspectionPlanChangeNoteViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/inspection-plan-change-note")
public class InspectionPlanChangeNoteController {

    private InspectionPlanChangeNoteMappingService inspectionPlanChangeNoteMappingService;

    @Autowired
    public InspectionPlanChangeNoteController(InspectionPlanChangeNoteMappingService inspectionPlanChangeNoteMappingService) {
        this.inspectionPlanChangeNoteMappingService = inspectionPlanChangeNoteMappingService;
    }

    @GetMapping("/{id}")
    public InspectionPlanChangeNoteViewDTO findById(@PathVariable long id) {
        return inspectionPlanChangeNoteMappingService.findById(id);
    }
}
