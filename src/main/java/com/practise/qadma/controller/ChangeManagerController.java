package com.practise.qadma.controller;

import com.practise.qadma.mappingservice.ChangeManagerMappingService;
import com.practise.qadma.payload.InspectionPlanChangeNoteDTO;
import com.practise.qadma.payload.ProductChangeNoteDTO;
import com.practise.qadma.payload.view.InspectionPlanChangeNoteViewDTO;
import com.practise.qadma.payload.view.ProductChangeNoteViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/change-note")
public class ChangeManagerController {

    private final ChangeManagerMappingService changeManagerMappingService;

    @Autowired
    public ChangeManagerController(ChangeManagerMappingService changeManagerMappingService) {
        this.changeManagerMappingService = changeManagerMappingService;
    }

    @PostMapping("/inspection-plan")
    public List<InspectionPlanChangeNoteViewDTO> processInspectionPlanChangeNote(
            @RequestBody InspectionPlanChangeNoteDTO inspectionPlanChangeNoteDTO) {

        return changeManagerMappingService.processInspectionPlanChangeNote(inspectionPlanChangeNoteDTO);
    }

    @PostMapping("/product")
    public List<ProductChangeNoteViewDTO> processProductChangeNote(
            @RequestBody ProductChangeNoteDTO productChangeNoteDTO) {

        System.out.println(productChangeNoteDTO);

        return changeManagerMappingService.processProductChangeNote(productChangeNoteDTO);
    }
}
