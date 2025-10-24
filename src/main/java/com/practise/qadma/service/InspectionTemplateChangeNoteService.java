package com.practise.qadma.service;

import com.practise.qadma.entity.InspectionTemplate;
import com.practise.qadma.entity.InspectionTemplateChangeNote;
import com.practise.qadma.payload.InspectionTemplateChangeNoteDTO;
import com.practise.qadma.payload.view.InspectionTemplateChangeNoteViewDTO;

public interface InspectionTemplateChangeNoteService {

    InspectionTemplateChangeNoteViewDTO save(InspectionTemplateChangeNoteDTO changeNote);
}
