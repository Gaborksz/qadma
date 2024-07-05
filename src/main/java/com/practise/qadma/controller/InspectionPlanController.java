package com.practise.qadma.controller;

import com.practise.qadma.payload.inspectionplan.InspectionPlanDTO;
import com.practise.qadma.service.InspectionPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/inspectionplan")
public class InspectionPlanController {

    private InspectionPlanService inspectionPlanService;


    @Autowired
    public InspectionPlanController(InspectionPlanService inspectionPlanService) {
        this.inspectionPlanService = inspectionPlanService;
    }


    @GetMapping("/{id}")
    public InspectionPlanDTO findInspectionPlan(@PathVariable long id) {
        return inspectionPlanService.findById(id);
    }

    @PostMapping
    public InspectionPlanDTO createInspectionPlan(@RequestBody InspectionPlanDTO inspectionPlanDTO) {
        return inspectionPlanService.save(inspectionPlanDTO);
    }

    @PutMapping("/{id}")
    public InspectionPlanDTO updateInspectionPlan(@RequestBody InspectionPlanDTO inspectionPlanUpdateDTO) {
        return inspectionPlanService.update(inspectionPlanUpdateDTO);
    }
}
