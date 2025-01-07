package com.practise.qadma.conversion;

import com.practise.qadma.payload.view.InspectionPlanChangeNoteViewDTO;

public interface InspectionPlanChangeNoteConversionService {

    InspectionPlanChangeNoteViewDTO findById(long id);
}
