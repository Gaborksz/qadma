package com.practise.qadma.service;

import com.practise.qadma.entity.InspectionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SequenceService {

    private InspectionTemplateService inspectionTemplateService;
    private InspectionTemplateChangeNoteService inspectionTemplateChangeNoteService;

    @Autowired
    public SequenceService(InspectionTemplateService inspectionTemplateService, InspectionTemplateChangeNoteService inspectionTemplateChangeNoteService) {
        this.inspectionTemplateService = inspectionTemplateService;
        this.inspectionTemplateChangeNoteService = inspectionTemplateChangeNoteService;
    }


    public Map<Integer, InspectionTemplate> saveSequence(Map<Integer, InspectionTemplate> sequence) {

//        sequence.forEach((sequenceNumber, template) -> {
//            if (template.getId() == 0) {
//                inspectionTemplateService.save(template);
//            } else {
//                inspectionTemplateService.findById(template.getId());
//            }
//        });

        return sequence;
    }
}
