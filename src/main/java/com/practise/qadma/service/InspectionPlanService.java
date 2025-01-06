package com.practise.qadma.service;

import com.practise.qadma.entity.InspectionPlan;

public interface InspectionPlanService {

    InspectionPlan findById(long id);

    InspectionPlan save( InspectionPlan inspectionPlan);

    InspectionPlan update(InspectionPlan inspectionPlan);

    InspectionPlan findByProductId(long productId
    );
}