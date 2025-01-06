package com.practise.qadma.controller;

import com.practise.qadma.conversion.InspectionPlanConversionService;
import com.practise.qadma.payload.InspectionPlanDTO;
import com.practise.qadma.payload.view.InspectionPlanViewDTO;
import com.practise.qadma.service.InspectionPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/inspection-plan")
public class InspectionPlanController {

    private InspectionPlanConversionService inspectionPlanConversionService;


    @Autowired
    public InspectionPlanController(InspectionPlanConversionService inspectionPlanConversionService) {
        this.inspectionPlanConversionService = inspectionPlanConversionService;
    }


    @GetMapping("/{id}")
    public InspectionPlanViewDTO findInspectionPlan(@PathVariable long id) {
        return inspectionPlanConversionService.findById(id);
    }

    @PostMapping
    public  InspectionPlanViewDTO save( @RequestBody InspectionPlanDTO inspectionPlanDTO) {
        return inspectionPlanConversionService.save(inspectionPlanDTO);
    }
}
