package com.practise.qadma.controller;


import com.practise.qadma.conversion.InspectionTemplateConversionService;
import com.practise.qadma.payload.InspectionTemplateDTO;
import com.practise.qadma.payload.view.InspectionTemplateViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inspection-template")
public class InspectionTemplateController {

    private InspectionTemplateConversionService inspectionTemplateConversionService;


    @Autowired
    public InspectionTemplateController(InspectionTemplateConversionService inspectionTemplateConversionService) {
        this.inspectionTemplateConversionService = inspectionTemplateConversionService;
    }

    @GetMapping("/{id}")
    public InspectionTemplateViewDTO findById(@PathVariable long id) {
        return inspectionTemplateConversionService.findById(id);
    }
}
