package com.practise.qadma.dao;

import com.practise.qadma.entity.InspectionPlanChangeNote;

import java.util.Optional;

public interface InspectionPlanChangeNoteRepository {

    Optional<InspectionPlanChangeNote> findById(long id);

    InspectionPlanChangeNote save(InspectionPlanChangeNote changeNote);
}
