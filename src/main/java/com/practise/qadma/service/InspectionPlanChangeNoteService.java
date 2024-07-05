package com.practise.qadma.service;

import com.practise.qadma.payload.changenote.InspectionPlanChangeNoteDTO;

import java.util.List;


public interface InspectionPlanChangeNoteService {

    List<InspectionPlanChangeNoteDTO> save(InspectionPlanChangeNoteDTO inspectionPlanChangeNoteDTO);
}
