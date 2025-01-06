package com.practise.qadma.service;

import com.practise.qadma.entity.InspectionPlanChangeNote;

public interface InspectionPlanChangeNoteService {

    InspectionPlanChangeNote findById(long id);

    InspectionPlanChangeNote save(InspectionPlanChangeNote changeNote);
}
