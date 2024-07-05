package com.practise.qadma.controller;


import com.practise.qadma.payload.inspectiontemplate.InspectionTemplateDTO;
import com.practise.qadma.service.InspectionTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inspectiontemplate")
public class InspectionTemplateController {

    private InspectionTemplateService inspectionTemplateService;


    @Autowired
    public InspectionTemplateController(InspectionTemplateService inspectionTemplateService) {
        this.inspectionTemplateService = inspectionTemplateService;
    }

    @GetMapping()
    public InspectionTemplateDTO findById(@PathVariable("/{id}") long id) {
        return inspectionTemplateService.findById(id);
    }
}
