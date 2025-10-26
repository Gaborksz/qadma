package com.practise.qadma.controller;

import com.practise.qadma.payload.InspectionTemplateChangeNoteDTO;
import com.practise.qadma.payload.view.InspectionTemplateChangeNoteViewDTO;
import com.practise.qadma.service.InspectionTemplateChangeNoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inspection-template-change-note")
public class InspectionTemplateChangeNoteController {

    private final InspectionTemplateChangeNoteService inspectionTemplateChangeNoteService;

    public InspectionTemplateChangeNoteController(InspectionTemplateChangeNoteService inspectionTemplateChangeNoteService) {
        this.inspectionTemplateChangeNoteService = inspectionTemplateChangeNoteService;
    }

    @PostMapping()
    public ResponseEntity<InspectionTemplateChangeNoteViewDTO> save(@RequestBody InspectionTemplateChangeNoteDTO changeNoteDTO) {

        System.out.println(changeNoteDTO);

        InspectionTemplateChangeNoteViewDTO  changeNote = inspectionTemplateChangeNoteService.save(changeNoteDTO);

        return ResponseEntity.ok(changeNote);
    }
}
