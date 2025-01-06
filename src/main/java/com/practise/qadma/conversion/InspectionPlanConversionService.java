package com.practise.qadma.conversion;

import com.practise.qadma.payload.InspectionPlanDTO;
import com.practise.qadma.payload.view.InspectionPlanViewDTO;


public interface InspectionPlanConversionService {

    InspectionPlanViewDTO findById(long id);

    InspectionPlanViewDTO save(InspectionPlanDTO inspectionPlanDTO);

    InspectionPlanViewDTO update(InspectionPlanDTO inspectionPlanDTO);
}
