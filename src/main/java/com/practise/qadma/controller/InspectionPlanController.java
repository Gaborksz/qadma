package com.practise.qadma.controller;

import com.practise.qadma.mappingservice.InspectionPlanMappingService;
import com.practise.qadma.payload.InspectionPlanDTO;
import com.practise.qadma.payload.view.InspectionPlanViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/inspection-plan")
public class InspectionPlanController {

    private InspectionPlanMappingService inspectionPlanMappingService;


    @Autowired
    public InspectionPlanController(InspectionPlanMappingService inspectionPlanMappingService) {
        this.inspectionPlanMappingService = inspectionPlanMappingService;
    }


    @GetMapping("/{id}")
    public InspectionPlanViewDTO findInspectionPlan(@PathVariable long id) {
        return inspectionPlanMappingService.findById(id);
    }

    @PostMapping
    public  InspectionPlanViewDTO save( @RequestBody InspectionPlanDTO inspectionPlanDTO) {
        return inspectionPlanMappingService.save(inspectionPlanDTO);
    }
}
