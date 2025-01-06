package com.practise.qadma.dao;

import com.practise.qadma.entity.InspectionTemplateChangeNote;

public interface InspectionTemplateChangeNoteRepository {

    InspectionTemplateChangeNote save(InspectionTemplateChangeNote changeNote);

    InspectionTemplateChangeNote saveAndFlush(InspectionTemplateChangeNote changeNote);
}
