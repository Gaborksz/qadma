package com.practise.qadma.service;

import com.practise.qadma.entity.ArchivedInspectionTemplate;
import com.practise.qadma.entity.InspectionTemplate;
import com.practise.qadma.entity.InspectionTemplateChangeNote;

public interface InspectionTemplateChangeNoteService {

    InspectionTemplateChangeNote createChangeNoteForNewTemplate(InspectionTemplate inspectionTemplate);

    InspectionTemplateChangeNote save(InspectionTemplateChangeNote changeNote);

    InspectionTemplateChangeNote saveAndFlush(InspectionTemplateChangeNote changeNote);
}
