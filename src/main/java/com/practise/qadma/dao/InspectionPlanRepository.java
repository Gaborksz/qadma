package com.practise.qadma.dao;

import com.practise.qadma.entity.InspectionPlan;

import java.util.Optional;

public interface InspectionPlanRepository {

    Optional<InspectionPlan> findById(long id);

    InspectionPlan save(InspectionPlan inspectionPlan);

    InspectionPlan update (InspectionPlan inspectionPlan);

    InspectionPlan finByProductId(long productId);
}
