package com.practise.qadma.dao;

import com.practise.qadma.entity.InspectionPlanChangeNote;

public interface InspectionPlanChangeNoteRepository {

    InspectionPlanChangeNote findById(long id);

    InspectionPlanChangeNote save(InspectionPlanChangeNote changeNote);
}
