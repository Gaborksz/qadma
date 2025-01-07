package com.practise.qadma.mappingservice;

import com.practise.qadma.payload.view.InspectionPlanChangeNoteViewDTO;

public interface InspectionPlanChangeNoteMappingService {

    InspectionPlanChangeNoteViewDTO findById(long id);
}
