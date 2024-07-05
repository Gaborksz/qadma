package com.practise.qadma.service;

import com.practise.qadma.exception.ItemNotFoundException;
import com.practise.qadma.payload.inspectionplan.InspectionPlanDTO;

public interface InspectionPlanService {

    InspectionPlanDTO findById(long id) throws ItemNotFoundException;

    InspectionPlanDTO save(InspectionPlanDTO inspectionPlanDTO);

    InspectionPlanDTO update(InspectionPlanDTO updateDTO);
}
