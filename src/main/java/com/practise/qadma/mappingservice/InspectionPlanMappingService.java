package com.practise.qadma.mappingservice;

import com.practise.qadma.payload.InspectionPlanDTO;
import com.practise.qadma.payload.view.InspectionPlanViewDTO;


public interface InspectionPlanMappingService {

    InspectionPlanViewDTO findById(long id);

    InspectionPlanViewDTO save(InspectionPlanDTO inspectionPlanDTO);

    InspectionPlanViewDTO update(InspectionPlanDTO inspectionPlanDTO);
}
