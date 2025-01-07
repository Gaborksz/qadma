package com.practise.qadma.controller;

import com.practise.qadma.conversion.ChangeManagerConversionService;
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

    private final ChangeManagerConversionService changeManagerConversionService;

    @Autowired
    public ChangeManagerController(ChangeManagerConversionService changeManagerConversionService) {
        this.changeManagerConversionService = changeManagerConversionService;
    }

    @PostMapping("/inspection-plan")
    public List<InspectionPlanChangeNoteViewDTO> processInspectionPlanChangeNote(
            @RequestBody InspectionPlanChangeNoteDTO inspectionPlanChangeNoteDTO) {

        return changeManagerConversionService.processInspectionPlanChangeNote(inspectionPlanChangeNoteDTO);
    }

    @PostMapping("/product")
    public List<ProductChangeNoteViewDTO> processProductChangeNote(
            @RequestBody ProductChangeNoteDTO productChangeNoteDTO) {

        return changeManagerConversionService.processProductChangeNote(productChangeNoteDTO);
    }

    @GetMapping("product/{id}")
    public  ProductChangeNoteViewDTO getProductChangeNote(
            @PathVariable long id) {

        return  changeManagerConversionService.getProductChangeNote(id);
    }
}
