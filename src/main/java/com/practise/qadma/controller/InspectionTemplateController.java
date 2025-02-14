package com.practise.qadma.controller;


import com.practise.qadma.mappingservice.InspectionTemplateMappingService;
import com.practise.qadma.payload.view.InspectionTemplateViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inspection-template")
public class InspectionTemplateController {

    private final InspectionTemplateMappingService inspectionTemplateMappingService;


    @Autowired
    public InspectionTemplateController(InspectionTemplateMappingService inspectionTemplateMappingService) {
        this.inspectionTemplateMappingService = inspectionTemplateMappingService;
    }

    @GetMapping("/{id}")
    public InspectionTemplateViewDTO findById(@PathVariable long id) {
        return inspectionTemplateMappingService.findById(id);
    }
}
